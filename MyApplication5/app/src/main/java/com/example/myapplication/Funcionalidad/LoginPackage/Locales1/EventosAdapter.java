package com.example.myapplication.Funcionalidad.LoginPackage.Locales1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Funcionalidad.LoginPackage.EventoConcreto.EventoConcretoActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.EventosAdapterViewHolder> {
    private final ArrayList<Eventos> listaLocal;
    private final LayoutInflater vInflater;

    public EventosAdapter(Context context, ArrayList<Eventos> lista){
        listaLocal = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EventosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_eventos_local, parent, false);
        return new EventosAdapter.EventosAdapterViewHolder(votacionView, this);


    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull EventosAdapter.EventosAdapterViewHolder holder, int position) {
        holder.nombre.setText(String.valueOf(listaLocal.get(holder.getAdapterPosition()).getNombre()));
        holder.fecha.setText(String.valueOf(listaLocal.get(holder.getAdapterPosition()).getFecha()));
        holder.precio.setText("Precio: " + String.valueOf(listaLocal.get(holder.getAdapterPosition()).getPrecio()));
        holder.edad_min.setText("Edad min: " +String.valueOf(listaLocal.get(holder.getAdapterPosition()).getEdad_min()));

        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), EventoConcretoActivity.class);
                intent.putExtra("id_evento",listaLocal.get(holder.getAdapterPosition()).getId_evento());
                view.getContext().startActivity(intent);

            }
        });



    }




    @Override
    public int getItemCount() {
        return listaLocal.size();
    }

    public class  EventosAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, fecha, precio, edad_min;
        public final EventosAdapter vAdapter;

        public EventosAdapterViewHolder(View vista, EventosAdapter publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            nombre = vista.findViewById(R.id.nombre_evento_local);
            fecha = vista.findViewById(R.id.fecha_evento_local);
            precio = vista.findViewById(R.id.precio_evento_local);
            edad_min = vista.findViewById(R.id.edad_min_evento_local);

        }


    }


}
