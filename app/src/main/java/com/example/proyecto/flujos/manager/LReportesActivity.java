package com.example.proyecto.flujos.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.proyecto.R;

public class LReportesActivity extends AppCompatActivity {
    ImageButton casa,servicios,contacto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lreportes);
        casa=findViewById(R.id.irCasa_lreport);
        casa.setOnClickListener(view -> {
            Intent intent_Casa  =new Intent(LReportesActivity.this, ManagerActivity.class);
            startActivity(intent_Casa);
        });

        servicios=findViewById(R.id.reportes_lreport);
        servicios.setOnClickListener(view -> {
            Intent intent=new Intent(LReportesActivity.this, LReportesActivity.class);
            startActivity(intent);
        });
        contacto=findViewById(R.id.irClientes_lreport);
        contacto.setOnClickListener(view -> {
            Intent intent=new Intent(LReportesActivity.this, LClientesActivity.class);
            startActivity(intent);
        });
    }
}