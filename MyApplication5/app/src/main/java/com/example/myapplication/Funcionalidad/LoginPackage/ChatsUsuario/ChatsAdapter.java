package com.example.myapplication.Funcionalidad.LoginPackage.ChatsUsuario;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Funcionalidad.LoginPackage.Chat.ChatActivity;
import com.example.myapplication.Funcionalidad.LoginPackage.Perfil.PerfilPublicacionesActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatsAdapterViewHolder> {
    private final ArrayList<Chat_Usuario> listaChats;
    private final LayoutInflater vInflater;

    public ChatsAdapter(Context context, ArrayList<Chat_Usuario> lista){
        listaChats = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ChatsAdapter.ChatsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_chats_usuario, parent, false);
        return new ChatsAdapter.ChatsAdapterViewHolder(votacionView, this);
    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull ChatsAdapter.ChatsAdapterViewHolder holder, int position) {

        holder.nombre.setText(String.valueOf(listaChats.get(holder.getAdapterPosition()).getNombre()));
        holder.fecha.setText(String.valueOf(listaChats.get(holder.getAdapterPosition()).getFecha_creacion()));
       if(!listaChats.get(holder.getAdapterPosition()).getNum_no_leidos().equals("0") ) {
            holder.no_leidos.setText("+" + String.valueOf(listaChats.get(holder.getAdapterPosition()).getNum_no_leidos()));
        }

        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), ChatActivity.class);
                intent.putExtra("id_chat",listaChats.get(holder.getAdapterPosition()).getId_chat());
                view.getContext().startActivity(intent);

            }
        });

    }




    @Override
    public int getItemCount() {
        return listaChats.size();
    }

    public class  ChatsAdapterViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, fecha, no_leidos;
        public final ChatsAdapter vAdapter;

        public ChatsAdapterViewHolder(View vista, ChatsAdapter publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            nombre = vista.findViewById(R.id.nombre_chat_usuario_Lista);
            fecha = vista.findViewById(R.id.fecha_creacion_chats_usuario_Lista);
            no_leidos = vista.findViewById(R.id.num_no_leidos_chat_usuario);


        }


    }


}
