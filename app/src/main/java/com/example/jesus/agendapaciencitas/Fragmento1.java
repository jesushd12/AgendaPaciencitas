package com.example.jesus.agendapaciencitas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jesus on 12/9/2015.
 */
public class Fragmento1 extends Fragment {
    private CaldroidFragment caldroidFragment;
    SimpleDateFormat formatoFechaServidor = new SimpleDateFormat("yyyy-MM-dd");


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

        cal = Calendar.getInstance();
        try {
            cal.setTime(formato.parse("18-12-2015"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date blueDate3 = cal.getTime();



        if (caldroidFragment!=null){

            caldroidFragment.setTextColorForDate(R.color.caldroid_light_red,blueDate);
            caldroidFragment.setTextColorForDate(R.color.caldroid_light_red,blueDate2);
            caldroidFragment.setTextColorForDate(R.color.caldroid_sky_blue,blueDate3);

        }

    }
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmento1_layout,container,false);
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
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
            caldroidFragment.setArguments(args);
        }
            setCustomResourceForDate();

            FragmentTransaction t = this.getFragmentManager().beginTransaction();
            t.replace(R.id.caldroide,caldroidFragment);
            t.commit();

        final CaldroidListener listener = new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {
                String fecha_dia = formatoFechaServidor.format(date);
                Intent intentCitas = new Intent(getContext(),CitasActivity.class);
                intentCitas.putExtra("fechaseleccionada",fecha_dia);
                startActivity(intentCitas);

            }
        };
        caldroidFragment.setCaldroidListener(listener);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
