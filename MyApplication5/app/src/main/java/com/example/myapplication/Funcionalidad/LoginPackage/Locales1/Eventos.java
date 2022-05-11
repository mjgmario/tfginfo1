package com.example.myapplication.Funcionalidad.LoginPackage.Locales1;

public class Eventos {
    private int id_evento;
    private String nombre, fecha, descripcion, edad_min, edad_max, precio_copas, precio;

    public Eventos(int id_evento, String nombre, String fecha, String descripcion, String edad_min, String edad_max, String precio_copas, String precio) {
        this.id_evento = id_evento;
        this.nombre = nombre;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.edad_min = edad_min;
        this.edad_max = edad_max;
        this.precio_copas = precio_copas;
        this.precio = precio;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEdad_min() {
        return edad_min;
    }

    public void setEdad_min(String edad_min) {
        this.edad_min = edad_min;
    }

    public String getEdad_max() {
        return edad_max;
    }

    public void setEdad_max(String edad_max) {
        this.edad_max = edad_max;
    }

    public String getPrecio_copas() {
        return precio_copas;
    }

    public void setPrecio_copas(String precio_copas) {
        this.precio_copas = precio_copas;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
