package com.example.myapplication.Funcionalidad.LoginPackage.Chat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Constantes;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AnadirUsuarioActivity extends AppCompatActivity {
    private static final String TAG= "Pagina mensajes chat";
    private Button botonAnadir ;
    private ImageButton botonAtras;
    private EditText usuario_nombre;
    private TextView tarjetaError;
    private int id_chat;

    //Ver como autocompletar y poniendo todos los contactos
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_chat);
        vincularVistas();
        Intent intent = getIntent();
        id_chat = intent.getIntExtra("id_chat", 0);


        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        botonAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreUsuario = usuario_nombre.getText().toString();
                anadirUsuario(nombreUsuario);

            }
        });

    }

    private void anadirUsuario(String nombreUsuario) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INSERT_USUARIO_CHAT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Boolean error = null;
                        try{
                            error = jsonObject.getBoolean("error");

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(error){
                            String mensaje = null;
                            try {
                                mensaje = jsonObject.getString("message");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d(TAG, "Error Respuesta en JSON: " + mensaje);
                            tarjetaError.setText(mensaje);
                            falloLogin();
                        }
                        else{

                            exitoLogin();


                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_chat", String.valueOf(id_chat));
                params.put("nombre", nombreUsuario);

                return params;
            }
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        // Añadir petición a la cola
        requestQueue.add(request);


    }

    public void exitoLogin(){
        Toast.makeText(this, "Usuario anadido", Toast.LENGTH_SHORT).show(); //Correcto
        finish();
    }
    public void falloLogin(){
        tarjetaError.setVisibility(View.VISIBLE);
        usuario_nombre.setText("");
    }




    public void vincularVistas() {
        botonAtras =  findViewById(R.id.botonAtrasAnadirChat);
        botonAnadir = findViewById(R.id.botonAnadirUsuarioChatAnadir);
        usuario_nombre = findViewById(R.id.nombreusuarioAnadirChat);
        tarjetaError = findViewById(R.id.mensajeErrorAnadirChat);
    }

}

