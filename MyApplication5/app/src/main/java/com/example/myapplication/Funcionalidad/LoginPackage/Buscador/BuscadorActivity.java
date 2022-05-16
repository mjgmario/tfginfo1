package com.example.myapplication.Funcionalidad.LoginPackage.Buscador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.example.myapplication.ClasesApp.PartyingApp;
import com.example.myapplication.ClasesApp.Publicacion;
import com.example.myapplication.Constantes;
import com.example.myapplication.Funcionalidad.LoginPackage.ChatConcretoLocal.Mensaje_Local;
import com.example.myapplication.Funcionalidad.LoginPackage.ChatsUsuario.ChatsUsuarioActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Compartir.CompartirActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.LocalesActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Perfil.PerfilPublicacionesActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio.InicioActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio.PublicacionesAdapterInicio;
import com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio.RecomendadosParaTiAdapterInicio;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuscadorActivity extends AppCompatActivity{
    private static final String TAG= "Pagina buscador";
    private BottomNavigationView mBottomNavigationView;
    private RecyclerView recyclerresultadosBusqueda;

    private EditText textBusqueda, textFiltroEdad, textFiltroPrecio;
    private TextView noHayElementos;
    private RecyclerView recyclerLocalesMasPopulares, recyclerPublicacionesMasPopulares;
    private ImageButton botonBusqueda, botonFiltros;
    private ArrayList<Publicacion> listaPublicacionesDestacadas;
    private ArrayList<Local> listaLocalesDestacados;
    private ArrayList<Local_Busqueda> listaLocalesBusqueda = new ArrayList<>();
    private LinearLayout layoutInicial, layoutFiltros;
    private Boolean pantalla_primera = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscador_activity);
        vincularVistas();
        obtenerPublicacionesMasDestacadas();
        obtenerLocalesMasDestacados();
        botonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // implementar va o si ya ha pulsado que pueda cambiar
                ejecutarBusqueda(textBusqueda.getText().toString());
            }
        });
        textBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerresultadosBusqueda.getVisibility() == View.VISIBLE){
                    recyclerresultadosBusqueda.setVisibility(View.GONE  );
                    if(pantalla_primera){
                        layoutInicial.setVisibility(View.VISIBLE);
                    }
                    else{
                        layoutFiltros.setVisibility(view.VISIBLE);
                    }
                }
                else{
                    recyclerresultadosBusqueda.setVisibility(View.VISIBLE);
                    if(pantalla_primera){
                        layoutInicial.setVisibility(View.GONE);
                    }
                    else{
                        layoutFiltros.setVisibility(View.GONE);
                    }
                }
            }
        });
        botonFiltros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(layoutInicial.getVisibility() == View.VISIBLE){
                    pantalla_primera = false;
                    layoutInicial.setVisibility(View.GONE);
                    layoutFiltros.setVisibility(View.VISIBLE);

                }
                else{
                    pantalla_primera = true;
                    layoutFiltros.setVisibility(View.GONE);
                    layoutInicial.setVisibility(View.VISIBLE);
                }
            }
        });

        textBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e(TAG, "User input: " + charSequence);


                // query the database based on the user input
                String edad, precio;
                edad = textFiltroEdad.getText().toString();
                precio = textFiltroPrecio.getText().toString();


                if(edad.equals("")){
                    edad = "1000";
                }
                if(precio.equals("")){
                    precio= "1000";
                }
                Log.d(TAG, precio);
                Log.d(TAG, edad);

                ejecutarBusquedaAutocomplete(charSequence.toString(), edad, precio);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent6 = new Intent(this, InicioActivity.class);
                    startActivity(intent6);
                    break;
                case R.id.buscador:

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
        mBottomNavigationView.setSelectedItemId(R.id.buscador);



    }
    public void ejecutarBusquedaAutocomplete(String contenido, String edad, String precio) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_AUTOCOMPLETE_BUSCADOR ,
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
                        else {
                            Boolean hay = null;
                            try {
                                hay = jsonObject.getBoolean("hay");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (hay) {
                                listaLocalesBusqueda = new ArrayList<>();

                                JSONArray jsonArray= null;
                                try {
                                    // Obtener el array del objeto
                                    jsonArray = jsonObject.getJSONArray("lista");

                                    for(int i=0; i<jsonArray.length(); i++){

                                        try {

                                            JSONObject objeto= jsonArray.getJSONObject(i);
                                            Local_Busqueda local = new Local_Busqueda(
                                                    objeto.getString("nombre"),
                                                    Integer.parseInt(objeto.getString("id_local"))
                                            );
                                            listaLocalesBusqueda.add(local);
                                        } catch (JSONException e) {
                                            Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                        }
                                    }
                                    llenarRecyclerBusqueda();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }else{
                                listaLocalesBusqueda = new ArrayList<>();
                                llenarRecyclerBusqueda();

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
                params.put("contenido", contenido);
                params.put("edad", edad);
                params.put("precio", precio);

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
    private void obtenerLocalesMasDestacados() {
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
                            listaLocalesDestacados = new ArrayList<>();
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


                                        listaLocalesDestacados.add(local);

                                    } catch (JSONException e) {
                                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            llenaRecyclerLocalesMasPopulares();
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

    private void obtenerPublicacionesMasDestacadas() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_PUBLICACIONES_MAS_DESTACADAS,
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
                            listaPublicacionesDestacadas = new ArrayList<>();
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
                                        listaPublicacionesDestacadas.add(publicacion);

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

    private void ejecutarBusqueda(String contenido) {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_LOCALES_BUSQUEDA,
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
                        else {
                            Boolean hay = null;
                            try {
                                hay = jsonObject.getBoolean("hay");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (hay) {
                                Integer id_local;
                                try {
                                    JSONObject jsonelem = jsonObject.getJSONObject("elem");
                                    id_local = new Integer(jsonelem.getString("id_local"));
                                    pasarActividadLocal(id_local);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                noHayElementos.setVisibility(View.VISIBLE);
                                textBusqueda.setText("");
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
                params.put("nombre_local", contenido);

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
    private void llenarRecyclerBusqueda() {
        LocalesAdapterBusqueda sitiosAdapter = new LocalesAdapterBusqueda(this, listaLocalesBusqueda);
        recyclerresultadosBusqueda.setAdapter(sitiosAdapter);
        recyclerresultadosBusqueda.setLayoutManager(new LinearLayoutManager(this));

    }

    private void pasarActividadLocal(Integer id_local) {
        Intent intent= new Intent(this, LocalesActivity.class);
        intent.putExtra("idLocal",id_local);
        startActivity(intent);

    }

    private void llenaRecyclerLocalesMasPopulares() {
        RecomendadosParaTiAdapterInicio amigosHanEstadoAdapter = new RecomendadosParaTiAdapterInicio(this, listaLocalesDestacados);
        recyclerLocalesMasPopulares.setAdapter(amigosHanEstadoAdapter);
        recyclerLocalesMasPopulares.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void llenarRecyclerPublicaciones() {
        PublicacionesAdapterInicio publicacionesAdapter = new PublicacionesAdapterInicio(this, listaPublicacionesDestacadas );
        recyclerPublicacionesMasPopulares.setAdapter(publicacionesAdapter);
        recyclerPublicacionesMasPopulares.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }



    public void vincularVistas() {
        noHayElementos = findViewById(R.id.NoresultadosBusqueda);
        textBusqueda  = findViewById(R.id.textBusqueda);
        recyclerLocalesMasPopulares = findViewById(R.id.recyclerLocalesPaginaBusqueda);
        recyclerPublicacionesMasPopulares = findViewById(R.id.recyclerPublicacionesPaginaBusqueda);
        botonBusqueda = findViewById(R.id.BotonBuscar);
        mBottomNavigationView = findViewById(R.id.bottom_navigation_Buscador);
        recyclerresultadosBusqueda = findViewById(R.id.recyclerBusquedaLocales);
        botonFiltros = findViewById(R.id.BotonFiltrosBuscador);
        layoutInicial = findViewById(R.id.layoutinicialbusqueda);
        layoutFiltros = findViewById(R.id.layoutfiltros);
        textFiltroEdad = findViewById(R.id.EditTextEdad);
        textFiltroPrecio = findViewById(R.id.editTextPrecioBusqueda);


    }

}

