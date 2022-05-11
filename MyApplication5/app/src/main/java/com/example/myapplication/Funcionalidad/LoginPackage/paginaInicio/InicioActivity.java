package com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ClasesApp.Local;
import com.example.myapplication.ClasesApp.Local_User;
import com.example.myapplication.ClasesApp.PartyingApp;
import com.example.myapplication.ClasesApp.Publicacion;
import com.example.myapplication.Constantes;
import com.example.myapplication.Funcionalidad.LoginPackage.Buscador.BuscadorActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.ChatsUsuario.ChatsUsuarioActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Compartir.CompartirActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Perfil.PerfilPublicacionesActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InicioActivity extends AppCompatActivity {

    private static final String TAG= "Pagina de inicio";

    private TextView textoSitiosHasEstado, textoRecomendadoParaTi, TextoSitiosEstadoAmigos, TextoPublicacionesAmigos;
    private RecyclerView recycler_sitios_has_estado , recycler_recomendado_para_ti, recycler_sitios_estado_amigos, recycler_publicaciones_amigos;
    private BottomNavigationView mBottomNavigationView;
    private ArrayList<Local_User> listaSitios ;
    private ArrayList<Local> listaRecomendados;
    private ArrayList<Local> listaSitiosAmigos;
    private ArrayList<Publicacion> listaPublicaciones ;
    private View scroll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        vincularVistas();

        obtenerListaSitios();
        obtenerListaRecomendados();
        obtenerListaSitiosAmigos();
        obtenerListaPublicaciones();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    if (i1 - i3>= 0) {
                        mBottomNavigationView.setVisibility(View.VISIBLE);
                    } else {
                        mBottomNavigationView.setVisibility(View.GONE);
                    }
                }
            });
        }
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    //actualizar
                    break;
                case R.id.buscador:
                    Intent intent5 = new Intent(this, BuscadorActivity.class);
                    startActivity(intent5);
                    break;
                case R.id.compartir:
                    Intent intent2 = new Intent(this, CompartirActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.perfil:
                    Intent intent3 = new Intent(this, PerfilPublicacionesActivity.class);
                    intent3.putExtra("id_user", PartyingApp.getUser().getId());
                    startActivity(intent3);

                    break;
                case R.id.chat:
                   Intent intent4 = new Intent(this, ChatsUsuarioActivity.class);
                    startActivity(intent4);
                    break;

            }


            return false;
        });
        mBottomNavigationView.setSelectedItemId(R.id.home);
    }



    private void obtenerListaSitios() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_SITIOS_USUARIO_HA_ESTADO,
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
                            listaSitios = new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);

                                        Local_User local = new Local_User(
                                                Integer.parseInt(objeto.getString("id_local")),
                                                objeto.getString("fecha"),
                                                objeto.getString("nombre"),
                                                objeto.getString("lat"),
                                                objeto.getString("longi")
                                        );
                                        listaSitios.add(local);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            llenarRecyclerSitiosHasEstado();


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

    private void obtenerListaRecomendados() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_SITIOS_RECOMENDADOS,

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
                            listaRecomendados = new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);

                                        Local local = new Local(
                                                Integer.parseInt(objeto.getString("id_local")),
                                                objeto.getString("nombre"),
                                                objeto.getString("lat"),
                                                objeto.getString("longi")
                                        );


                                        listaRecomendados.add(local);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            llenarRecyclerRecomendadosParaTi();


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
    private void obtenerListaSitiosAmigos() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_SITIOS_HAN_ESTADO_SEGUIDOS,

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
                            listaSitiosAmigos = new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);

                                        Local local = new Local(
                                                Integer.parseInt(objeto.getString("id_local")),
                                                objeto.getString("nombre"),
                                                objeto.getString("lat"),
                                                objeto.getString("longi")
                                        );


                                        listaSitiosAmigos.add(local);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            llenarRecyclerSitiosHanEstadoAmigos();


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
    private void obtenerListaPublicaciones() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_PUBLICACIONES_INICIO,
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
                            listaPublicaciones = new ArrayList<>();
                            try {
                                // Obtener el array del objeto
                                jsonArray = jsonObject.getJSONArray("lista");

                                for(int i=0; i<jsonArray.length(); i++){

                                    try {
                                        JSONObject objeto= jsonArray.getJSONObject(i);
                                        Publicacion publicacion = new Publicacion(
                                                Integer.parseInt(objeto.getString("id")),
                                                objeto.getString("descripcion"),
                                                objeto.getString("fecha")
                                        );
                                        listaPublicaciones.add(publicacion);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

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
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", String.valueOf(PartyingApp.getUser().getId()));
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

    private void llenarRecyclerSitiosHasEstado() {
        SitiosHasEstadoAdapter sitiosAdapter = new SitiosHasEstadoAdapter(this, listaSitios);
        recycler_sitios_has_estado.setAdapter(sitiosAdapter);
        recycler_sitios_has_estado.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
    private void llenarRecyclerRecomendadosParaTi() {
        RecomendadosParaTiAdapterInicio recomendadosParaTi = new RecomendadosParaTiAdapterInicio(this, listaRecomendados );
        recycler_recomendado_para_ti.setAdapter(recomendadosParaTi);
        recycler_recomendado_para_ti.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void llenarRecyclerSitiosHanEstadoAmigos() {
        SitiosHanEstadoAmigosAdapter sitiosHanEstadoAmigosAdapter = new SitiosHanEstadoAmigosAdapter(this, listaSitiosAmigos );
        recycler_sitios_estado_amigos.setAdapter(sitiosHanEstadoAmigosAdapter);
        recycler_sitios_estado_amigos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
    private void llenarRecyclerPublicaciones() {
        PublicacionesAdapterInicio publicacionesAdapter = new PublicacionesAdapterInicio(this, listaPublicaciones );
        recycler_publicaciones_amigos.setAdapter(publicacionesAdapter);
        recycler_publicaciones_amigos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    /*
        private void llenarRecyclerHistorias() {
            listaHistorias = presentador.getHistorias();
            HistoriasAdapter historiasAdapter = new HistoriasAdapter(this, listaHistorias, this );
            historias.setAdapter(historiasAdapter);
            historias.setLayoutManager(new LinearLayoutManager(this));


        }
    */
    public void vincularVistas() {
        recycler_sitios_has_estado = findViewById(R.id.SitiosHasEstadoRecycler);
        recycler_recomendado_para_ti = findViewById(R.id.RecomendadoParaTiRecycler);
        recycler_sitios_estado_amigos = findViewById(R.id.SitiosHanEstadoAmigosRecycler);
        recycler_publicaciones_amigos = findViewById(R.id.PublicacionesRecycler);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        textoSitiosHasEstado = findViewById(R.id.textoSitiosInicio);
        textoRecomendadoParaTi = findViewById(R.id.TextoRecomendadoParaTiInicio);
        TextoSitiosEstadoAmigos = findViewById(R.id.TextoSitiosAmigosInicio);
        TextoPublicacionesAmigos = findViewById(R.id.TextoPublicacionesAmigos);
        scroll = findViewById(R.id.scrollViewInicio);


    }

}
