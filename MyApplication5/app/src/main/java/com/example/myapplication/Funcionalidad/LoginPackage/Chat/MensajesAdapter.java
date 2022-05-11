package com.example.myapplication.Funcionalidad.LoginPackage.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class MensajesAdapter extends RecyclerView.Adapter<MensajesAdapter.MensajesViewHolder> {
    private final ArrayList<Mensaje> listaMensajes;
    private LayoutInflater vInflater;
    public MensajesAdapter(Context context, ArrayList<Mensaje> mensajes){
        listaMensajes = mensajes;
        vInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public MensajesAdapter.MensajesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componentemensajechat, parent, false);
        return new MensajesAdapter.MensajesViewHolder(votacionView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MensajesAdapter.MensajesViewHolder holder, int position) {
        holder.nombre.setText(String.valueOf(listaMensajes.get(holder.getAdapterPosition()).getNombre_autor()));
        holder.fecha.setText(String.valueOf(listaMensajes.get(holder.getAdapterPosition()).getFecha()));
        holder.contenido.setText(String.valueOf(listaMensajes.get(holder.getAdapterPosition()).getContenido()));

    }

    @Override
    public int getItemCount() {
        return listaMensajes.size();
    }

    public class MensajesViewHolder extends RecyclerView.ViewHolder {
        private final MensajesAdapter vAdapter;
        public TextView contenido, fecha, nombre;
        public MensajesViewHolder(View vista, MensajesAdapter eventosAdapter ){
            super(vista);
            vAdapter = eventosAdapter;
            nombre = vista.findViewById(R.id.nombre_user_mensaje_chat_usuario);
            contenido = vista.findViewById(R.id.contenido_mensaje_chat_usuario);
            fecha = vista.findViewById(R.id.fecha_creacion_mensaje_usuario);

        }

    }
}

