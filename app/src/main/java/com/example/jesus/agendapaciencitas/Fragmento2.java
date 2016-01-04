package com.example.jesus.agendapaciencitas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jesus on 12/9/2015.
 */
public class Fragmento2 extends Fragment {
    ListView misPacientes;
    ArrayAdapter<String> listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento2_layout,container,false);
        misPacientes = (ListView)v.findViewById(R.id.listaMisPacientes);

        String[] values = new String[] { "Rosita",
                "Maria",
                "Melanie",
                "Diosa",
                "Norkys",
                "Adriana",
                "JLO",
                "Valentina"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
        misPacientes.setAdapter(adapter);
        misPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) misPacientes.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getContext(),
                        "  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });


        return v;
    }
}
