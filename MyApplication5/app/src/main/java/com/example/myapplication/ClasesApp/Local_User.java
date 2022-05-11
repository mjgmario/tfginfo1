package com.example.myapplication.ClasesApp;

public class Local_User {
    private int id;
    private String fecha, nombre, lat, longi;

    public Local_User(int id, String fecha, String nombre, String lat, String longi) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.lat = lat;
        this.longi = longi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }
}
