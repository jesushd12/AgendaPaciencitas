package com.example.jesus.agendapaciencitas;

/**
 * Created by Jesus on 1/24/2016.
 */
public class URL {

    public static String rutaCitasPorFecha = "http://192.168.1.9:80/API_Cita/public/Medicos/cita/"; // +{fecha}/{username}
    public static String rutaPacientesPorId = "http://192.168.1.9:80/API_Cita/public/Pacientes/porId/"; //+ idPaciente
    public static String rutaMisPacientes = "http://192.168.1.9:80/API_Cita/public/Medicos/get_CPacientes/"; //+ username

    public static String rutaHistoriaPaciente = "http://192.168.1.9:80/API_Cita/public/Pacientes/historias/"; // +idpaciente/ username

    public static String rutaVerificarLogin = "http://192.168.1.9:80/API_Cita/public/login";
    public static String rutaObtenerUsuario = "http://192.168.1.9:80/API_Cita/public/get_user/"; // +username


}
