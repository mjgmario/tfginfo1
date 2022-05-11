package com.example.myapplication.Funcionalidad.LoginPackage.Locales1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
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
import com.example.myapplication.ClasesApp.PartyingApp;
import com.example.myapplication.Constantes;
import com.example.myapplication.Funcionalidad.LoginPackage.Buscador.BuscadorActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.ChatsUsuario.ChatsUsuarioActivity;
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

public class LocalesActivity extends AppCompatActivity {
    private static final String TAG= "Pagina local";

    private RecyclerView recycler_eventos, recycler_relaciones, recycler_chats_local, recycler_amigos_que_han_ido;
    private BottomNavigationView mBottomNavigationView;
    private TextView nombre_local_tv, descrip_textview;
    private ImageView imagen;
    private Button he_Estado, ver_todoChat, botonPuntuar;
    private ArrayList<Eventos> listaEventos;
    private ArrayList<Usuario_Local> listaRelaciones;
    private ArrayList<Usuario_Local> lista_amigos_que_han_ido;
    private Integer id_local;
    private ImageButton botonMaps;
    private String latitud, longi;
    private Boolean haEstado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locales);
        Intent intent = getIntent();
        id_local = new Integer(intent.getIntExtra("idLocal", 0));
        vincularVistas();
        obtenerInfoGeneral();
        obtenerListaRelaciones();
        obtenerLista_amigos_que_han_ido();
        obtenerListaEventos();
        actualizarClicks();
        botonPuntuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7= new Intent(view.getContext(), PuntuarLocalActivity.class);
                intent7.putExtra("idLocal",id_local);
                startActivity(intent7);
            }
        });
        botonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitud + "," +longi);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                view.getContext().startActivity(mapIntent);
            }
        });
        versiHaEstadoEnLocal();
        he_Estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(he_Estado.getText().toString().equals("¿Has estado?")){
                    insertUserHeestadoLocal();
                }
            }
        });
        ver_todoChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            pasarActividadLista();

            }
        });
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




    }

    private void insertUserHeestadoLocal() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INSERT_USER_HA_ESTADO_LOCAL,
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


                                he_Estado.setText("He estado");

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

    private void versiHaEstadoEnLocal() {


        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_SI_USER_HA_IDO_LOCAL,
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
                            try {
                                haEstado = jsonObject.getBoolean("va");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(haEstado) {
                                he_Estado.setText("He estado");
                            }else{
                                he_Estado.setText("¿Has estado?");

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
                params.put("id_user", String.valueOf(PartyingApp.getUser().getId()));
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

    private void pasarActividadLista() {
        Intent intent7 = new Intent(this, MostrarChatsLocalActivity.class);
        intent7.putExtra("idLocal", id_local);
        startActivity(intent7);
    }
    private void actualizarClicks() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_UPDATE_CLICKS_LOCAL,
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

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }) {
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

    private void obtenerInfoGeneral() {

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INFO_LOCAL,

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
                            String nombre = "", desc = "";
                            try {
                                nombre = jsonObject.getString("nombre");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                desc = jsonObject.getString("descripcion");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                latitud = jsonObject.getString("latitud");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                longi = jsonObject.getString("longitud");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                                nombre_local_tv.setText(nombre);

                            descrip_textview.setText(desc);
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

    private void obtenerListaRelaciones() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        Log.d(TAG, "estoy dentro 1");

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_RELACIONES_LOCAL,

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
                            Log.d(TAG, "error");

                            String mensaje = null;
                            try {
                                mensaje = jsonObject.getString("message");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d(TAG, "Error Respuesta en JSON: " + mensaje);

                        } else {
                            Log.d(TAG, "no error");


                                JSONArray jsonArray = null;
                                listaRelaciones = new ArrayList<>();
                                try {
                                    // Obtener el array del objeto
                                    jsonArray = jsonObject.getJSONArray("lista");

                                    for (int i = 0; i < jsonArray.length(); i++) {


                                            JSONObject objeto = jsonArray.getJSONObject(i);
                                            Usuario_Local usuario = new Usuario_Local(
                                                    Integer.parseInt(objeto.getString("id")),
                                                    objeto.getString("nombre"));
                                            listaRelaciones.add(usuario);
                                        Log.d(TAG, "id " +String.valueOf(Integer.parseInt(objeto.getString("id"))));

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                llenarRecyclerRelaciones();

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

    private void obtenerLista_amigos_que_han_ido() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_AMIGOS_HAN_IDO_LOCAL,

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
                            {

                                JSONArray jsonArray = null;
                                lista_amigos_que_han_ido = new ArrayList<>();
                                try {
                                    // Obtener el array del objeto
                                    jsonArray = jsonObject.getJSONArray("lista");

                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        try {
                                            JSONObject objeto = jsonArray.getJSONObject(i);
                                            Usuario_Local usuario = new Usuario_Local(
                                                    Integer.parseInt(objeto.getString("id")),
                                                    objeto.getString("nombre"));
                                            lista_amigos_que_han_ido.add(usuario);
                                        } catch (JSONException e) {
                                            Log.e(TAG, "Error de parsing: " + e.getMessage());
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                llenarRecyclerAmigosHanEstado();
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
                params.put("id_local", String.valueOf(id_local));
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


    private void obtenerListaEventos() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.FETCH_EVENTOS_LOCAL,
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
                                listaEventos= new ArrayList<>();
                                try {
                                    // Obtener el array del objeto
                                    jsonArray = jsonObject.getJSONArray("lista");

                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        try {
                                            JSONObject objeto = jsonArray.getJSONObject(i);
                                            Eventos evento = new Eventos(Integer.parseInt(objeto.getString("id_evento")),
                                                    objeto.getString("nombre_evento"),
                                                    objeto.getString("fecha"),
                                                    objeto.getString("descrip"),
                                                    objeto.getString("edad_min"),
                                                    objeto.getString("edad_max"),
                                                    objeto.getString("precio_copas"),
                                                    objeto.getString("precio"));
                                            listaEventos.add(evento);
                                        } catch (JSONException e) {
                                            Log.e(TAG, "Error de parsing: " + e.getMessage());
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            Log.d(TAG, "En eventos");

                            llenarRecyclerEventos();

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





    private void llenarRecyclerAmigosHanEstado() {
        AmigosHanEstadoAdapter amigosHanEstadoAdapter = new AmigosHanEstadoAdapter(this, lista_amigos_que_han_ido );
        recycler_amigos_que_han_ido.setAdapter(amigosHanEstadoAdapter);
        recycler_amigos_que_han_ido.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    private void llenarRecyclerEventos() {
        EventosAdapter eventosAdapter = new EventosAdapter(this, listaEventos );
        recycler_eventos.setAdapter(eventosAdapter);
        recycler_eventos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
    private void llenarRecyclerRelaciones() {
        RelacionesAdapter relacionesAdapter = new RelacionesAdapter(this, listaRelaciones );
        recycler_relaciones.setAdapter(relacionesAdapter);
        recycler_relaciones.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    public void vincularVistas() {
        ver_todoChat = findViewById(R.id.botonVerTodoChat);
        recycler_eventos = findViewById(R.id.recyclerEventos);
        recycler_relaciones = findViewById(R.id.recyclerRelaciones);
        recycler_amigos_que_han_ido = findViewById(R.id.recyclerAmigosQueHanEstado);
        descrip_textview = findViewById(R.id.Descripcion_Pagina_Local);
        nombre_local_tv = findViewById(R.id.nombreLocalText);
        he_Estado = findViewById(R.id.botonHeEstado);
        imagen = findViewById(R.id.imagenLocal);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_locales);
        botonMaps = findViewById(R.id.iconoMapsLocalesActivity);
        botonPuntuar = findViewById(R.id.darOpinionBotonLocal);

    }

}

