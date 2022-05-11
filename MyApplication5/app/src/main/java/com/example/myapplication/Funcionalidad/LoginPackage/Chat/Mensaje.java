package com.example.myapplication.Funcionalidad.LoginPackage.Chat;

public class Mensaje {
    private int id_mensaje;
    private String contenido, fecha, nombre_autor;

    public Mensaje(int id_mensaje, String contenido, String fecha, String nombre_autor) {
        this.id_mensaje = id_mensaje;
        this.contenido = contenido;
        this.fecha = fecha;
        this.nombre_autor = nombre_autor;
    }


    public int getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre_autor() {
        return nombre_autor;
    }

    public void setNombre_autor(String nombre_autor) {
        this.nombre_autor = nombre_autor;
    }
}
