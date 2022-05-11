package com.example.myapplication.Funcionalidad.LoginPackage.Compartir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.myapplication.ClasesApp.PartyingApp;
import com.example.myapplication.Funcionalidad.LoginPackage.Buscador.BuscadorActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.ChatsUsuario.ChatsUsuarioActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Perfil.PerfilPublicacionesActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio.InicioActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CompartirActivity extends AppCompatActivity {
    // Vamos a poner al principio solo los chats normales
    //funcionalidad anadir usuario hacer
    private ImageButton botonAtras;
    private Button  botonNuevaPublicacion, botonNuevoChat, botonNuevoChatLocal;
    private BottomNavigationView mBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartir);
        vincularVistas();

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        botonNuevaPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarACompartirPublicacion();

            }
        });
        botonNuevoChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarACrearChat();
            }
        });
        botonNuevoChatLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarACrearChatLocal();
            }
        });
        /*notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        */ // ver si seguir teniendo notificaciones

        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent6= new Intent(this, InicioActivity.class);
                    startActivity(intent6);
                    break;
                case R.id.buscador:
                    Intent intent5 = new Intent(this, BuscadorActivity.class);
                    startActivity(intent5);
                    break;
                case R.id.compartir:

                    break;
                case R.id.perfil:
                    Intent intent3 = new Intent(this, PerfilPublicacionesActivity.class);
                    intent3.putExtra("id_user", PartyingApp.getUser().getId());
                    startActivity(intent3);

                    break;
                case R.id.chat:
                    Intent intent4 = new Intent(this, ChatsUsuarioActivity.class);
                    startActivity(intent4);
                    break;

            }


            return false;
        });
        mBottomNavigationView.setSelectedItemId(R.id.compartir);


    }

    private void pasarACrearChatLocal() {
        Intent intent= new Intent(this, CrearChatLocalActivity.class);
        startActivity(intent);
    }

    private void pasarACrearChat() {
        Intent intent= new Intent(this, CrearChatActivity.class);
        startActivity(intent);
    }

    private void pasarACompartirPublicacion() {
        Intent intent= new Intent(this, CompartirPublicacionActivity.class);
        startActivity(intent);
    }

    public void vincularVistas() {
        botonNuevoChatLocal = findViewById(R.id.BotonNuevoChatLocal);
        botonNuevaPublicacion = findViewById(R.id.botonNuevaPublicacion);
        botonAtras = findViewById(R.id.botonAtrasCompartir);
        botonNuevoChat = findViewById(R.id.botonNuevoChat);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_compartir);

    }

}



