package com.example.myapplication.Funcionalidad.LoginPackage.Perfil;


public class Lugares {
    private int id_local;
    private String fecha, nombre;

    public Lugares(int id_local, String fecha, String nombre) {
        this.id_local = id_local;
        this.fecha = fecha;
        this.nombre = nombre;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
