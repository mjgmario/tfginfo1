package com.example.myapplication.Funcionalidad.LoginPackage.Compartir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ClasesApp.PartyingApp;
import com.example.myapplication.Constantes;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CrearChatActivity extends AppCompatActivity {

    private Button botonCrearChat;
    private ImageButton botonAtras;
    private EditText nombreChat;
    private int id_chat;
    private static final String TAG= "Pagina de crearchat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_chat);
        vincularVistas();

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        botonCrearChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearChat();
            }
        });



    }

    private void crearChat() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INSERT_CREAR_CHAT,
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

                        }
                        else{
                            try {
                                id_chat = jsonObject.getInt("id_chat");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            pasarActividadAnadirGenteChat();
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
                params.put("id_admin", String.valueOf(PartyingApp.getUser().getId()));
                params.put("nombre", nombreChat.getText().toString());
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



    private void pasarActividadAnadirGenteChat() {
        Intent intent= new Intent(this, AnadirGenteChatUsuarios.class);
        intent.putExtra("id_chat",id_chat);
        this.startActivity(intent);
        finish();
    }


    public void vincularVistas() {
        botonAtras = findViewById(R.id.botonAtrasCrearChatNombre);
        botonCrearChat = findViewById(R.id.botonContinuarCrearChatPantalla1);
        nombreChat = findViewById(R.id.editTextNombreCrearChat);
    }

}



