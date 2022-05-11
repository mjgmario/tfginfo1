package com.example.myapplication.Funcionalidad.LoginPackage.Locales1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.myapplication.Funcionalidad.LoginPackage.ChatConcretoLocal.ChatConcretoLocalActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.ChatConcretoLocal.ParticiparChatLocalConcretoActivity;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatsLocalAdapter extends RecyclerView.Adapter<ChatsLocalAdapter.ChatsLocalViewHolder> {
    private final ArrayList<chat_local> listaLocalUser;
    private final LayoutInflater vInflater;
    private static final String TAG= "Carga chats local";

    public ChatsLocalAdapter(Context context, ArrayList<chat_local> lista){
        listaLocalUser = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ChatsLocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_chats_local, parent, false);
        return new ChatsLocalAdapter.ChatsLocalViewHolder(votacionView, this);


    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull ChatsLocalAdapter.ChatsLocalViewHolder holder, int position) {
        holder.nombre.setText(String.valueOf(listaLocalUser.get(holder.getAdapterPosition()).getNombre()));
        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue= Volley.newRequestQueue(view.getContext());

                // Nueva petición JSONObject
                StringRequest request = new StringRequest(
                        Request.Method.POST, Constantes.URL_FETCH_USER_EN_CHAT_LOCAL,
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
                                Boolean esta = null;
                                try {
                                    esta = jsonObject.getBoolean("esta");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (esta) {
                                    Intent intent= new Intent(view.getContext(), ChatConcretoLocalActivity.class);
                                    intent.putExtra("id_chat_local",listaLocalUser.get(holder.getAdapterPosition()).getId_chat());
                                    view.getContext().startActivity(intent);
                                } else {
                                    Intent intent= new Intent(view.getContext(), ParticiparChatLocalConcretoActivity.class);
                                    intent.putExtra("id_chat_local",listaLocalUser.get(holder.getAdapterPosition()).getId_chat());
                                    view.getContext().startActivity(intent);
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
                        params.put("id_chat_local", String.valueOf(listaLocalUser.get(holder.getAdapterPosition()).getId_chat()));
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
        });



    }




    @Override
    public int getItemCount() {
        return listaLocalUser.size();
    }

    public class  ChatsLocalViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public final ChatsLocalAdapter vAdapter;

        public ChatsLocalViewHolder(View vista, ChatsLocalAdapter publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            nombre = vista.findViewById(R.id.nombre_chat_local);


        }


    }


}
