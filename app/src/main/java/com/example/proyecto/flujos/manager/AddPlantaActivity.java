package com.example.proyecto.flujos.manager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.databinding.ActivityAddPlantaBinding;
import com.example.proyecto.modelPlanta.Planta;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddPlantaActivity extends AppCompatActivity {
    ActivityAddPlantaBinding binding;
    FirebaseFirestore db;
    Uri imageUri;

    StorageReference storageReference;
    String filename;

    ImageButton casa,servicios,contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_planta);
        binding = ActivityAddPlantaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
        binding.bAddPlanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                subirPlanta();
            }
        });
        //--
        casa=findViewById(R.id.irCasa_agregar);
        casa.setOnClickListener(view -> {
            Intent intent_Casa  =new Intent(AddPlantaActivity.this, ManagerActivity.class);
            startActivity(intent_Casa);
        });

        servicios=findViewById(R.id.Reportes_agregar);
        servicios.setOnClickListener(view -> {
            Intent intent=new Intent(AddPlantaActivity.this, LReportesActivity.class);
            startActivity(intent);
        });
        contacto=findViewById(R.id.irClientes_agregar);
        contacto.setOnClickListener(view -> {
            Intent intent=new Intent(AddPlantaActivity.this, LClientesActivity.class);
            startActivity(intent);
        });
    }

    private void subirPlanta() {



        final ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now= new Date();
        filename=formatter.format(now);
        FirebaseStorage.getInstance().getReference("images/"+filename).putFile(imageUri)
                .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if(task.isSuccessful()){
                                        String nombre_form= binding.nNombre.getText().toString();
                                        String stock_form= binding.nStock.getText().toString();
                                        String precio_form= binding.nPrecio.getText().toString();
                                        String recom_form= binding.nRecomendaciones.getText().toString();
                                        Planta planta= new Planta();
                                        planta.setNombre(nombre_form);
                                        planta.setStock(stock_form);
                                        planta.setPrecio(precio_form);
                                        planta.setRecomendacion(recom_form);
                                        planta.setI_perfil(task.getResult().toString());
                                        db=FirebaseFirestore.getInstance();
                                        db.collection("plantas")
                                                .add(planta)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Log.e("msg","se gurado");
                                                        Intent intent= new Intent(AddPlantaActivity.this,ManagerActivity.class);
                                                        startActivity(intent);

                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                });
                                    }
                                }
                            });
                            progressDialog.dismiss();
                            Toast.makeText(AddPlantaActivity.this,"Image Uploaded",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(AddPlantaActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode== RESULT_OK && data != null ){
            data.getData();

            imageUri=data.getData();
            getImageinImageView();

        }
    }

    private void getImageinImageView() {

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        binding.imagePerfil.setImageBitmap(bitmap);
    }
}