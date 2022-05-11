package com.example.myapplication.Funcionalidad.LoginPackage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ClasesApp.PartyingApp;
import com.example.myapplication.ClasesApp.Usuario;
import com.example.myapplication.Constantes;
import com.example.myapplication.Funcionalidad.LoginPackage.Registro.PantallaRegistroActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio.InicioActivity;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG= "Login";

    private EditText mUsername, mPassword;
    private CardView tarjetaBoton;
    private Button botonRegistrar;
    private TextView errorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getIntent();


        vincularVistas();
        tarjetaBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ver valores que falten
                validarLogin(mUsername.getText().toString(), mPassword.getText().toString());
            }
        });
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarActividad();

            }
        });
    }

    private void pasarActividad() {
        Intent intent = new Intent(this, PantallaRegistroActivity.class);
        startActivity(intent);
    }

    private void validarLogin(String nombre, String contrasena) {
        if (nombre.equals("") || contrasena.equals("")) {
            errorView.setText("Rellena todos los campos");
            falloLogin();
        } else {
            findViewById(R.id.tarjeta2).setVisibility(View.INVISIBLE);

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            // Nueva petición JSONObject
            StringRequest request = new StringRequest(
                    Request.Method.POST, Constantes.URL_LOGIN,
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
                            JSONArray jsonArray = null;
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
                                errorView.setText(mensaje);
                                falloLogin();

                                Log.d(TAG, "Error Respuesta en JSON: " + mensaje);

                            } else {
                                Integer id = null;
                                try {
                                    id = jsonObject.getInt("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                PartyingApp.setUser(Usuario.getInstance(nombre, contrasena, id));
                                exitoLogin();

                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                            falloLogin();
                        }
                    }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nombre", nombre);
                    params.put("password", contrasena);
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

    }




    public void exitoLogin(){
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }
    public void falloLogin(){
        findViewById(R.id.tarjeta2).setVisibility(View.VISIBLE);
        mUsername.setText("");
        mPassword.setText("");
    }


    public void vincularVistas() {
        mUsername = findViewById(R.id.editTextPersonName);
        mPassword = findViewById(R.id.editTextPassword);
        tarjetaBoton = findViewById(R.id.tarjeta);
        botonRegistrar = findViewById(R.id.botonRegistrar);
        errorView = findViewById(R.id.errorText);
    }
}