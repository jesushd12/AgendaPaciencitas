package com.example.jesus.agendapaciencitas;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoriaPaciente extends AppCompatActivity {
    int idPaciente;
    Paciente paciente;
    ArrayList<Cita>citas;
    TextView nombre;
    TextView apellido;
    TextView cedula;
    TextView email;
    TextView tlfCasa;
    TextView tlfCel;
    TextView direccion;
    ListView listView_historia;
    RelativeLayout relativeLayout = null;

    private HashMap<String, String> datosSesion=null;
    String username="chikito";

    SimpleDateFormat formatoFechaServidor = new SimpleDateFormat("yyyy-MM-dd");

    ProgressDialog pDialog;
    SessionManager sessionManager;
    public HistoriaPaciente() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia_paciente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        if(sessionManager.isLoggedIn()){
            datosSesion = new HashMap<>();
            datosSesion = sessionManager.getUserDetails();
            username = datosSesion.get("name");

        }
        paciente = new Paciente();
        paciente.setId(getIntent().getExtras().getInt("idPaciente"));
        paciente.setNombre(getIntent().getExtras().getString("nombre"));
        paciente.setApellido(getIntent().getExtras().getString("apellido"));
        paciente.setCedula(getIntent().getExtras().getString("cedula"));
        paciente.setCorreo(getIntent().getExtras().getString("email"));
        paciente.setTlfCasa(getIntent().getExtras().getString("tlfcasa"));
        paciente.setTlfCelular(getIntent().getExtras().getString("tlfcelular"));
        paciente.setDireccion(getIntent().getExtras().getString("direccion"));


        nombre = (TextView)findViewById(R.id.txt_nombrePaciente);
        apellido = (TextView)findViewById(R.id.txt_apellidoPaciente);
        cedula = (TextView) findViewById(R.id.txt_cedPaciente);
        email = (TextView)findViewById(R.id.txt_emailpaciente);
        tlfCasa = (TextView)findViewById(R.id.txt_tlf1);
        tlfCel = (TextView)findViewById(R.id.txt_tlf2);
        direccion = (TextView)findViewById(R.id.txt_direccion);
        listView_historia = (ListView)findViewById(R.id.listaHistoria);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeFragment);
        relativeLayout.setOnTouchListener(new OnSwipeTouchListener(this){

            public void onSwipeRight() {
                finish();
            }

        });


        nombre.setText("Nombre: "+paciente.getNombre());
        apellido.setText("Apellido: "+paciente.getApellido());
        cedula.setText("Cedula: "+paciente.getCedula());
        email.setText("EmailL "+paciente.getCorreo());
        tlfCasa.setText("Tlf Casa: "+paciente.getTlfCasa());
        tlfCel.setText("Tlf Celular: "+paciente.getTlfCelular());
        direccion.setText("Direccion: "+paciente.getDireccion());




        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Cargando historia...");
        pDialog.setCancelable(false);
        showpDialog();
        JsonArrayRequest req = new JsonArrayRequest(URL.rutaHistoriaPaciente+paciente.getId()+"/"+username,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            citas = new ArrayList<>();
                            for(int auxiliar = 0; auxiliar<response.length();auxiliar++) {
                                JSONObject jsonObject  = response.getJSONObject(auxiliar);
                                Cita citaInd = new Cita();
                                citaInd.setId(jsonObject.getInt("id"));
                                try {
                                    citaInd.setFecha(formatoFechaServidor.parse(jsonObject.getString("fecha")));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                citaInd.setIdPaciente(jsonObject.getInt("paciente"));
                                citaInd.setIdMedico(jsonObject.getInt("medicos"));
                                citaInd.setTratamiento(jsonObject.getString("tratamiento"));
                                citaInd.setDiagnostico(jsonObject.getString("diagnostico"));
                                citaInd.setMotivo(jsonObject.getString("motivo"));
                                citas.add(citaInd);
                            }

                            BaseAdapter adapter = new BaseAdapter() {
                                @Override
                                public int getCount() {
                                    return citas.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return citas.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View rowView =convertView;
                                    if(convertView==null){
                                        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        rowView = inflater.inflate(R.layout.historia_cita,parent, false);
                                    }
                                    TextView fecha =(TextView)rowView.findViewById(R.id.txt_fechaCita);
                                    TextView hora = (TextView)rowView.findViewById(R.id.txt_horaCita);
                                    TextView motivo = (TextView)rowView.findViewById(R.id.txt_motivo);
                                    TextView tratamiento = (TextView)rowView.findViewById(R.id.txt_tratamiento);
                                    TextView diagnostico = (TextView)rowView.findViewById(R.id.txt_diagnostico);

                                    String fechaCita = "Fecha: "+formatoFechaServidor.format(citas.get(position).getFecha());
                                    fecha.setText(fechaCita);
                                    String horaCita = "Hora: "+citas.get(position).getHora();
                                    hora.setText(horaCita);
                                    String tratamientoCita = "Tratamiento: "+citas.get(position).getTratamiento();
                                    tratamiento.setText(tratamientoCita);
                                    String diagnosticoCita = "Diagnostico: "+citas.get(position).getDiagnostico();
                                    diagnostico.setText(diagnosticoCita);
                                    String motivoCita = "Motivo : "+citas.get(position).getMotivo();
                                    motivo.setText(motivoCita);

                                    return rowView;
                                }
                            };
                            listView_historia.setAdapter(adapter);

                        } catch (JSONException e) {
                            System.out.println("Error");
                            e.printStackTrace();
                        }
                        hidepDialog();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
            }
        });
        // Adding request to request queue
        Volley.newRequestQueue(this).add(req);




    }



    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
