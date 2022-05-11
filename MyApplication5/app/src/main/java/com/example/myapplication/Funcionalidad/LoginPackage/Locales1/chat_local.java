package com.example.myapplication.Funcionalidad.LoginPackage.Locales1;

public class chat_local {
    private int id_chat, id_local;
    private String nombre;


    public chat_local(int id_chat, int id_local, String nombre) {
        this.id_chat = id_chat;
        this.id_local = id_local;
        this.nombre = nombre;
    }

    public int getId_chat() {
        return id_chat;
    }

    public void setId_chat(int id_chat) {
        this.id_chat = id_chat;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
