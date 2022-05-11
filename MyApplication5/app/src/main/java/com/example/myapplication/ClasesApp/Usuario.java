package com.example.myapplication.ClasesApp;

public class Usuario {
    private String nombre, contrasena;
    private Integer id;
    private static Usuario user;
    public static Usuario getInstance(String nombre, String contrasena, Integer id) {
        if(user == null){
            user = new Usuario(nombre, contrasena, id);
            return user;
        }else return user;
    }

    private Usuario(String nombre, String contrasena, Integer id) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario user) {
        Usuario.user = user;
    }
}
