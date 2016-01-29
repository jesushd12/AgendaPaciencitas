package com.example.jesus.agendapaciencitas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Jesus on 12/9/2015.
 */
public class Fragmento3 extends Fragment {
    ListView miConfiguracion;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento3_layout,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Configuracion");
        miConfiguracion = (ListView)v.findViewById(R.id.listaConfiguracion);

        String[] values = new String[] { "Notificaciones",
                "Horario"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
        miConfiguracion.setAdapter(adapter);
        miConfiguracion.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) miConfiguracion.getItemAtPosition(position);
                if (position == 1) {
                    Intent intent = new Intent(getActivity(),ConfiguracionActivity.class);
                    startActivity(intent);
                }


            }

        });

        return v;
    }
}
