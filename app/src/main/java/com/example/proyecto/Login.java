package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyecto.flujos.cliente.LProductosActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void ingresar(View view){
        Intent iniciar = new Intent(this, LProductosActivity.class);
        startActivity(iniciar);
    }
}