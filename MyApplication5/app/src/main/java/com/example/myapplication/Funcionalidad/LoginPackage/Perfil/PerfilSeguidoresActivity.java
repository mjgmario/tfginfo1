package com.example.myapplication.Funcionalidad.LoginPackage.Perfil;

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
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Constantes;
import com.example.myapplication.Funcionalidad.LoginPackage.Buscador.BuscadorActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.ChatsUsuario.ChatsUsuarioActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Compartir.CompartirActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.AmigosHanEstadoAdapter;
import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.Usuario_Local;
import com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio.InicioActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PerfilSeguidoresActivity extends AppCompatActivity {
    private static final String TAG= "Pagina de seguidores";
    private RecyclerView recycler_seguidores;
    private TextView nombre_text, descripcion_text, botonSiguiendo;
    private String nombre, descripcion;
    private ImageButton botonPublicaciones, botonLugares;
    private BottomNavigationView mBottomNavigationView;
    private ArrayList<Usuario_Local> listaUsuarios;
    private Integer id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_seguidores);
        Intent intent = getIntent();
        id_user = new Integer(intent.getIntExtra("id_user", 0));
        vincularVistas();

                /*notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        */ // ver si seguir teniendo notificaciones

        obtenerSeguidores();
        botonLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarActividadLugares();
            }
        });
        botonSiguiendo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarActividadSiguiendo();

            }
        });
        botonPublicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarActividadPublicaciones();
            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent4 = new Intent(this, InicioActivity.class);
                    startActivity(intent4);                    break;
                case R.id.buscador:
                    Intent intent5 = new Intent(this, BuscadorActivity.class);
                    startActivity(intent5);
                    break;
                case R.id.compartir:
                    Intent intent2 = new Intent(this, CompartirActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.perfil:


                    break;
                case R.id.chat:
                    Intent intent8 = new Intent(this, ChatsUsuarioActivity.class);
                    startActivity(intent8);
                    break;

            }


            return false;
        });
        mBottomNavigationView.setSelectedItemId(R.id.perfil);
    }

    private void pasarActividadSiguiendo() {
        Intent intent= new Intent(this, PerfilSeguidosActivity.class);
        intent.putExtra("id_user",id_user);
        startActivity(intent);
    }

    private void pasarActividadLugares() {
        Intent intent= new Intent(this, PerfilLugaresActivity.class);
        intent.putExtra("id_user",id_user);
        startActivity(intent);
    }

    private void pasarActividadPublicaciones() {
        Intent intent= new Intent(this, PerfilPublicacionesActivity.class);
        intent.putExtra("id_user",id_user);
        startActivity(intent);
    }
    private void obtenerSeguidores() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_SEGUIDORES_USUARIO_PERFIL,
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
                                descripcion = jsonObject.getString("descripcion");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            JSONArray jsonArray= null;
                            listaUsuarios = new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);
                                        Usuario_Local user = new Usuario_Local(
                                                Integer.parseInt(objeto.getString("id")),
                                                objeto.getString("nombre"));
                                        listaUsuarios.add(user);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            nombre_text.setText(nombre);
                            descripcion_text.setText("Descripcion: "+ descripcion);
                            llenarRecyclerSeguidores();


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
                params.put("id_user", String.valueOf(id_user));
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


    private void llenarRecyclerSeguidores() {
        AmigosHanEstadoAdapter usuariosAdapter = new AmigosHanEstadoAdapter(this, listaUsuarios );
        recycler_seguidores.setAdapter(usuariosAdapter);
        recycler_seguidores.setLayoutManager(new LinearLayoutManager(this));
    }
    public void vincularVistas() {
        recycler_seguidores = findViewById(R.id.recyclerSeguidores);
        nombre_text = findViewById(R.id.nombreUserPerfilSeguidores);
        descripcion_text = findViewById(R.id.descripcionUserPerfilSeguidores);
        botonSiguiendo = findViewById(R.id.botonSiguiendoPerfilSeguidores);
        botonLugares = findViewById(R.id.botonLugaresPerfilSeguidores);
        botonPublicaciones = findViewById(R.id.botonPublicacionesPerfilSeguidores);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_Perfil_Seguidores);

    }
}