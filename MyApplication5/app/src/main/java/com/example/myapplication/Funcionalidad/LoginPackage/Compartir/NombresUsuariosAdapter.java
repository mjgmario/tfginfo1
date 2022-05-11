package com.example.myapplication.Funcionalidad.LoginPackage.Compartir;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class NombresUsuariosAdapter extends RecyclerView.Adapter<NombresUsuariosAdapter.NombresUsuariosViewholder> {
    private final ArrayList<String> listaLocalUser;
    private final LayoutInflater vInflater;

    public NombresUsuariosAdapter(Context context, ArrayList<String> lista){
        listaLocalUser = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NombresUsuariosAdapter.NombresUsuariosViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_usuarios_crear_chat, parent, false);
        return new NombresUsuariosAdapter.NombresUsuariosViewholder(votacionView, this);


    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull NombresUsuariosAdapter.NombresUsuariosViewholder holder, int position) {
        holder.nombre.setText(listaLocalUser.get(holder.getAdapterPosition()));

    }




    @Override
    public int getItemCount() {
        return listaLocalUser.size();
    }

    public class  NombresUsuariosViewholder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public final NombresUsuariosAdapter vAdapter;

        public NombresUsuariosViewholder(View vista, NombresUsuariosAdapter publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            nombre = vista.findViewById(R.id.nombre_user_crear_chat);

        }


    }


}

