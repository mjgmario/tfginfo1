package com.example.myapplication.Funcionalidad.LoginPackage.ChatConcretoLocal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Funcionalidad.LoginPackage.Perfil.PerfilPublicacionesActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class MensajeLocalAdapter  extends RecyclerView.Adapter<MensajeLocalAdapter.MensajesLocalViewHolder>{

    private final ArrayList<Mensaje_Local> listaMensajes;
    private LayoutInflater vInflater;
    public MensajeLocalAdapter(Context context, ArrayList<Mensaje_Local> mensajes){
        listaMensajes = mensajes;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    public MensajeLocalAdapter.MensajesLocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_mensajes_chat, parent, false);
        return new MensajeLocalAdapter.MensajesLocalViewHolder(votacionView, this);
    }

    public void onBindViewHolder(@NonNull MensajeLocalAdapter.MensajesLocalViewHolder holder, int position) {
        holder.nombre.setText(String.valueOf(listaMensajes.get(holder.getAdapterPosition()).getNombreAutor()));
        holder.fecha.setText(String.valueOf(listaMensajes.get(holder.getAdapterPosition()).getFecha()));
        holder.contenido.setText(String.valueOf(listaMensajes.get(holder.getAdapterPosition()).getContenido()));
        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), PerfilPublicacionesActivity.class);
                intent.putExtra("id_user",listaMensajes.get(holder.getAdapterPosition()).getId_autor());
                view.getContext().startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return listaMensajes.size();
    }

    public class MensajesLocalViewHolder extends RecyclerView.ViewHolder {
        private final MensajeLocalAdapter vAdapter;
        public TextView contenido, fecha, nombre;
        public MensajesLocalViewHolder(View vista, MensajeLocalAdapter eventosAdapter ){
            super(vista);
            vAdapter = eventosAdapter;
            nombre = vista.findViewById(R.id.nombre_user_mensaje_chat);
            contenido = vista.findViewById(R.id.contenido_mensaje_chat);
            fecha = vista.findViewById(R.id.fecha_mensaje_chat);

        }

    }
}
