package com.example.myapplication.Funcionalidad.LoginPackage.ChatsUsuario;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.myapplication.Funcionalidad.LoginPackage.Buscador.BuscadorActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Compartir.CompartirActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Perfil.PerfilPublicacionesActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio.InicioActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatsUsuarioActivity extends AppCompatActivity {
    // Vamos a poner al principio solo los chats normales
    //funcionalidad anadir usuario hacer
    private RecyclerView chats_recycler;
    private BottomNavigationView mBottomNavigationView;
    private ArrayList<Chat_Usuario> listaChats;
    private ImageButton botonAtras;
    private static final String TAG= "Pagina de chats";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        vincularVistas();

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        obtenerListaChats();
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
                    Intent intent4 = new Intent(this, CompartirActivity.class);
                    startActivity(intent4);
                    break;
                case R.id.perfil:
                    Intent intent3 = new Intent(this, PerfilPublicacionesActivity.class);
                    intent3.putExtra("id_user", PartyingApp.getUser().getId());
                    startActivity(intent3);

                    break;
                case R.id.chat:

                    break;

            }


            return false;
        });
        mBottomNavigationView.setSelectedItemId(R.id.chat);

    }


    private void obtenerListaChats() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_CHATS_USUARIO,
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
                            JSONArray jsonArrayNoLeidos= null;

                            listaChats = new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");
                                jsonArrayNoLeidos = jsonObject.getJSONArray("numeros");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);
                                        JSONObject objeto2= jsonArrayNoLeidos.getJSONObject(i);

                                        Chat_Usuario chat = new Chat_Usuario(
                                                Integer.parseInt(objeto.getString("id_chat")),
                                                objeto.getString("nombre"),
                                                objeto.getString("fecha_ultimo_mensaje"),
                                                objeto2.getString("contador")

                                        );


                                        listaChats.add(chat);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            llenarRecyclerChats();
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


    private void llenarRecyclerChats() {
        ChatsAdapter chatsAdapter = new ChatsAdapter(this, listaChats );
        chats_recycler.setAdapter(chatsAdapter);
        chats_recycler.setLayoutManager(new LinearLayoutManager(this));
    }
    public void vincularVistas() {
        chats_recycler = findViewById(R.id.recyclerchatsusuario);
        botonAtras = findViewById(R.id.botonAtrasChatsUsuario);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_Lista_chats_user);
    }

}



