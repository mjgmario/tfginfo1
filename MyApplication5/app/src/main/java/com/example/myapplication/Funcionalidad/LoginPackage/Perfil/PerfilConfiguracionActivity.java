package com.example.myapplication.Funcionalidad.LoginPackage.Perfil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.R;

public class PerfilConfiguracionActivity extends AppCompatActivity {
    private Button editarDesc;
    private String contenidodes;
    private EditText editTextDesc;
    private ImageButton botonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_configuracion);
        vincularVistas();
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editarDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void vincularVistas() {
        editarDesc = findViewById(R.id.botonEditarDescripcion);
        editTextDesc = findViewById(R.id.editTextDescripcionPerfil);
        botonAtras = findViewById(R.id.botonAtrasCambiarPerfil);


    }
}