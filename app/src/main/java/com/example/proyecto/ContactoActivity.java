package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.proyecto.flujos.cliente.ClienteActivity;
import com.example.proyecto.flujos.cliente.LProductosActivity;

public class ContactoActivity extends AppCompatActivity {
    ImageButton casa,tienda,servicios,contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        //--
        casa=findViewById(R.id.irCasa_c);
        casa.setOnClickListener(view -> {
            Intent intent_Casa  =new Intent(ContactoActivity.this, ClienteActivity.class);
            startActivity(intent_Casa);
        });
        tienda=findViewById(R.id.irTienda_c);
        tienda.setOnClickListener(view -> {
            Intent intent_tienda=new Intent(ContactoActivity.this, LProductosActivity.class);
            startActivity(intent_tienda);
        });
        servicios=findViewById(R.id.irServicios_c);
        servicios.setOnClickListener(view -> {
            Intent intent=new Intent(ContactoActivity.this, Carrito.class);
            startActivity(intent);
        });
        contacto=findViewById(R.id.irContacto_c);
        contacto.setOnClickListener(view -> {
            Intent intent=new Intent(ContactoActivity.this, ContactoActivity.class);
            startActivity(intent);
        });
    }
}