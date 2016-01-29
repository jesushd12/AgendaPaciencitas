package com.example.jesus.agendapaciencitas;

/**
 * Created by Jesus on 1/24/2016.
 */
public class Paciente {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String tlfCasa;
    private String tlfCelular;

    public Paciente() {
    }

    public Paciente(String apellido, String cedula, String correo, String direccion, int id, String nombre, String tlfCasa, String tlfCelular) {
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.direccion = direccion;
        this.id = id;
        this.nombre = nombre;
        this.tlfCasa = tlfCasa;
        this.tlfCelular = tlfCelular;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTlfCasa() {
        return tlfCasa;
    }

    public void setTlfCasa(String tlfCasa) {
        this.tlfCasa = tlfCasa;
    }

    public String getTlfCelular() {
        return tlfCelular;
    }

    public void setTlfCelular(String tlfCelular) {
        this.tlfCelular = tlfCelular;
    }
}
