package com.example.jesus.agendapaciencitas;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jesus on 1/24/2016.
 */
public class Cita {
    private int id;
    private Date fecha;
    private String hora;
    private int idPaciente;
    private int idMedico;
    private String tratamiento;
    private String diagnostico;
    private String motivo;
    public ArrayList<String> todosLosMotivos = new ArrayList<>();
    private String nombrePaciente;
    private String apellidoPaciente;

    public Cita() {
    }

    public Cita(int id, Date fecha, String hora, int idPaciente, int idMedico, String tratamiento, String diagnostico, String motivo){
        this.diagnostico = diagnostico;
        this.fecha = fecha;
        this.hora = hora;
        this.id = id;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.motivo = motivo;
        this.tratamiento = tratamiento;
    }


    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        todosLosMotivos.add(motivo);
        this.motivo = motivo;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public ArrayList<String> getTodosLosMotivos() {
        return todosLosMotivos;
    }

    public void setTodosLosMotivos(ArrayList<String> todosLosMotivos) {
        this.todosLosMotivos = todosLosMotivos;
    }

    public String getApellidoPaciente() {
        return apellidoPaciente;
    }

    public void setApellidoPaciente(String apellidoPaciente) {
        this.apellidoPaciente = apellidoPaciente;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }
}
