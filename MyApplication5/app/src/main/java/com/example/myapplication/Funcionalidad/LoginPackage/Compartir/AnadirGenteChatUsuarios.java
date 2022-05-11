package com.example.myapplication.Funcionalidad.LoginPackage.Compartir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnadirGenteChatUsuarios extends AppCompatActivity {
    private Button botonAnadirUsuario;
    private Button botonFinalizar;
    private EditText nombreUsuario;
    private RecyclerView recyclerUsuarioEstan;
    private TextView errorText;
    private int id_chat;
    private static final String TAG= "Pagina de crearchat";
    private ArrayList<String> listaUsuarios = new ArrayList<>();
    private NombresUsuariosAdapter usuariosAdapter;
    private ImageButton botonAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_gente_chat_usuarios);
        vincularVistas();

        Intent intent = getIntent();
        id_chat = intent.getIntExtra("id_chat", 0);
        usuariosAdapter = new NombresUsuariosAdapter(this, listaUsuarios );
        llenarRecyclerUsuarios();

        botonFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarAFin();

            }
        });
        botonAnadirUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anadirUsuario(nombreUsuario.getText().toString());
            }
        });

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarChat();
                finish();
            }
        });



    }

    private void eliminarChat() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_DELETE_CHAT_CREAR_CHAT,
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
        requestQueue.add(request);
    }


    private void pasarAFin() {
        Intent intent= new Intent(this, CompartirActivity.class);
        this.startActivity(intent);
        finish();
    }

    private void anadirUsuario(String contenido) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INSERT_USER_CHAT,
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

                        Boolean error = null, noExiste = null, esta = null;
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
                            try{
                                noExiste = jsonObject.getBoolean("noExiste");
                                esta = jsonObject.getBoolean("esta");

                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(noExiste){
                                errorText.setVisibility(View.VISIBLE);
                                errorText.setText("El usuario no existe");
                                nombreUsuario.setText("");
                            }
                            else{
                                if(esta){
                                    errorText.setVisibility(View.VISIBLE);
                                    errorText.setText("El usuario ya esta en el chat");
                                    nombreUsuario.setText("");

                                }
                                else{
                                    errorText.setVisibility(View.GONE);
                                    errorText.setText("");
                                    listaUsuarios.add(contenido);
                                    usuariosAdapter.notifyDataSetChanged();

                                }
                            }


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
                params.put("nombre", contenido);
                return params;
            }
        };

        // Añadir petición a la cola
        requestQueue.add(request);
    }

    private void llenarRecyclerUsuarios() {
        recyclerUsuarioEstan.setAdapter(usuariosAdapter);
        recyclerUsuarioEstan.setLayoutManager(new LinearLayoutManager(this));
    }

    public void vincularVistas() {
        botonAnadirUsuario = findViewById(R.id.botonAnadirUsuarioAnadirGente);
        botonFinalizar = findViewById(R.id.botonFinalizarChatAnadirGente);
        nombreUsuario = findViewById(R.id.editTextAnadirUsuarioAnadirGente);
        recyclerUsuarioEstan = findViewById(R.id.recyclerUsuariosAnadirGente);
        errorText = findViewById(R.id.TextFalloAnadirUsuarioAnadirGente);
        botonAtras = findViewById(R.id.botonAtrasCompartirChatUsuario);
    }

}



