package com.example.proyecto.flujos.manager;

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

public class ListaPlantasAdapter extends RecyclerView.Adapter<ListaPlantasAdapter.PlantaViewHolder> {


    Context context;
    ArrayList<Planta> arrayList;

    public ListaPlantasAdapter(Context context, ArrayList<Planta> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ListaPlantasAdapter.PlantaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plantas,parent,false);
        return new PlantaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPlantasAdapter.PlantaViewHolder holder, int position) {

        Planta planta= arrayList.get(position);
        holder.nombre.setText(planta.getNombre());
        holder.precio.setText(planta.getPrecio());
        holder.stock.setText(planta.getStock());
        Glide.with(context).load(planta.getI_perfil()).into(holder.imageView);
        //Log.d("msg",String.valueOf(planta.getI_perfil()));
        //holder.uri.setImageURI(Uri.parse(planta.getI_perfil()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class PlantaViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, precio,stock;
        ImageView imageView;



        public PlantaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.n_nombre);
            stock=itemView.findViewById(R.id.n_stock);
            precio=itemView.findViewById(R.id.n_precio);
            imageView=itemView.findViewById(R.id.imageView);


        }
    }
}
