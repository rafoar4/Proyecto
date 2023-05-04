package com.example.proyecto.flujos.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.proyecto.R;
import com.example.proyecto.flujos.manager.LComprasActivity;
import com.example.proyecto.flujos.manager.ManagerActivity;
import com.example.proyecto.modelPlanta.Planta;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ClienteActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    ArrayList<Planta> plantas;
    Button irProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        irProductos=findViewById(R.id.irProductos);
        irProductos.setOnClickListener(view -> {
            Intent intent=new Intent(ClienteActivity.this, LProductosActivity.class);
            startActivity(intent);
        });
    }
}