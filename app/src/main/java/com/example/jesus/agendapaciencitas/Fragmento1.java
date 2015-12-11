package com.example.jesus.agendapaciencitas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesus on 12/9/2015.
 */
public class Fragmento1 extends Fragment {
    private CaldroidFragment caldroidFragment;
    HashMap<String, List<String>>pacientesCitas;
    List<String> listaPacientes;
    ExpandableListView exp_list;
    CitasAdapter citasAdapter;


    public void setCustomResourceForDate(){
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(formato.parse("19-12-2015"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date blueDate = cal.getTime();


        cal = Calendar.getInstance();
        try {
            cal.setTime(formato.parse("20-12-2015"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date blueDate2 = cal.getTime();



        if (caldroidFragment!=null){
            caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_sky_blue,blueDate);
            caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_sky_blue,blueDate2);


            caldroidFragment.setTextColorForDate(R.color.caldroid_white,blueDate);
            caldroidFragment.setTextColorForDate(R.color.caldroid_white,blueDate2);

        }

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento1_layout,container,false);

        exp_list = (ExpandableListView)v.findViewById(R.id.exp_list);

        pacientesCitas = DataProvider.getInfo();
        listaPacientes = new ArrayList<String>(pacientesCitas.keySet());
        citasAdapter = new CitasAdapter(getContext(), pacientesCitas,listaPacientes);
        exp_list.setAdapter(citasAdapter);


        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        caldroidFragment = new CaldroidFragment();
        if(savedInstanceState !=null){
            caldroidFragment.restoreStatesFromKey(savedInstanceState,"CALDROID_SAVED_STATE");

        }else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);
            caldroidFragment.setArguments(args);
        }
            setCustomResourceForDate();

            FragmentTransaction t = this.getFragmentManager().beginTransaction();
            t.replace(R.id.caldroide,caldroidFragment);
            t.commit();

        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                String fecha_seleccionada = formatter.format(date);
                pacientesCitas = DataProvider.getInfo(fecha_seleccionada);
                listaPacientes = new ArrayList<String>(pacientesCitas.keySet());
                citasAdapter = new CitasAdapter(getContext(), pacientesCitas,listaPacientes);
                exp_list.setAdapter(citasAdapter);

            }
        };
        caldroidFragment.setCaldroidListener(listener);

        return v;
    }
}
