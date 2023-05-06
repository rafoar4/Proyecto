package com.example.proyecto.flujos.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        Planta planta= arrayList.get(position);
        holder.nombre.setText(planta.getNombre());
        holder.precio.setText(planta.getPrecio());
        holder.stock.setText(planta.getStock());

        /*storageReference = FirebaseStorage.getInstance().getReference(planta.getI_perfil());
        try {
            File local = File.createTempFile("temp",".jpg");
            storageReference.getFile(local)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(local.getAbsolutePath());
                    holder.imageView.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("msg","error",e);
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/
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
