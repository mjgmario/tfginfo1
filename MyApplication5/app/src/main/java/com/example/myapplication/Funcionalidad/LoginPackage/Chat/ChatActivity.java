package com.example.myapplication.Funcionalidad.LoginPackage.Chat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Representa un chat particular, viene dado el id_chat, vienen los mensajes
public class ChatActivity extends AppCompatActivity  {
    private static final String TAG= "Pagina mensajes chat";
    private RecyclerView mensajesRecycler;
    private TextView nombreChat;
    private ImageButton botonAtras, botonAnadir;
    private Button  botonEnviar;
    private ArrayList<Mensaje> mensajes;
    private EditText chat_text;
    private int id_chat;
    String nombre_chat;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        vincularVistas();
        Intent intent = getIntent();
        id_chat = intent.getIntExtra("id_chat", 0);
        obtenerMensajes();


        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acabarLLamadas();
                insertActualizacionTablaRegistro();
                finish();
            }
        });
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contenido = chat_text.getText().toString();
                enviarMensaje(contenido);

            }
        });
        botonAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anadirUsuario();

            }
        });

        repetidor.run();

    }

    private void insertActualizacionTablaRegistro() {

        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.UPDATE_REGISTRO_CHAT_USER,
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
                params.put("id_user", String.valueOf(PartyingApp.getUser().getId()));
                params.put("id_chat", String.valueOf(id_chat));
                return params;
            }
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        SingletonChat.getInstance(this).addToRequestQueue(request);


    }

    private void acabarLLamadas() {
        handler.removeCallbacks(repetidor);

    }

    private Runnable repetidor = new Runnable() {
        @Override
        public void run() {
            obtenerMensajes2();
            handler.postDelayed(this, 5000);
        }
    };
    private void anadirUsuario() {
        Intent intent = new Intent(this, AnadirUsuarioActivity.class);
        intent.putExtra("id_chat", id_chat);
        startActivity(intent);
    }

    private void obtenerMensajes2(){

        // RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_MENSAJES_CHAT,
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

                            JSONArray jsonArray= null;
                            mensajes = new ArrayList<>();
                            try {

                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);

                                        Mensaje mensaje = new Mensaje(
                                                Integer.parseInt(objeto.getString("id_mensaje")),
                                                objeto.getString("contenido"),
                                                objeto.getString("fecha"),
                                                objeto.getString("nombre")
                                        );
                                        mensajes.add(mensaje);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            llenarRecyclerMensajes();


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
                return params;
            }
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        SingletonChat.getInstance(this).addToRequestQueue(request);
        // Añadir petición a la cola
        //requestQueue.add(request);

    }
    private void obtenerMensajes() {
        // RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_MENSAJES_CHAT,
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
                            
                            JSONArray jsonArray= null;
                            mensajes = new ArrayList<>();
                            try {
                                nombre_chat = jsonObject.getString("nombre");
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);

                                        Mensaje mensaje = new Mensaje(
                                                Integer.parseInt(objeto.getString("id_mensaje")),
                                                objeto.getString("contenido"),
                                                objeto.getString("fecha"),
                                                objeto.getString("nombre")
                                        );
                                        mensajes.add(mensaje);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            nombreChat.setText(nombre_chat);
                            llenarRecyclerMensajes();


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
                return params;
            }
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        // Añadir petición a la cola
       //requestQueue.add(request);
        SingletonChat.getInstance(this).addToRequestQueue(request);


    }

    private void enviarMensaje(String contenido) {
        // RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_ANADIR_MENSAJE_CHAT,
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
                            //actualizar el recycler
                            chat_text.setText("");
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
                params.put("id_autor", String.valueOf(PartyingApp.getUser().getId()));
                params.put("id_chat", String.valueOf(id_chat));
                params.put("contenido", contenido);

                return params;
            }
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        // Añadir petición a la cola
        //requestQueue.add(request);
        SingletonChat.getInstance(this).addToRequestQueue(request);


    }


    private void llenarRecyclerMensajes() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        MensajesAdapter mensajesAdapter = new MensajesAdapter(this, mensajes );
        mensajesRecycler.setAdapter(mensajesAdapter);
        mensajesRecycler.setLayoutManager(linearLayoutManager);
    }

    public void vincularVistas() {
        botonAnadir = findViewById(R.id.botonaAnadirUsuarioChatUsuario);
        nombreChat = findViewById(R.id.nombre_chat_chat_usuario);
        botonAtras = findViewById(R.id.botonAtrasChatUsuario);
        botonEnviar = findViewById(R.id.botonEnviarChatUsuario);
        chat_text = findViewById(R.id.editTextMensajeChatUsuario);
        mensajesRecycler = findViewById(R.id.recyclerMensajesChatUsuario);
    }

}

