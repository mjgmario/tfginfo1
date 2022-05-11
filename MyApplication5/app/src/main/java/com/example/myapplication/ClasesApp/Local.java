package com.example.myapplication.ClasesApp;

public class Local {
    private int id;
    private String  nombre, lat, longi;

    public Local(int id, String nombre, String lat, String longi) {
        this.id = id;
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
