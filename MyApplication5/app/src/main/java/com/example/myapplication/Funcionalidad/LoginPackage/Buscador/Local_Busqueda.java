package com.example.myapplication.Funcionalidad.LoginPackage.Buscador;

public class Local_Busqueda {
    private String nombre;
    private int id;

    public Local_Busqueda(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
