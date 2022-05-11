package com.example.myapplication.Funcionalidad.LoginPackage.PublicacionConcreta;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.myapplication.Funcionalidad.LoginPackage.Perfil.PerfilPublicacionesActivity;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PublicacionConcretaActivity extends AppCompatActivity {
    private static final String TAG = "Pagina de publicaciones";
    private TextView nombre_text, descripcion_text, fecha_text;
    private String nombre, descripcion, fecha;
    private ImageButton imagenUsuario, botonAtras;
    private Integer id_publicacion, id_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_concreta);
        Intent intent = getIntent();
        id_publicacion = new Integer(intent.getIntExtra("id_publicacion", 0));
        vincularVistas();


        obtenerInfoPublicacion();
        actualizarClicks();
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imagenUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pasarAlPerfil();

            }
        });



    }

    private void actualizarClicks() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_UPDATE_CLICKS_PUBLICACION,
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
                params.put("id_foto", String.valueOf(id_publicacion));
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

    private void pasarAlPerfil() {
        Intent intent = new Intent(this, PerfilPublicacionesActivity.class);
        intent.putExtra("id_user", id_user);
        startActivity(intent);
    }


    private void obtenerInfoPublicacion() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_FETCH_INFO_PUBLICACION,
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
                                id_user = new Integer(jsonObject.getInt("id_user"));
                                fecha = jsonObject.getString("fecha");
                                nombre = jsonObject.getString("nombre");
                                descripcion = jsonObject.getString("descripcion");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            nombre_text.setText(nombre);
                            descripcion_text.setText(descripcion);
                            fecha_text.setText(fecha);

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
                params.put("id_publicacion", String.valueOf(id_publicacion));
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


    public void vincularVistas() {
        imagenUsuario = findViewById(R.id.imagenPerfilPublicacionConcreta);
        botonAtras = findViewById(R.id.botonAtrasPublicacionConcreta);
        nombre_text = findViewById(R.id.nombreUserPublicacionConcreta);
        descripcion_text = findViewById(R.id.descripcionPublicacionConcreta);
        fecha_text = findViewById(R.id.fechaPublicacionPublicacionConcreta);
    }
}