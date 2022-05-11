package com.example.myapplication.Funcionalidad.LoginPackage.paginaInicio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClasesApp.Local_User;
import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.LocalesActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class SitiosHasEstadoAdapter extends RecyclerView.Adapter<SitiosHasEstadoAdapter.SitiosHasEstadoViewHolder> {
    private final ArrayList<Local_User> listaLocalUser;
    private final LayoutInflater vInflater;

    public SitiosHasEstadoAdapter(Context context, ArrayList<Local_User> lista){
        listaLocalUser = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SitiosHasEstadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componentes_sitios_estado, parent, false);
        return new SitiosHasEstadoAdapter.SitiosHasEstadoViewHolder(votacionView, this);


    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull SitiosHasEstadoAdapter.SitiosHasEstadoViewHolder holder, int position) {
        holder.nombre.setText(String.valueOf(listaLocalUser.get(holder.getAdapterPosition()).getNombre()));
        holder.fecha.setText(String.valueOf(listaLocalUser.get(holder.getAdapterPosition()).getFecha()));

        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), LocalesActivity.class);
                intent.putExtra("idLocal",listaLocalUser.get(holder.getAdapterPosition()).getId());
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
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + listaLocalUser.get(holder.getAdapterPosition()).getLat()+ "," + listaLocalUser.get(holder.getAdapterPosition()).getLongi());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                view.getContext().startActivity(mapIntent);

            }
        });
    }




    @Override
    public int getItemCount() {
        return listaLocalUser.size();
    }

    public class  SitiosHasEstadoViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, fecha;
        public final SitiosHasEstadoAdapter vAdapter;
        public ImageButton imagenMap;

        public SitiosHasEstadoViewHolder(View vista, SitiosHasEstadoAdapter publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            fecha = vista.findViewById(R.id.fecha_sitios_he_estado);
            nombre = vista.findViewById(R.id.nombre_sitios_he_estado);
            imagenMap = vista.findViewById(R.id.iconoMapsHasEstadoInicio);


        }


    }


}
