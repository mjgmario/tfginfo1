package com.example.myapplication.Funcionalidad.LoginPackage.Perfil;

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

public class LugaresAdapter extends RecyclerView.Adapter<LugaresAdapter.LugaresViewHolder> {
    private final ArrayList<Lugares> listaLocalUser;
    private final LayoutInflater vInflater;
    private static final String TAG= "Carga chats local";

    public LugaresAdapter(Context context, ArrayList<Lugares> lista){
        listaLocalUser = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LugaresAdapter.LugaresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_local_perfil, parent, false);
        return new LugaresAdapter.LugaresViewHolder(votacionView, this);


    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull LugaresAdapter.LugaresViewHolder holder, int position) {
        holder.nombre.setText(String.valueOf(listaLocalUser.get(holder.getAdapterPosition()).getNombre()));
        holder.fecha.setText(String.valueOf(listaLocalUser.get(holder.getAdapterPosition()).getFecha()));
        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), LocalesActivity.class);
                intent.putExtra("idLocal",listaLocalUser.get(holder.getAdapterPosition()).getId_local());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLocalUser.size();
    }

    public class  LugaresViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre, fecha;
        public final LugaresAdapter vAdapter;

        public LugaresViewHolder(View vista, LugaresAdapter publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            nombre = vista.findViewById(R.id.nombre_local_perfil_lugares);
            fecha = vista.findViewById(R.id.fechaLocalperfillugares);

        }
    }


}
