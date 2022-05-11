package com.example.myapplication.Funcionalidad.LoginPackage.Locales1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Constantes;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MostrarChatsLocalActivity extends AppCompatActivity {
    private static final String TAG= "Pagina chats local";

    private ArrayList<chat_local> listaChatsLocal;
    private RecyclerView  recycler_chats_local;
    private int id_local;
    private ImageButton botonAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_chats_local);
        Intent intent = getIntent();
        id_local = new Integer(intent.getIntExtra("idLocal", 0));
        vincularVistas();
        obtenerListaChats_Local();
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }




    private void obtenerListaChats_Local() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_CHATS_LOCAL,

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
                        try {
                            error = jsonObject.getBoolean("error");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (error) {
                            String mensaje = null;
                            try {
                                mensaje = jsonObject.getString("message");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d(TAG, "Error Respuesta en JSON: " + mensaje);

                        } else {


                            JSONArray jsonArray = null;
                            listaChatsLocal= new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    try {
                                        JSONObject objeto = jsonArray.getJSONObject(i);
                                        chat_local chat_loc = new chat_local(
                                                Integer.parseInt(objeto.getString("id_chat_local")),
                                                id_local,
                                                objeto.getString("nombre_chat"));
                                        listaChatsLocal.add(chat_loc);
                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: " + e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d(TAG, "En chat");

                            llenarRecyclerChatsLocal();
                            Log.d(TAG, "despues de chat");

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
                params.put("id_local", String.valueOf(id_local));
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



    private void llenarRecyclerChatsLocal() {
        ChatsLocalAdapter chatsLocalAdapter = new ChatsLocalAdapter(this, listaChatsLocal );
        recycler_chats_local.setAdapter(chatsLocalAdapter);
        recycler_chats_local.setLayoutManager(new LinearLayoutManager(this));
    }


    public void vincularVistas() {
        recycler_chats_local = findViewById(R.id.recycler_chats_local);
        botonAtras = findViewById(R.id.botonAtrasListaChatsLocal);

    }

}