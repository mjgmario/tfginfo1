package com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClasesApp.Publicacion;
import com.example.myapplication.Funcionalidad.LoginPackage.PublicacionConcreta.PublicacionConcretaActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class PublicacionesAdapterInicio extends RecyclerView.Adapter<PublicacionesAdapterInicio.PublicacionesInicioViewHolder> {
    private final ArrayList<Publicacion> listaLocalUser;
    private final LayoutInflater vInflater;

    public PublicacionesAdapterInicio(Context context, ArrayList<Publicacion> lista){
        listaLocalUser = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PublicacionesAdapterInicio.PublicacionesInicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_publicaciones_amigos, parent, false);
        return new PublicacionesAdapterInicio.PublicacionesInicioViewHolder(votacionView, this);
    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull PublicacionesAdapterInicio.PublicacionesInicioViewHolder holder, int position) {

        holder.descripcion.setText(String.valueOf(listaLocalUser.get(holder.getAdapterPosition()).getDescripcion()));

        holder.descripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), PublicacionConcretaActivity.class);
                intent.putExtra("id_publicacion",listaLocalUser.get(holder.getAdapterPosition()).getId());
                view.getContext().startActivity(intent);
            }
        });

    }




    @Override
    public int getItemCount() {
        return listaLocalUser.size();
    }

    public class  PublicacionesInicioViewHolder extends RecyclerView.ViewHolder {

        public TextView descripcion, fecha;
        public final PublicacionesAdapterInicio vAdapter;

        public PublicacionesInicioViewHolder(View vista, PublicacionesAdapterInicio publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            descripcion = vista.findViewById(R.id.descripcion_publicacion_amigos);

        }


    }


}
