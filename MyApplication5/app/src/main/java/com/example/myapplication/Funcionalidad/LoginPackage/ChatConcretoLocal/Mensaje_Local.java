package com.example.myapplication.Funcionalidad.LoginPackage.ChatConcretoLocal;

public class Mensaje_Local {
    private Integer id_mensaje, id_autor;
    private String contenido, nombreAutor, fecha;

    public Mensaje_Local(Integer id_mensaje, Integer id_autor, String contenido, String nombreAutor, String fecha) {
        this.id_mensaje = id_mensaje;
        this.id_autor = id_autor;
        this.contenido = contenido;
        this.nombreAutor = nombreAutor;
        this.fecha = fecha;
    }

    public Integer getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(Integer id_mensaje) {
        this.id_mensaje = id_mensaje;
    }

    public Integer getId_autor() {
        return id_autor;
    }

    public void setId_autor(Integer id_autor) {
        this.id_autor = id_autor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

