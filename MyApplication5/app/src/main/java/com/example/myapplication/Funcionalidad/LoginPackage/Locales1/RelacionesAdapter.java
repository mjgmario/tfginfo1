package com.example.myapplication.Funcionalidad.LoginPackage.Locales1;

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

public class RelacionesAdapter extends RecyclerView.Adapter<RelacionesAdapter.RelacionesViewholder> {
    private final ArrayList<Usuario_Local> listaLocalUser;
    private final LayoutInflater vInflater;

    public RelacionesAdapter(Context context, ArrayList<Usuario_Local> lista){
        listaLocalUser = lista;
        vInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RelacionesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View votacionView = vInflater.inflate(R.layout.componente_relaciones_local, parent, false);
        return new RelacionesAdapter.RelacionesViewholder(votacionView, this);


    }

    @Override // ver si es administrador
    public void onBindViewHolder(@NonNull RelacionesAdapter.RelacionesViewholder holder, int position) {
        holder.nombre.setText(listaLocalUser.get(holder.getAdapterPosition()).getNombre());
        holder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), PerfilPublicacionesActivity.class);
                intent.putExtra("id_user",listaLocalUser.get(holder.getAdapterPosition()).getId());
                view.getContext().startActivity(intent);

            }
        });

    }




    @Override
    public int getItemCount() {
        return listaLocalUser.size();
    }

    public class  RelacionesViewholder extends RecyclerView.ViewHolder {
        public TextView nombre;
        public final RelacionesAdapter vAdapter;

        public RelacionesViewholder(View vista, RelacionesAdapter publicacionesAdapter ){
            super(vista);
            vAdapter = publicacionesAdapter;
            nombre = vista.findViewById(R.id.nombre_relaciones_local);


        }


    }


}
