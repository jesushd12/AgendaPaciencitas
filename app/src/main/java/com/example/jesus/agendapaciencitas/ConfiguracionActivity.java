package com.example.jesus.agendapaciencitas;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class ConfiguracionActivity extends AppCompatActivity {

    EditText horaInicio = null;
    EditText horaFin = null;
    EditText tiempoAtencion = null;
    CheckBox lunes = null;
    CheckBox martes = null;
    CheckBox miercoles = null;
    CheckBox jueves = null;
    CheckBox viernes = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        horaInicio = (EditText)findViewById(R.id.edt_horaInicio);
        horaFin = (EditText)findViewById(R.id.edt_horafin);
        tiempoAtencion = (EditText)findViewById(R.id.edt_tiempoAtencion);
        lunes = (CheckBox)findViewById(R.id.checkbox_lunes);
        martes =(CheckBox)findViewById(R.id.checkbox_martes);
        miercoles =(CheckBox)findViewById(R.id.checkbox_miercoles);
        jueves =(CheckBox)findViewById(R.id.checkbox_jueves);
        viernes =(CheckBox)findViewById(R.id.checkbox_viernes);



    }

}
