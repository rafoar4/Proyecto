package com.example.proyecto.flujos.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.proyecto.R;

public class LComprasActivity extends AppCompatActivity {
    ImageButton casa,servicios,contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcompras);
        casa=findViewById(R.id.irCasa_lcompras);
        casa.setOnClickListener(view -> {
            Intent intent_Casa  =new Intent(LComprasActivity.this, ManagerActivity.class);
            startActivity(intent_Casa);
        });

        servicios=findViewById(R.id.Reportes_lcompras);
        servicios.setOnClickListener(view -> {
            Intent intent=new Intent(LComprasActivity.this, LReportesActivity.class);
            startActivity(intent);
        });
        contacto=findViewById(R.id.irClientes_lcompras);
        contacto.setOnClickListener(view -> {
            Intent intent=new Intent(LComprasActivity.this, LClientesActivity.class);
            startActivity(intent);
        });
    }
}