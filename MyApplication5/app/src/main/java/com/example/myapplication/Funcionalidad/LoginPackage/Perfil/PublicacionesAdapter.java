package com.example.myapplication.Funcionalidad.LoginPackage.Perfil;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Funcionalidad.LoginPackage.PublicacionConcreta.PublicacionConcretaActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PublicacionesAdapter extends RecyclerView.Adapter<PublicacionesAdapter.PublicacionesViewHolder> {
    private final ArrayList<Publicacion> listaLocalUser;
    private final LayoutInflater vInflater;
    private static final String TAG= "Carga chats local";

    public PublicacionesAdapter(Context context, ArrayList<Publicacion> lista){
        listaLocalUser = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PublicacionesAdapter.PublicacionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_publicacion_perfil, parent, false);
        return new PublicacionesAdapter.PublicacionesViewHolder(votacionView, this);


    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull PublicacionesAdapter.PublicacionesViewHolder holder, int position) {
        holder.imagenPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), PublicacionConcretaActivity.class);
                intent.putExtra("id_publicacion",listaLocalUser.get(holder.getAdapterPosition()).getId_publicacion());
                view.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return listaLocalUser.size();
    }

    public class  PublicacionesViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagenPublicacion;
        public final PublicacionesAdapter vAdapter;

        public PublicacionesViewHolder(View vista, PublicacionesAdapter publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            imagenPublicacion = vista.findViewById(R.id.imagenPublicacionPerfilPublicaciones);

        }
    }


}
