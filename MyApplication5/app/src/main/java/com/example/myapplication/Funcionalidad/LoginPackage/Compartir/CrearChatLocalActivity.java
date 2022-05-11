package com.example.myapplication.Funcionalidad.LoginPackage.Compartir;

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
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CrearChatLocalActivity extends AppCompatActivity {
    private ImageButton botonAtras;
    private EditText nombreChat_local, nombre_local, descripcion;
    private TextView errorText;
    private Button finalizarButton;
    private static final String TAG= "Pagina de crearchat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_chat_local);
        vincularVistas();

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        finalizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearChatLocal();
            }
        });



    }

    private void crearChatLocal() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);

        // Nueva petición JSONObject
        StringRequest request = new StringRequest(
                Request.Method.POST, Constantes.URL_INSERT_CHAT_LOCAL,
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
                            Boolean noExisteLocal = null;
                            try{
                                noExisteLocal = jsonObject.getBoolean("noExiste");

                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if(noExisteLocal){
                                errorText.setVisibility(View.VISIBLE);
                                errorText.setText("No existe local con ese nombre");
                            }else
                                {
                                    Boolean ExisteNombre = null;
                                    try{
                                        ExisteNombre = jsonObject.getBoolean("ExisteNombre");

                                    }catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if(ExisteNombre){
                                        errorText.setVisibility(View.VISIBLE);
                                        errorText.setText("Ya existe en el local un chat con ese nombre");
                                    }else {

                                        pasarActividadCompartir();
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
                params.put("id_user", String.valueOf(PartyingApp.getUser().getId()));
                params.put("nombre_chat", nombreChat_local.getText().toString());
                params.put("nombre_local", nombre_local.getText().toString());
                params.put("descripcion", descripcion.getText().toString());
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

        botonAtras = findViewById(R.id.botonAtrasCrearChatNombre);
        finalizarButton = findViewById(R.id.botonFinalizarCrearChatLocal);
        descripcion = findViewById(R.id.editTextDescripcionChatLocal);
        nombre_local = findViewById(R.id.editTextNombreLocalAsociado);
        nombreChat_local = findViewById(R.id.editTextNombreCrearChatLocal);
        errorText = findViewById(R.id.TextErrorChatLocal);
    }



    private void pasarActividadCompartir() {
        Intent intent= new Intent(this, CompartirActivity.class);
        this.startActivity(intent);
        finish();
        }
    }