package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyecto.databinding.ActivityMainBinding;
import com.example.proyecto.flujos.cliente.ClienteActivity;

import com.example.proyecto.flujos.cliente.LProductosActivity;
import com.example.proyecto.flujos.manager.ManagerActivity;
import com.example.proyecto.modelPlanta.Planta;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Log.d("msg",plantaList.get(1).getNombre());
        //binding.button.setOnClickListener(view -> {
         //   Intent intent= new Intent(MainActivity.this,ManagerActivity.class);
//(intent);

       // });



       // binding.button2.setOnClickListener(view -> {
       //     Intent intent = new Intent(MainActivity.this, ClienteActivity.class);
//        });





    }
    public void registrarse(View view){
        Intent iniciar = new Intent(this, LProductosActivity.class);
        startActivity(iniciar);
    }



}
