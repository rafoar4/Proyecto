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
    ArrayList<Usuario> arrayList =new ArrayList<>();

    public ListaClientesAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Usuario> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Usuario> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ListaClientesAdapter.ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.l_cliente_individual,parent,false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaClientesAdapter.ClienteViewHolder holder, int position) {

        Usuario usuario = arrayList.get(position);
        holder.nombre.setText(usuario.getNombre());

        holder.rol.setText(usuario.getRol());

        //holder.hora.setText("DD 00:00");


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView hora;
        TextView apellido;
        TextView rol;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombreCliente);
            hora=itemView.findViewById(R.id.horaMensaje);
            apellido=itemView.findViewById(R.id.apellido);
            rol=itemView.findViewById(R.id.rol);

        }
    }
}
