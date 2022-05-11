package com.example.myapplication.Funcionalidad.LoginPackage.EventoConcreto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.Usuario_Local;
import com.example.myapplication.Funcionalidad.LoginPackage.Perfil.PerfilPublicacionesActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class UsuariosVanAEstarAdapter extends RecyclerView.Adapter<UsuariosVanAEstarAdapter.UsuariosVanAEstarViewholder> {
    private final ArrayList<Usuario_Local> listaLocalUser;
    private final LayoutInflater vInflater;

    public UsuariosVanAEstarAdapter(Context context, ArrayList<Usuario_Local> lista){
        listaLocalUser = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UsuariosVanAEstarAdapter.UsuariosVanAEstarViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_han_estado_local, parent, false);
        return new UsuariosVanAEstarAdapter.UsuariosVanAEstarViewholder(votacionView, this);


    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull UsuariosVanAEstarAdapter.UsuariosVanAEstarViewholder holder, int position) {
        holder.nombre.setText(String.valueOf(listaLocalUser.get(holder.getAdapterPosition()).getNombre()));
        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), PerfilPublicacionesActivity.class);
                intent.putExtra("id_user",listaLocalUser.get(holder.getAdapterPosition()).getId());
                view.getContext().startActivity(intent);

            }
        });


    }




    @Override
    public int getItemCount() {
        return listaLocalUser.size();
    }

    public class  UsuariosVanAEstarViewholder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public final UsuariosVanAEstarAdapter vAdapter;

        public UsuariosVanAEstarViewholder(View vista, UsuariosVanAEstarAdapter publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            nombre = vista.findViewById(R.id.nombre_user_han_estado);


        }


    }


}

