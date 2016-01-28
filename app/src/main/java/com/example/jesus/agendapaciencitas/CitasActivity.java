package com.example.jesus.agendapaciencitas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CitasActivity extends AppCompatActivity {

    ExpandableListView exp_list;
    ArrayList<Cita> citasDelDia;
    private ProgressDialog pDialog;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String fechaSeleccionada = getIntent().getExtras().getString("fechaseleccionada");
        getSupportActionBar().setTitle("Citas del dia: "+fechaSeleccionada);
        String username="/jesus";

        exp_list = (ExpandableListView)findViewById(R.id.lalistica);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Cargando citas...");
        pDialog.setCancelable(false);

        showpDialog();

        //Peticion al servidor de todas las citas correspondientes a la fecha seleccionada
        JsonObjectRequest req2 = new JsonObjectRequest(Request.Method.GET,URL.rutaCitasPorFecha+fechaSeleccionada+username,null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {

                try {
                    // Parsing json object response
                    // response will be a json object
                    citasDelDia= new ArrayList<>();
                    JSONArray jsonArray = response.getJSONArray("citas");
                    for(int aux = 0 ; aux  <jsonArray.length();aux++){
                        JSONObject jsonObject = jsonArray.getJSONObject(aux);
                        Cita citaInd = new Cita();
                        citaInd.setId(jsonObject.getInt("id"));
                        citaInd.setHora(jsonObject.getString("hora"));
                        citaInd.setIdPaciente(jsonObject.getInt("id_pa"));
                        citaInd.setMotivo(jsonObject.getString("motivo"));
                        citaInd.setNombrePaciente(jsonObject.getString("nombre"));
                        citaInd.setApellidoPaciente(jsonObject.getString("apellido"));
                        citasDelDia.add(citaInd);
                    }

                    BaseExpandableListAdapter baseExpandableListAdapter = new BaseExpandableListAdapter() {
                        @Override
                        public int getGroupCount() {
                            return citasDelDia.size();
                        }

                        @Override
                        public int getChildrenCount(int groupPosition) {
                            return 1;
                        }

                        @Override
                        public Object getGroup(int groupPosition) {
                            return citasDelDia.get(groupPosition).getHora()+" : "+citasDelDia.get(groupPosition).getNombrePaciente()+" "+citasDelDia.get(groupPosition).getApellidoPaciente();
                        }

                        @Override
                        public Object getChild(int groupPosition, int childPosition) {
                            return citasDelDia.get(groupPosition).getTodosLosMotivos().get(childPosition);
                        }

                        @Override
                        public long getGroupId(int groupPosition) {
                            return groupPosition;
                        }

                        @Override
                        public long getChildId(int groupPosition, int childPosition) {
                            return childPosition;
                        }

                        @Override
                        public boolean hasStableIds() {
                            return false;
                        }

                        @Override
                        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                            String group_title = (String)getGroup(groupPosition);
                            if(convertView==null){
                                LayoutInflater inflator = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                //convertView = inflator.inflate(R.layout.header_list_layout,parent,false);
                                convertView = inflator.inflate(android.R.layout.simple_expandable_list_item_1,parent,false);
                            }
                            //TextView header_textView =(TextView) convertView.findViewById(R.id.header_list_name);
                            TextView header_textView =(TextView) convertView.findViewById(android.R.id.text1);
                            header_textView.setText(group_title);
                            header_textView.setBackgroundResource(R.color.colorSuave);
                            return convertView;
                        }

                        @Override
                        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                            String child_title = (String)getChild(groupPosition,childPosition);
                            if(convertView==null){
                                LayoutInflater inflator = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                //convertView = inflator.inflate(R.layout.child_list_layout,parent,false);
                                convertView = inflator.inflate(android.R.layout.simple_expandable_list_item_1,parent,false);
                            }
                            //TextView child_textView =(TextView) convertView.findViewById(R.id.child_list_name);
                            TextView child_textView =(TextView) convertView.findViewById(android.R.id.text1);
                            child_textView.setText("    Motivo: "+child_title);


                            return convertView;
                        }

                        @Override
                        public boolean isChildSelectable(int groupPosition, int childPosition) {
                            //System.out.println("Presione un nino"+groupPosition+" " +childPosition);
                            return true;
                        }


                    };
                    exp_list.setAdapter(baseExpandableListAdapter);
                    exp_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                        @Override
                        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                            System.out.println("Presione un nino"+citasDelDia.get(groupPosition).getNombrePaciente());
                            return false;
                        }
                    });

                } catch (JSONException e) {

                    e.printStackTrace();
                    Toast.makeText(getParent(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // hide the progress dialog

            }
        });

        Volley.newRequestQueue(this).add(req2);

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


        /*
        JsonArrayRequest req = new JsonArrayRequest(URL.rutaCitasPorFecha+fechaSeleccionada+username,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            citasDelDia = new ArrayList<>();
                            for(int auxiliar = 0; auxiliar<response.length();auxiliar++) {
                                Cita citaInd = new Cita();
                                citaInd.setId(response.getJSONObject(auxiliar).getInt("id"));
                                citaInd.setHora(response.getJSONObject(auxiliar).getString("hora"));
                                citaInd.setIdPaciente(response.getJSONObject(auxiliar).getInt("id_pa"));
                                citaInd.setMotivo(response.getJSONObject(auxiliar).getString("motivo"));
                                citaInd.setNombrePaciente(response.getJSONObject(auxiliar).getString("nombre"));
                                citaInd.setApellidoPaciente(response.getJSONObject(auxiliar).getString("apellido"));

                                System.out.println("Cita: " + citaInd.toString());
                                citasDelDia.add(citaInd);
                            }
                            BaseExpandableListAdapter baseExpandableListAdapter = new BaseExpandableListAdapter() {
                                @Override
                                public int getGroupCount() {
                                    return citasDelDia.size();
                                }

                                @Override
                                public int getChildrenCount(int groupPosition) {
                                    return 1;
                                }

                                @Override
                                public Object getGroup(int groupPosition) {
                                    return citasDelDia.get(groupPosition).getHora()+" : "+citasDelDia.get(groupPosition).getNombrePaciente()+" "+citasDelDia.get(groupPosition).getApellidoPaciente();
                                }

                                @Override
                                public Object getChild(int groupPosition, int childPosition) {
                                    return citasDelDia.get(groupPosition).getTodosLosMotivos().get(childPosition);
                                }

                                @Override
                                public long getGroupId(int groupPosition) {
                                    return groupPosition;
                                }

                                @Override
                                public long getChildId(int groupPosition, int childPosition) {
                                    return childPosition;
                                }

                                @Override
                                public boolean hasStableIds() {
                                    return false;
                                }

                                @Override
                                public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                                    String group_title = (String)getGroup(groupPosition);
                                    if(convertView==null){
                                        LayoutInflater inflator = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        //convertView = inflator.inflate(R.layout.header_list_layout,parent,false);
                                        convertView = inflator.inflate(android.R.layout.simple_expandable_list_item_1,parent,false);
                                    }
                                    //TextView header_textView =(TextView) convertView.findViewById(R.id.header_list_name);
                                    TextView header_textView =(TextView) convertView.findViewById(android.R.id.text1);
                                    header_textView.setText(group_title);
                                    return convertView;
                                }

                                @Override
                                public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                                    String child_title = (String)getChild(groupPosition,childPosition);
                                    if(convertView==null){
                                        LayoutInflater inflator = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                        //convertView = inflator.inflate(R.layout.child_list_layout,parent,false);
                                         convertView = inflator.inflate(android.R.layout.simple_list_item_1,parent,false);
                                    }
                                    //TextView child_textView =(TextView) convertView.findViewById(R.id.child_list_name);
                                    TextView child_textView =(TextView) convertView.findViewById(android.R.id.text1);
                                    child_textView.setText(child_title);

                                    return convertView;
                                }

                                @Override
                                public boolean isChildSelectable(int groupPosition, int childPosition) {
                                    return true;
                                }


                            };
                            exp_list.setAdapter(baseExpandableListAdapter);

                        } catch (JSONException e) {
                            System.out.println("Error");
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getParent(),"Error de servidor 2",Toast.LENGTH_LONG).show();

                System.out.println("HAY UN ERROR"+error.toString());
            }
        });
        // Adding request to request queue
        Volley.newRequestQueue(this).add(req);
        */