package com.example.myapplication.Funcionalidad.LoginPackage.Registro;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio.InicioActivity;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PantallaRegistroActivity extends AppCompatActivity {

    private static final String TAG= "registro";

    private EditText mUsername, mPassword, mRepetirContrasena, mCorreo, mTelefono;
    private CardView tarjetaRegistro;
    private TextView errorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_registro);

        vincularVistas();
        tarjetaRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ver valores que falten
                validarRegistro(mUsername.getText().toString(), mPassword.getText().toString(),
                        mRepetirContrasena.getText().toString(), mCorreo.getText().toString(),
                        mTelefono.getText().toString());
            }
        });
    }


    private void validarRegistro(String name, String cont, String repCont, String correo, String telefono) {
        if(!cont.equals(repCont)){
            errorView.setText("No coinciden las contrasenas");
            errorView.setVisibility(View.VISIBLE);
            falloRegistro();
        }
        else if(name.equals("") || cont.equals("") || correo.equals("") || telefono.equals("")){
            errorView.setText("Rellena todos los campos");
            errorView.setVisibility(View.VISIBLE);
            falloRegistro();
        }
        else{
            RequestQueue requestQueue= Volley.newRequestQueue(this);

            StringRequest request = new StringRequest(
                    Request.Method.POST, Constantes.URL_INSERT_USER,

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
                            JSONArray jsonArray= null;
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
                                    errorView.setText(mensaje);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Log.d(TAG, "Error Respuesta en JSON: " + mensaje);

                            }
                            else{
                                Integer id = null;
                                try {
                                    id = jsonObject.getInt("id");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                PartyingApp.setUser(Usuario.getInstance(name, cont, id));
                                exitoRegistro();

                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                            falloRegistro();
                        }
                    }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nombre", name);
                    params.put( "password", cont);
                    params.put( "correo", correo);
                    //params.put( "publico", publico);  insertar lo de publico
                    params.put( "phone", telefono);

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


    public void exitoRegistro(){
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }
    public void falloRegistro(){
        errorView.setVisibility(View.VISIBLE);
        mUsername.setText("");
        mPassword.setText("");
        mRepetirContrasena.setText("");
        mCorreo.setText("");
        mTelefono.setText("");

    }


    public void vincularVistas() {
        mUsername = findViewById(R.id.editTextPersonName);
        mPassword = findViewById(R.id.editTextPassword);
        mRepetirContrasena = findViewById(R.id.editTextPassword2);
        mCorreo = findViewById(R.id.editTextCorreo);
        mTelefono = findViewById(R.id.editTextCorreo);

        tarjetaRegistro = findViewById(R.id.CardRegistro);
        errorView = findViewById(R.id.TextoError);
    }
}

