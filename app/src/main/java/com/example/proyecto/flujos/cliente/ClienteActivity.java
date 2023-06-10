package com.example.proyecto.flujos.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.proyecto.Carrito;
import com.example.proyecto.ContactoActivity;
import com.example.proyecto.Login;
import com.example.proyecto.R;
import com.example.proyecto.Register;
import com.example.proyecto.flujos.manager.LClientesActivity;
import com.example.proyecto.flujos.manager.LComprasActivity;
import com.example.proyecto.flujos.manager.ManagerActivity;
import com.example.proyecto.modelPlanta.Planta;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ClienteActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    ArrayList<Planta> plantas;
    Button irSesion,irRegistro;
    ImageButton casa,tienda,servicios,contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        irSesion=findViewById(R.id.inicio);
        irSesion.setOnClickListener(view -> {
            Intent intent_inicio=new Intent(ClienteActivity.this, Login.class);
            startActivity(intent_inicio);
        });

        irRegistro=findViewById(R.id.bt_registrar2);
        irRegistro.setOnClickListener(view -> {
            Intent intent=new Intent(ClienteActivity.this, Register.class);
            startActivity(intent);
        });


        casa=findViewById(R.id.irCasa);
        casa.setOnClickListener(view -> {
            Intent intent_Casa  =new Intent(ClienteActivity.this, ClienteActivity.class);
            startActivity(intent_Casa);
        });
        tienda=findViewById(R.id.irTienda);
        tienda.setOnClickListener(view -> {
            Intent intent_tienda=new Intent(ClienteActivity.this,LProductosActivity.class);
            startActivity(intent_tienda);
        });
        servicios=findViewById(R.id.irServicios);
        servicios.setOnClickListener(view -> {
            Intent intent=new Intent(ClienteActivity.this, Carrito.class);
            startActivity(intent);
        });
        contacto=findViewById(R.id.irContacto);
        contacto.setOnClickListener(view -> {
            Intent intent=new Intent(ClienteActivity.this, ContactoActivity.class);
            startActivity(intent);
        });
    }
}