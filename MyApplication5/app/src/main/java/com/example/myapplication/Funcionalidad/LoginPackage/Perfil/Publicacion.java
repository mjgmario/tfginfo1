package com.example.myapplication.Funcionalidad.LoginPackage.Perfil;

public class Publicacion {
    private int id_publicacion;
    private String descripcion;

    public Publicacion(int id_publicacion, String descripcion) {
        this.id_publicacion = id_publicacion;
        this.descripcion = descripcion;
    }

    public int getId_publicacion() {
        return id_publicacion;
    }

    public void setId_publicacion(int id_publicacion) {
        this.id_publicacion = id_publicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
