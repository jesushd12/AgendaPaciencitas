package com.example.jesus.agendapaciencitas;

/**
 * Created by Jesus on 1/24/2016.
 */
public class URL {

    private static String chikitoIP = "192.168.0.107";

    public static String rutaCitasPorFecha = "http://"+chikitoIP+":8080/API_Citas/public/Medicos/cita/"; // +{fecha}/{username}
    public static String rutaPacientesPorId = "http://"+chikitoIP+":8080/API_Citas/public/Pacientes/porId/"; //+ idPaciente
    public static String rutaMisPacientes = "http://"+chikitoIP+":8080/API_Citas/public/Medicos/get_CPacientes/"; //+ username

    public static String rutaHistoriaPaciente = "http://"+chikitoIP+":8080/API_Citas/public/Pacientes/historias/"; // +idpaciente/ username

    public static String rutaVerificarLogin = "http://"+chikitoIP+":8080/API_Citas/public/login";
    public static String rutaObtenerUsuario = "http://"+chikitoIP+":8080/API_Citas/public/get_user/"; // +username


}
