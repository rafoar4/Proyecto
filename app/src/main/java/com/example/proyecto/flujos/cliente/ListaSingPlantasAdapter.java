package com.example.proyecto.flujos.cliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto.R;
import com.example.proyecto.modelPlanta.Planta;

import java.util.ArrayList;

public class ListaSingPlantasAdapter extends RecyclerView.Adapter<ListaSingPlantasAdapter.SingHolder> {
    Context context;
    ArrayList<Planta> arrayList;

    public ListaSingPlantasAdapter(Context context, ArrayList<Planta> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plantas_sing,parent,false);
        return new SingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingHolder holder, int position) {
        Planta planta= arrayList.get(position);
        holder.nombre.setText(planta.getNombre());
        holder.precio.setText(planta.getPrecio());
        holder.stock.setText(planta.getStock());
        Glide.with(context).load(planta.getI_perfil().toString()).into(holder.imageView);

        //Log.d("msg",String.valueOf(planta.getI_perfil()));
        //holder.uri.setImageURI(Uri.parse(planta.getI_perfil()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SingHolder extends RecyclerView.ViewHolder{
        TextView nombre, precio,stock;
        ImageView imageView;

        public SingHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombrePS);
            stock=itemView.findViewById(R.id.stockCS);
            precio=itemView.findViewById(R.id.precioS);
            imageView=itemView.findViewById(R.id.imagePs);
        }
    }
}
