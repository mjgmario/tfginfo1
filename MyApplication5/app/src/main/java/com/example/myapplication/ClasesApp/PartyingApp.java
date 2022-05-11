package com.example.myapplication.ClasesApp;

public class PartyingApp {
    private static Usuario user = null;
    private static PartyingApp app = null;
    public static PartyingApp getApp(){
        if(app == null){
            app = new PartyingApp();
        }
        return app;
    }
    public void setUser(String nombre, String constrasena, Integer id){
        user = Usuario.getInstance(nombre, constrasena, id);
    }

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario user) {
        PartyingApp.user = user;
    }

    public static void setApp(PartyingApp app) {
        PartyingApp.app = app;
    }
}
