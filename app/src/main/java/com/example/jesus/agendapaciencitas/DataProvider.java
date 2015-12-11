package com.example.jesus.agendapaciencitas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jesus on 12/9/2015.
 */
public class DataProvider {

    public static HashMap<String, List<String>> getInfo(){
        HashMap<String,List<String>> detallePaciente = new HashMap<String,List<String>>();
        List<String> motivos = new ArrayList<String>();
        motivos.add("Gripe");
        List<String> motivos2 = new ArrayList<String>();
        motivos2.add("Codo fracturado");
        detallePaciente.put("Melanie",motivos);
        detallePaciente.put("Maria",motivos2);

        return detallePaciente;
    }
    public static HashMap<String, List<String>> getInfo(String fecha){
        HashMap<String,List<String>> detallePaciente = new HashMap<String,List<String>>();
        List<String> motivos = new ArrayList<String>();
        List<String> motivos2 = new ArrayList<String>();

        switch(fecha){
            case "20-12-2015":
                motivos.add("Herpes bucal");
                motivos2.add("VPH");
                detallePaciente.put("Aurora",motivos);
                detallePaciente.put("Martina",motivos2);
                detallePaciente.put("Diosa",motivos2);
                detallePaciente.put("Rosita",motivos2);
                break;
            case "19-12-2015":
                motivos.add("Herpes bucal");
                motivos2.add("VPH");
                detallePaciente.put("Maria",motivos);
                detallePaciente.put("Patricia",motivos2);
                detallePaciente.put("Diosa",motivos2);
                detallePaciente.put("Lacatira",motivos2);

                break;
        }


        return detallePaciente;
    }
}
