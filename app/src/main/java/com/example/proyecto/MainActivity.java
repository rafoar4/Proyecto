package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.databinding.ActivityMainBinding;
import com.example.proyecto.flujos.cliente.ClienteActivity;

import com.example.proyecto.flujos.cliente.LProductosActivity;
import com.example.proyecto.flujos.manager.LClientesActivity;
import com.example.proyecto.flujos.manager.ManagerActivity;
import com.example.proyecto.modelPlanta.Planta;
import com.example.proyecto.modelUsuario.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore db;
    Usuario usuario;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.e("TAG", "onCreate: ga" );
        auth=FirebaseAuth.getInstance();
        binding.btIniciosesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentiasl();
            }
        });
        //Log.d("msg",plantaList.get(1).getNombre());
        //binding.button.setOnClickListener(view -> {
         //   Intent intent= new Intent(MainActivity.this,ManagerActivity.class);
//(intent);

       // });



       // binding.button2.setOnClickListener(view -> {
       //     Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
//        });





    }

    private void checkCredentiasl() {
        String correo=binding.tEmail.getText().toString();
        String nombre=binding.nombre.getText().toString();
        String apellido=binding.apellido.getText().toString();
        String direc=binding.tDirec.getText().toString();
        String pass1=binding.tPass.getText().toString();
        String pass2=binding.tPass2.getText().toString();




        if(nombre.isEmpty()){
            showError(binding.nombre,"Nombre no puede estar vacio");
        } else if (apellido.isEmpty()) {
            showError(binding.apellido,"Nombre no puede estar vacio");
        } else if (direc.isEmpty()) {
            showError(binding.tDirec,"Nombre no puede estar vacio");
        } else if (!correo.contains("@")||correo.isEmpty()) {
            showError(binding.tEmail,"Correo no valido");
        } else if (pass1.isEmpty() || pass1.length()<7) {
            showError(binding.tPass,"Contraseña insegura");

        } else if (pass2.isEmpty() || !pass2.equals(pass1)) {
            showError(binding.tPass2,"Cotraseñas no coinciden");

        } else {
            usuario=new Usuario();
            usuario.setNombre(nombre);
            usuario.setCorreo(correo);
            usuario.setPassword(pass2);
            usuario.setApellido(apellido);
            usuario.setDireccion(direc);
            usuario.setRol("cliente");
            auth.createUserWithEmailAndPassword(correo,pass2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Log.e("TAG", "onComplete: ga2" );
                        userID=auth.getCurrentUser().getUid();
                        Log.e("TAG", "onComplete: "+userID );
                        db=FirebaseFirestore.getInstance();
                        db.collection("usuarios")
                                        .document(userID)
                                        .set(usuario)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(MainActivity.this,"Usuario registrado exitosamente",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MainActivity.this,Login.class));
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                e.printStackTrace();
                                            }
                                        });
                        Toast.makeText(MainActivity.this,"Usuario registrado exitosamente",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Login.class));


                    }else {
                        Toast.makeText(MainActivity.this,"Error en el registro"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

    public void registrarse(View view){
        Intent iniciar = new Intent(this, LProductosActivity.class);
        startActivity(iniciar);
    }



}
