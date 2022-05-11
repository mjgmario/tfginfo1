package com.example.myapplication.Funcionalidad.LoginPackage.ChatConcretoLocal;

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
import com.example.myapplication.Funcionalidad.LoginPackage.Chat.SingletonChat;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatConcretoLocalActivity extends AppCompatActivity  {
    private static final String TAG= "Pagina mensajes chat";
    private RecyclerView mensajesRecycler;
    private TextView nombreChat;
    private ImageButton botonAtras;
    private Button  botonEnviar;
    private ArrayList<Mensaje_Local> mensajes;
    private EditText chat_text;
    private Integer id_chat_local;
    private String nombre_chat_string;
    private Handler handler = new Handler();

    //ver nombre chat
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_concreto_local);
        vincularVistas();
        Intent intent = getIntent();
        id_chat_local = new Integer(intent.getIntExtra("id_chat_local", 0));
        obtenerMensajes();


        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acabarLLamadas();
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
        repetidor.run();



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
    private void obtenerMensajes2(){

        // RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.FETCH_MENSAJES_CHAT_LOCAL,
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
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);

                                        Mensaje_Local mensaje = new Mensaje_Local(
                                                Integer.parseInt(objeto.getString("id_mensaje")),
                                                Integer.parseInt(objeto.getString("id")),
                                                objeto.getString("contenido"),
                                                objeto.getString("nombre"),
                                                objeto.getString("fecha")
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
                params.put("id_chat_local", String.valueOf(id_chat_local));
                return params;
            }
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        // Añadir petición a la cola
        // requestQueue.add(request);
        SingletonChatLocal.getInstance(this).addToRequestQueue(request);


    }
    private void obtenerMensajes() {
        // RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.FETCH_MENSAJES_CHAT_LOCAL,
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
                                nombre_chat_string = jsonObject.getString("nombre_chat");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            JSONArray jsonArray= null;
                            mensajes = new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);

                                        Mensaje_Local mensaje = new Mensaje_Local(
                                                Integer.parseInt(objeto.getString("id_mensaje")),
                                                Integer.parseInt(objeto.getString("id")),
                                                objeto.getString("contenido"),
                                                objeto.getString("nombre"),
                                                objeto.getString("fecha")
                                        );
                                        mensajes.add(mensaje);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            nombreChat.setText(nombre_chat_string);
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
                params.put("id_chat_local", String.valueOf(id_chat_local));
                return params;
            }
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        // Añadir petición a la cola
        // requestQueue.add(request);

        SingletonChatLocal.getInstance(this).addToRequestQueue(request);

    }

    private void enviarMensaje(String contenido) {
        // RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INSERT_MENSAJE_CHAT_LOCAL,
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
                params.put("id_chat_local", String.valueOf(id_chat_local));
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
        // requestQueue.add(request);
        SingletonChatLocal.getInstance(this).addToRequestQueue(request);


    }


    private void llenarRecyclerMensajes() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        MensajeLocalAdapter mensajesAdapter = new MensajeLocalAdapter(this, mensajes );
        mensajesRecycler.setAdapter(mensajesAdapter);
        mensajesRecycler.setLayoutManager(linearLayoutManager);
    }

    public void vincularVistas() {
        nombreChat = findViewById(R.id.nombre_chat_local_concreto);
        botonAtras = findViewById(R.id.botonAtrasChatConcreto);
        botonEnviar = findViewById(R.id.botonEnviarMensajeChatConcreto);
        chat_text = findViewById(R.id.editTextMensajeChatConcreto);
        mensajesRecycler = findViewById(R.id.recyclerListaMensajesChatConcreto);
    }

}

