package com.example.myapplication.Funcionalidad.LoginPackage.ChatsUsuario;

public class Chat_Usuario {
    private int id_chat;
    private String nombre, fecha_creacion, num_no_leidos;

    public Chat_Usuario(int id_chat, String nombre, String fecha_creacion, String num_no_leidos) {
        this.id_chat = id_chat;
        this.nombre = nombre;
        this.fecha_creacion = fecha_creacion;
        this.num_no_leidos = num_no_leidos;
    }

    public int getId_chat() {
        return id_chat;
    }

    public void setId_chat(int id_chat) {
        this.id_chat = id_chat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getNum_no_leidos() {
        return num_no_leidos;
    }

    public void setNum_no_leidos(String num_no_leidos) {
        this.num_no_leidos = num_no_leidos;
    }
}
