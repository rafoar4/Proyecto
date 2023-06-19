package com.example.proyecto.flujos.cliente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto.R;
import com.example.proyecto.modelPlanta.Planta;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ListaSingPlantasAdapter extends RecyclerView.Adapter<ListaSingPlantasAdapter.SingHolder> {
    Context context;
    ArrayList<Planta> arrayList;

    StorageReference storageReference;


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
        storageReference= FirebaseStorage.getInstance().getReference("images");
        Planta planta= arrayList.get(position);
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
        Glide.with(context).load(planta.getI_perfil()).into(holder.imageView);

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

        ImageButton go_left,go_right;
        int currentIndex;

        public SingHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombrePS);
            stock=itemView.findViewById(R.id.stockCS);
            precio=itemView.findViewById(R.id.precioS);
            imageView=itemView.findViewById(R.id.imagePs);
            go_left=itemView.findViewById(R.id.go_left2);
            go_right=itemView.findViewById(R.id.go_right2);
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
