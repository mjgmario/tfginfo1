package com.example.myapplication.Funcionalidad.LoginPackage.ChatConcretoLocal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParticiparChatLocalConcretoActivity extends AppCompatActivity {

    private static final String TAG= "Pagina chat local";
    private String nombre, descrip, num_amigos;
    private TextView nombreChat, amigos_van_a_ir, descripcion;
    private Button botonSi, botonNo;
    private int id_chat_local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participar_chat_concreto);
        vincularVistas();
        Intent intent = getIntent();
        id_chat_local = intent.getIntExtra("id_chat_local", 0);
        obtenerInfoChatLocal();
        botonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        botonSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anadirChat();
            }
        });


    }

    private void obtenerInfoChatLocal() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_INFO_PARTICIPAR_CHAT_CONCRETO,

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
                                nombre = jsonObject.getString("nombre");
                                descrip = jsonObject.getString("descripcion");
                                num_amigos = jsonObject.getString("num_amigos");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            nombreChat.setText(nombre);
                            descripcion.setText("Descripcion: " + descrip);
                            amigos_van_a_ir.setText("Amigos que están en el chat: " + num_amigos);
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
                params.put("id_chat_local", String.valueOf(id_chat_local));
                params.put("id_user", String.valueOf(PartyingApp.getUser().getId()));

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


    private void anadirChat() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_ANADIR_USER_CHAT_LOCAL,
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
                            pasarActividad();

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
                params.put("id_chat_local", String.valueOf(id_chat_local));
                params.put("id_user", String.valueOf(PartyingApp.getUser().getId()));
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

    private void pasarActividad() {
        Intent intent= new Intent(this, ChatConcretoLocalActivity.class);
        intent.putExtra("id_chat_local",id_chat_local);
        startActivity(intent);
        finish();
    }


    public void vincularVistas() {

        botonSi = findViewById(R.id.boton_si_participar_chat_concreto);
        botonNo = findViewById(R.id.boton_no_participar_chat_concreto);
        nombreChat = findViewById(R.id.nombre_chat_participar_concreto);
        amigos_van_a_ir = findViewById(R.id.textoAmigos);
        descripcion = findViewById(R.id.descripcion_participar_concreto);
    }
}