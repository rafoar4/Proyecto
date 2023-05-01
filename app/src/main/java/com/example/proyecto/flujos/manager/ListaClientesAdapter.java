package com.example.proyecto.flujos.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.modelUsuario.Usuario;

import java.util.ArrayList;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder> {

    Context context;
    ArrayList<Usuario> arrayList;

    public ListaClientesAdapter(Context context, ArrayList<Usuario> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ListaClientesAdapter.ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_clientes,parent,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaClientesAdapter.ClienteViewHolder holder, int position) {

        Usuario usuario = arrayList.get(position);
        holder.nombre.setText(usuario.getNombre());
        //holder.hora.setText("DD 00:00");


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView hora;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.n_nombre);
            hora=itemView.findViewById(R.id.horaMensaje);

        }
    }
}
