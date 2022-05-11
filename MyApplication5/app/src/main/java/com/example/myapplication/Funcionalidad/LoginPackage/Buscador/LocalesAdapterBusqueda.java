package com.example.myapplication.Funcionalidad.LoginPackage.Buscador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Funcionalidad.LoginPackage.Locales1.LocalesActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class LocalesAdapterBusqueda extends RecyclerView.Adapter<LocalesAdapterBusqueda.LocalesAdapterBusquedaViewHolder> {
    private final ArrayList<Local_Busqueda> listaLocalUser;
    private final LayoutInflater vInflater;

    public LocalesAdapterBusqueda(Context context, ArrayList<Local_Busqueda> lista){
        listaLocalUser = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LocalesAdapterBusqueda.LocalesAdapterBusquedaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componentelocalesbusqueda, parent, false);
        return new LocalesAdapterBusqueda.LocalesAdapterBusquedaViewHolder(votacionView, this);
    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull LocalesAdapterBusqueda.LocalesAdapterBusquedaViewHolder holder, int position) {

        holder.nombre.setText(listaLocalUser.get(holder.getAdapterPosition()).getNombre());

        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), LocalesActivity.class);
                intent.putExtra("idLocal",listaLocalUser.get(holder.getAdapterPosition()).getId());
                view.getContext().startActivity(intent);
            }
        });

    }




    @Override
    public int getItemCount() {
        return listaLocalUser.size();
    }

    public class  LocalesAdapterBusquedaViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public final LocalesAdapterBusqueda vAdapter;

        public LocalesAdapterBusquedaViewHolder(View vista, LocalesAdapterBusqueda publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            nombre = vista.findViewById(R.id.nombre_local_busqueda);

        }


    }


}
