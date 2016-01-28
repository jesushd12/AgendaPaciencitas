package com.example.jesus.agendapaciencitas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jesus on 12/9/2015.
 */
public class Fragmento2 extends Fragment {
    ListView misPacientes;
    ArrayAdapter<String> listAdapter;
    ArrayList<Paciente> pacientes;
    ArrayList<Paciente> auxiliarPaciente;
    EditText search;

    private ProgressDialog pDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento2_layout,container,false);
        misPacientes = (ListView)v.findViewById(R.id.listaMisPacientes);
        search = (EditText)v.findViewById(R.id.edt_search);
        search.setEnabled(false);
        auxiliarPaciente = null;

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Cargando citas...");
        pDialog.setCancelable(false);
        showpDialog();
        JsonArrayRequest req = new JsonArrayRequest(URL.rutaMisPacientes+"jesus",
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            pacientes = new ArrayList<>();
                            for(int auxiliar = 0; auxiliar<response.length();auxiliar++) {
                                JSONObject jsonObject  = response.getJSONObject(auxiliar);
                                Paciente pacienteInd = new Paciente();
                                pacienteInd.setId(jsonObject.getInt("id"));
                                pacienteInd.setNombre(jsonObject.getString("nombre"));
                                pacienteInd.setApellido(jsonObject.getString("apellido"));
                                pacienteInd.setCedula(jsonObject.getString("cedula"));
                                pacienteInd.setDireccion(jsonObject.getString("direccion"));
                                pacienteInd.setCorreo(jsonObject.getString("correo"));
                                pacienteInd.setTlfCasa(jsonObject.getString("tlfncasa"));
                                pacienteInd.setTlfCelular(jsonObject.getString("tlfncelular"));
                                pacientes.add(pacienteInd);
                            }

                            auxiliarPaciente = pacientes;

                            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, pacientes) {
                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = super.getView(position, convertView, parent);
                                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                                    text1.setText(pacientes.get(position).getNombre()+" "+pacientes.get(position).getApellido());
                                    return view;
                                }
                            };
                            misPacientes.setAdapter(adapter);
                            misPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                                @Override
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    Intent intent = new Intent(getActivity(),HistoriaPaciente.class);
                                    intent.putExtra("idPaciente",pacientes.get(position).getId());
                                    intent.putExtra("nombre",pacientes.get(position).getNombre());
                                    intent.putExtra("apellido",pacientes.get(position).getApellido());
                                    intent.putExtra("cedula",pacientes.get(position).getCedula());
                                    intent.putExtra("email",pacientes.get(position).getCorreo());
                                    intent.putExtra("tlfcasa",pacientes.get(position).getTlfCasa());
                                    intent.putExtra("tlfcelular",pacientes.get(position).getTlfCelular());
                                    intent.putExtra("direccion",pacientes.get(position).getDireccion());
                                    startActivity(intent);
                                }

                            });

                        } catch (JSONException e) {
                            System.out.println("Error");
                            e.printStackTrace();
                        }
                        search.setEnabled(true);
                        hidepDialog();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
            }
        });
        // Adding request to request queue
        Volley.newRequestQueue(getContext()).add(req);


        return v;
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
