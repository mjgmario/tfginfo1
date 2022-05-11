package com.example.myapplication.Funcionalidad.LoginPackage.EventoConcreto;

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
import com.example.myapplication.ClasesApp.PartyingApp;
import com.example.myapplication.Constantes;
import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.PuntuarLocalActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.Usuario_Local;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventoConcretoActivity extends AppCompatActivity {
    private static final String TAG= "Pagina evento";

    private String nombr, fech, descrip, edad_mi, edad_ma, precio_copa, prec;
    private TextView nombre, fecha, descripcion, edad_min, edad_max, precio_copas, precio;
    private RecyclerView recyclerHanEstado;
    private Button voyAIr, opinionButton;
    private ImageButton botonAtras;
    private Integer id_evento;
    private ArrayList<Usuario_Local> lista_usuarios_van_a_ir;
    private Boolean va;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_concreto);
        Intent intent = getIntent();
        id_evento = new Integer(intent.getIntExtra("id_evento", 0));
        vincularVistas();
        opinionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent7= new Intent(view.getContext(), PuntuarEventoActivity.class);
                intent7.putExtra("id_evento",id_evento);
                startActivity(intent7);
            }
        });
        obtenerInfoGeneral();
        obtenerListaUsuariosVan();

        actualizarClicks();
        verSiVaAIr();
        voyAIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(voyAIr.getText().toString().equals("¿Vas a ir?")){
                    insertarUserEvento();
                }
                else{
                    eliminarUserEvento();
                }
            }
        });
    botonAtras.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });


    }

    private void verSiVaAIr() {


        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_SI_USER_VA_A_EVENTO,
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
                                va = jsonObject.getBoolean("va");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(va) {
                                voyAIr.setText("Voy a ir");
                            }else{
                                voyAIr.setText("¿Vas a ir?");

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
                params.put("id_evento", String.valueOf(id_evento));
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

    private void actualizarClicks() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_UPDATE_CLICKS_EVENTO,
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
                params.put("id_evento", String.valueOf(id_evento));
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


    private void insertarUserEvento(){

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INSERT_USER_VA_A_EVENTO,
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
                            voyAIr.setText("Voy a ir");
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
                params.put("id_evento", String.valueOf(id_evento));
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

    private void eliminarUserEvento() {

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_DELETE_FROM_USER_VA_A_EVENTO,

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
                            voyAIr.setText("¿Vas a ir?");
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
                params.put("id_evento", String.valueOf(id_evento));
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
                Request.Method.POST, Constantes.URL_FETCH_INFO_EVENTO_CONCRETO,
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
                                nombr = jsonObject.getString("nombre_evento");
                                fech = jsonObject.getString("fecha");
                                descrip = jsonObject.getString("descrip");
                                edad_mi = jsonObject.getString("edad_min");
                                edad_ma = jsonObject.getString("edad_max");
                                precio_copa = jsonObject.getString("precio_copas");
                                prec = jsonObject.getString("precio");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            nombre.setText(nombr);

                            fecha.setText("Se celebra en " +fech);
                            descripcion.setText(descrip);
                            edad_min.setText("Edad minima: " + edad_mi);
                            edad_max.setText("Edad maxima: " +edad_ma);
                            precio_copas.setText("Precio_copas: " +precio_copa);
                            precio.setText("Precio: " +prec);
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
                params.put("id_evento", String.valueOf(id_evento));
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



    private void obtenerListaUsuariosVan() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_USERS_VAN_A_EVENTO,
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
                                lista_usuarios_van_a_ir = new ArrayList<>();
                                try {
                                    // Obtener el array del objeto
                                    jsonArray = jsonObject.getJSONArray("lista");

                                    for (int i = 0; i < jsonArray.length(); i++) {

                                        try {
                                            JSONObject objeto = jsonArray.getJSONObject(i);
                                            Usuario_Local usuario = new Usuario_Local(
                                                    Integer.parseInt(objeto.getString("id")),
                                                    objeto.getString("nombre"));
                                            lista_usuarios_van_a_ir.add(usuario);
                                        } catch (JSONException e) {
                                            Log.e(TAG, "Error de parsing: " + e.getMessage());
                                        }
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                llenaRecyclerUsuariosVanAIr();
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
                params.put("id_evento", String.valueOf(id_evento));

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


    private void llenaRecyclerUsuariosVanAIr() {
        UsuariosVanAEstarAdapter amigosHanEstadoAdapter = new UsuariosVanAEstarAdapter(this, lista_usuarios_van_a_ir );
        recyclerHanEstado.setAdapter(amigosHanEstadoAdapter);
        recyclerHanEstado.setLayoutManager(new LinearLayoutManager(this));
    }


    public void vincularVistas() {
        voyAIr = findViewById(R.id.botonVoyAIrEventoConcreto);
        nombre = findViewById(R.id.nombre_evento_concreto);
        fecha = findViewById(R.id.fecha_evento_concreto);
        descripcion = findViewById(R.id.descripcion_evento_concreto);
        edad_min = findViewById(R.id.edad_min_evento_concreto);
        edad_max = findViewById(R.id.edad_max_evento_concreto);
        precio_copas = findViewById(R.id.precio_copas_evento_concreto);
        precio = findViewById(R.id.precio_evento_concreto);
        botonAtras = findViewById(R.id.botonAtrasEventoConcreto);
        recyclerHanEstado = findViewById(R.id.recycler_van_a_ir_evento_concreto);
        opinionButton = findViewById(R.id.darOpinionEvento);


    }

}

