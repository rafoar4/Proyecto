package com.example.proyecto.flujos.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto.R;
import com.example.proyecto.modelPlanta.Planta;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ListaPlantasAdapter extends RecyclerView.Adapter<ListaPlantasAdapter.PlantaViewHolder> {


    Context context;
    ArrayList<Planta> arrayList;

    StorageReference storageReference;

    public ListaPlantasAdapter(Context context, ArrayList<Planta> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public com.example.proyecto.flujos.manager.ListaPlantasAdapter.PlantaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plantas_mult,parent,false);
        return new PlantaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaPlantasAdapter.PlantaViewHolder holder, int position) {

        Planta planta = arrayList.get(position);
        holder.nombre.setText(planta.getNombre());
        holder.precio.setText(planta.getPrecio());
        holder.stock.setText(planta.getStock());

        if (planta.getL_adicionales() != null && planta.getL_adicionales().size() > 0) {
            holder.go_left.setVisibility(View.VISIBLE);
            holder.go_right.setVisibility(View.VISIBLE);
        } else {
            holder.go_left.setVisibility(View.GONE);
            holder.go_right.setVisibility(View.GONE);
        }
        holder.go_left.setOnClickListener(view -> {
            int currentIndex = holder.getCurrentIndex();
            if (currentIndex > 0) {
                currentIndex--;
            } else {
                currentIndex = planta.getL_adicionales().size();
            }
            holder.setCurrentIndex(currentIndex);
            String imageUrl = holder.getCurrentImage();
            Glide.with(context).load(imageUrl).into(holder.imageView);
        });
        holder.go_right.setOnClickListener(view -> {
            int currentIndex = holder.getCurrentIndex();
            if (currentIndex < planta.getL_adicionales().size()) {
                currentIndex++;
            } else {
                currentIndex = 0;
            }
            holder.setCurrentIndex(currentIndex);
            String imageUrl = holder.getCurrentImage();
            Glide.with(context).load(imageUrl).into(holder.imageView);
        });

        String imageUrl = holder.getCurrentImage();
        Glide.with(context).load(imageUrl).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class PlantaViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, precio,stock;
        ImageView imageView;

        ImageButton go_left,go_right;
        int currentIndex;



        public PlantaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.n_nombre);
            stock=itemView.findViewById(R.id.n_stock);
            precio=itemView.findViewById(R.id.n_precio);
            imageView=itemView.findViewById(R.id.imageView);
            go_left=itemView.findViewById(R.id.go_left);
            go_right=itemView.findViewById(R.id.go_right);
            currentIndex=0;
        }
        public int getCurrentIndex() {
            return currentIndex;
        }

        public void setCurrentIndex(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        public String getCurrentImage() {
            Planta planta = arrayList.get(getAdapterPosition());
            if (currentIndex == 0) {
                return planta.getI_perfil();
            } else {
                return planta.getL_adicionales().get(currentIndex - 1);
            }
        }
    }
}
