package com.example.myapplication.Funcionalidad.LoginPackage.EventoConcreto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ClasesApp.PartyingApp;
import com.example.myapplication.Constantes;
import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.LocalesActivity;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PuntuarEventoActivity extends AppCompatActivity {
    private static final String TAG = "Pagina puntuar";
    private ImageButton botonAtras;
    private Integer id_evento;
    private TextView errorText;
    private EditText textPuntuacion;
    private Button continuarButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuar_evento);
        Intent intent = getIntent();
        id_evento = new Integer(intent.getIntExtra("id_evento", 0));

        vincularVistas();
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                darPuntuacion();
            }
        });
    }

    private void darPuntuacion() {

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INSERT_PUNTUACION_EVENTO,

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
                            Boolean muchas = null;
                            try {
                                muchas = jsonObject.getBoolean("muchas");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(muchas) {
                                errorText.setVisibility(View.VISIBLE);
                                errorText.setText("Has publicado más de 1 opinion");
                            }
                            else{
                                pasarActividadEvento();
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
                params.put("id_user", String.valueOf(PartyingApp.getUser().getId()));
                params.put("puntuacion", textPuntuacion.getText().toString());

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

    private void pasarActividadEvento() {
        Intent intent= new Intent(this, EventoConcretoActivity.class);
        intent.putExtra("id_evento",id_evento);
        this.startActivity(intent);
        finish();

    }

    public void vincularVistas() {
        botonAtras = findViewById(R.id.botonAtrasEventoPuntuacion);
        textPuntuacion = findViewById(R.id.editTextPuntuacionEvento);
        errorText = findViewById(R.id.TextErrorPuntuacionEvento);
        continuarButton = findViewById(R.id.botonContinuarPuntuarEvento);


    }
}