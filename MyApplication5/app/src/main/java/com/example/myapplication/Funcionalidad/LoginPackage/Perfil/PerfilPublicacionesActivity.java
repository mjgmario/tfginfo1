package com.example.myapplication.Funcionalidad.LoginPackage.Perfil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ClasesApp.PartyingApp;
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

public class PerfilPublicacionesActivity extends AppCompatActivity {
    private static final String TAG= "Pagina de publicaciones";

    private RecyclerView recycler_publicaciones;
    private TextView nombre_text, descripcion_text, botonSeguidores, botonSiguiendo;
    private String nombre, descripcion;
    private ImageButton botonLugares;
    private Button seguirButton;
    private BottomNavigationView mBottomNavigationView;
    private ArrayList<Publicacion> listaPublicaciones;
    private Integer id_user;
    private Boolean tiene_foto, lo_sigue;
    private String id_foto_perfil;
    private ImageView foto_perfil_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_publicaciones);
        Intent intent = getIntent();
        id_user = new Integer(intent.getIntExtra("id_user", 0));
        vincularVistas();


        obtenerListaPublicaciones();

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
        botonLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarActividadLugares();
            }
        });
        if(id_user == PartyingApp.getUser().getId()){
            seguirButton.setText("Tu perfil");
            seguirButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pasarAConfiguracionPerfil();

                }
            });
        }
        else{
            verSiEsSeguidor();
            seguirButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(seguirButton.getText().toString().equals("Seguir")){
                        seguirUsuario();
                    }
                    else{
                        dejarDeSeguirUsuario();
                    }
                }
            });
        }
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

    private void dejarDeSeguirUsuario() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_DELETE_SEGUIDOR_SEGUIDO,
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

                            seguirButton.setText("Seguir");
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id_seguidor", String.valueOf(PartyingApp.getUser().getId()));
                params.put("id_seguido", String.valueOf(id_user));

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

    private void seguirUsuario() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INSERT_SEGUIDOR_SEGUIDO,
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

                                seguirButton.setText("Siguiendo");
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id_seguidor", String.valueOf(PartyingApp.getUser().getId()));
                params.put("id_seguido", String.valueOf(id_user));

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

    private void verSiEsSeguidor() {


        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_SI_USER_SIGUE_A,
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
                                lo_sigue = jsonObject.getBoolean("sigue");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            // ver sin tiene foto de perfil
                            if(lo_sigue){
                                seguirButton.setText("Siguiendo");
                            }
                            else{
                                seguirButton.setText("Seguir");

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
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id_seguidor", String.valueOf(PartyingApp.getUser().getId()));
                params.put("id_seguido", String.valueOf(id_user));

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

    private void pasarAConfiguracionPerfil() {
        Intent intent9 = new Intent(this, PerfilConfiguracionActivity.class);
        startActivity(intent9);
    }

    private void pasarActividadLugares() {
        Intent intent= new Intent(this, PerfilLugaresActivity.class);
        intent.putExtra("id_user",id_user);
        startActivity(intent);
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


    private void obtenerListaPublicaciones() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_PUBLICACIONES_USUARIO,
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

                            // ver sin tiene foto de perfil
                            if(tiene_foto){

                                try {
                                    id_foto_perfil = jsonObject.getString("id_foto_perfil");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                fijarFoto(id_foto_perfil);
                            }

                            JSONArray jsonArray= null;
                            listaPublicaciones = new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);

                                        Publicacion publi = new Publicacion(
                                                Integer.parseInt(objeto.getString("id")),
                                                objeto.getString("descripcion"));
                                        listaPublicaciones.add(publi);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            nombre_text.setText(nombre);
                            descripcion_text.setText("Descripcion: " + descripcion);
                            llenarRecyclerPublicaciones();


                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
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


    private void llenarRecyclerPublicaciones() {
        PublicacionesAdapter publicacionesAdapter = new PublicacionesAdapter(this, listaPublicaciones );
        recycler_publicaciones.setAdapter(publicacionesAdapter);
        recycler_publicaciones.setLayoutManager(new GridLayoutManager(this, 3));
    }
    public void vincularVistas() {
        recycler_publicaciones = findViewById(R.id.recycler_publicaciones);
        nombre_text = findViewById(R.id.nombreUserPerfilPublicaciones);
        descripcion_text = findViewById(R.id.descripcionUserPerfilPublicaciones);
        botonSiguiendo = findViewById(R.id.botonSiguiendoPerfilPublicaciones);
        botonSeguidores = findViewById(R.id.botonSeguidoresPerfilPublicaciones);
        botonLugares = findViewById(R.id.botonLugaresPerfilPublicaciones);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_Perfil_Publicaciones);
        seguirButton = findViewById(R.id.botonSeguirPerfilPublicaciones);
        foto_perfil_view = findViewById(R.id.imagenPerfilPublicaciones);

    }
}