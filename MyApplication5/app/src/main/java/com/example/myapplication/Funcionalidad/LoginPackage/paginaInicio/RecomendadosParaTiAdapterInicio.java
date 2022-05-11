package com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClasesApp.Local;
import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.LocalesActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class RecomendadosParaTiAdapterInicio extends RecyclerView.Adapter<RecomendadosParaTiAdapterInicio.RecomendadosParaTiInicioViewHolder> {
    private final ArrayList<Local> listaLocal;
    private final LayoutInflater vInflater;

    public RecomendadosParaTiAdapterInicio(Context context, ArrayList<Local> lista){
        listaLocal = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecomendadosParaTiInicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_sitios_recomendados, parent, false);
        return new RecomendadosParaTiAdapterInicio.RecomendadosParaTiInicioViewHolder(votacionView, this);


    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull RecomendadosParaTiAdapterInicio.RecomendadosParaTiInicioViewHolder holder, int position) {
        holder.nombre.setText(String.valueOf(listaLocal.get(holder.getAdapterPosition()).getNombre()));

        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), LocalesActivity.class);
                intent.putExtra("idLocal",listaLocal.get(holder.getAdapterPosition()).getId());
                view.getContext().startActivity(intent);

            }
        });
        holder.imagenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Uri gmmIntentUri = Uri.parse("geo:"+listaLocal.get(holder.getAdapterPosition()).getLat()+ "," + listaLocal.get(holder.getAdapterPosition()).getLongi()+ "?z=19"+"&q="+listaLocal.get(holder.getAdapterPosition()).getNombre());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                view.getContext().startActivity(mapIntent);*/
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + listaLocal.get(holder.getAdapterPosition()).getLat()+ "," + listaLocal.get(holder.getAdapterPosition()).getLongi());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                view.getContext().startActivity(mapIntent);

            }
        });

    }




    @Override
    public int getItemCount() {
        return listaLocal.size();
    }

    public class  RecomendadosParaTiInicioViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, lat, longitud;
        public final RecomendadosParaTiAdapterInicio vAdapter;
        public ImageButton imagenMap;

        public RecomendadosParaTiInicioViewHolder(View vista, RecomendadosParaTiAdapterInicio publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            nombre = vista.findViewById(R.id.nombre_sitio_recomendado);
            imagenMap = vista.findViewById(R.id.iconoMapsRecomendado);
        }


    }


}
