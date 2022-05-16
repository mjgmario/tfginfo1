package com.example.myapplication.Funcionalidad.LoginPackage.Perfil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio.InicioActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PerfilLugaresActivity extends AppCompatActivity {
    private static final String TAG= "Pagina public lugares";
    private RecyclerView recycler_lugares;
    private TextView nombre_text, descripcion_text,botonSeguidores, botonSiguiendo ;
    private String nombre, descripcion;
    private ImageButton botonPublicaciones;
    private BottomNavigationView mBottomNavigationView;
    private ArrayList<Lugares> listaLugares;
    private Integer id_user;
private Boolean tiene_foto;
    private String id_foto_perfil;
    private ImageView foto_perfil_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_lugares);
        Intent intent = getIntent();
        id_user = new Integer(intent.getIntExtra("id_user", 0));
        vincularVistas();

                /*notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        */ // ver si seguir teniendo notificaciones

        obtenerListaLugares();
        botonSeguidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarActividadSeguidores();
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

    private void pasarActividadSeguidores() {
        Intent intent= new Intent(this, PerfilSeguidoresActivity.class);
        intent.putExtra("id_user",id_user);
        startActivity(intent);
    }

    private void pasarActividadPublicaciones() {
        Intent intent= new Intent(this, PerfilPublicacionesActivity.class);
        intent.putExtra("id_user",id_user);
        startActivity(intent);
    }
    private void obtenerListaLugares() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_LUGARES_USUARIO,

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
                                tiene_foto = jsonObject.getBoolean("tiene_foto");
                                nombre = jsonObject.getString("nombre");
                                descripcion = jsonObject.getString("descripcion");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(tiene_foto){

                                try {
                                    id_foto_perfil = jsonObject.getString("id_foto_perfil");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                fijarFoto(id_foto_perfil);
                            }


                            JSONArray jsonArray= null;
                            listaLugares = new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);

                                        Lugares lugar = new Lugares(
                                                Integer.parseInt(objeto.getString("id_local")),
                                                objeto.getString("fecha"),
                                                objeto.getString("nombre"));
                                        listaLugares.add(lugar);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            nombre_text.setText(nombre);
                            descripcion_text.setText("Descripcion: " + descripcion);
                            llenarRecyclerLugares();


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
    private void fijarFoto(String id_foto_perfil) {
        Picasso.get().load(Constantes.URL_ROOT_PERFIL + id_foto_perfil )
                .fit()
                .into(foto_perfil_view, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d(TAG, e.toString());

                    }
                });
    }


    private void llenarRecyclerLugares() {
        LugaresAdapter lugaresAdapter = new LugaresAdapter(this, listaLugares );
        recycler_lugares.setAdapter(lugaresAdapter);
        recycler_lugares.setLayoutManager(new LinearLayoutManager(this));
    }
    public void vincularVistas() {
        recycler_lugares = findViewById(R.id.recycler_lugares);
        nombre_text = findViewById(R.id.nombreUserPerfilLugares);
        descripcion_text = findViewById(R.id.descripcionPerfilLugares);
        botonSiguiendo = findViewById(R.id.botonSiguiendoPerfilLugares);
        botonSeguidores = findViewById(R.id.botonSeguidoresPerfilLugares);
        botonPublicaciones = findViewById(R.id.botonPublicacionesPerfilLugares);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_Perfil_Lugares);
        foto_perfil_view = findViewById(R.id.imagenPerfilLugaresPerfil);

    }
}