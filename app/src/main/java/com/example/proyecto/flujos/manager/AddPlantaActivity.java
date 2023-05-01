package com.example.proyecto.flujos.manager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.proyecto.databinding.ActivityAddPlantaBinding;
import com.example.proyecto.modelPlanta.Planta;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddPlantaActivity extends AppCompatActivity {
    ActivityAddPlantaBinding binding;
    FirebaseFirestore db;
    Uri imageUri;

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_planta);
        binding = ActivityAddPlantaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        binding.bAddPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_form= binding.nNombre.getText().toString();
                String stock_form= binding.nStock.getText().toString();
                String precio_form= binding.nPrecio.getText().toString();
                String recom_form= binding.nRecomendaciones.getText().toString();

                //String Image_form= binding.imageAdd.

                /*HashMap<String,Object> hashMap= new HashMap<>();
                hashMap.put("nombre",nombre_form);
                hashMap.put("stock",nombre_form);
                hashMap.put("precio",nombre_form);
                hashMap.put("recomendacion",nombre_form);*/


                uploadImage();

                Planta planta= new Planta();
                planta.setNombre(nombre_form);
                planta.setStock(stock_form);
                planta.setPrecio(precio_form);
                planta.setRecomendacion(recom_form);
                planta.setI_perfil(imageUri.toString());
                db=FirebaseFirestore.getInstance();
                db.collection("plantas")
                        .add(planta)
                        .addOnSuccessListener(unused ->{
                            Log.d("msg","Se guardo");
                            Intent intent= new Intent(AddPlantaActivity.this,ManagerActivity.class);
                            startActivity(intent);


                        })
                        .addOnFailureListener(e ->e.printStackTrace());

            }
        });
    }

    private void uploadImage() {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now= new Date();
        String filename=formatter.format(now);
        storageReference=FirebaseStorage.getInstance().getReference("images/"+filename);
        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        binding.imagePerfil.setImageURI(imageUri);
                        //Toast.makeText()

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && data !=null && data.getData() != null){
            imageUri = data.getData();
            binding.imagePerfil.setImageURI(imageUri);

        }
    }
}