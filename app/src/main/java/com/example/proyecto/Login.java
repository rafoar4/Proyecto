package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.flujos.cliente.ClienteActivity;
import com.example.proyecto.flujos.cliente.LProductosActivity;
import com.example.proyecto.flujos.cliente.ListaSingPlantasAdapter;
import com.example.proyecto.flujos.manager.LClientesActivity;
import com.example.proyecto.flujos.manager.ManagerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    FirebaseAuth auth;
    Button btn_registrar,btn_log,btn_log_google;
    EditText etcorreo,etpass;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db=FirebaseFirestore.getInstance();

        auth= FirebaseAuth.getInstance();

        etcorreo=findViewById(R.id.t_email);
        etpass=findViewById(R.id.t_pass);

        btn_log=findViewById(R.id.bt_iniciosesion);
        btn_registrar=findViewById(R.id.bt_registrar);

        btn_log.setOnClickListener(v -> {
            login();
        });
        btn_registrar.setOnClickListener(v -> {
            startActivity(new Intent(Login.this,MainActivity.class));
        });




    }

    private void login() {
        String correo=etcorreo.getText().toString();
        String pass=etpass.getText().toString();
        if (TextUtils.isEmpty(correo)){
            etcorreo.setError("Correo vacio");
            etcorreo.requestFocus();
        } else if (TextUtils.isEmpty(pass)) {
            etpass.setError("Contraseña vacia");
            etpass.requestFocus();
            
        }else {
            auth.signInWithEmailAndPassword(correo,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this,"Inicio de sesion correcta",Toast.LENGTH_SHORT).show();
                        db.collection("usuarios").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Log.e("ga", "onSuccess: "+ documentSnapshot.get("rol"));
                                if(documentSnapshot.get("rol").equals("cliente")){
                                    startActivity(new Intent(Login.this, ClienteActivity.class));
                                }else {
                                    startActivity(new Intent(Login.this,ManagerActivity.class));
                                }
                            }
                        });


                    }else {
                        Toast.makeText(Login.this,"Error en el inicio"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            reload();
        }


    }
    private void reload() { }

    public void ingresar(View view){
        Intent iniciar = new Intent(this, LProductosActivity.class);
        startActivity(iniciar);
    }
    public void LISTA(View view){
        Intent iniciar = new Intent(this, ListaSingPlantasAdapter.class);
        startActivity(iniciar);

    }
    public void login_manager(View view){
        Intent iniciar2 = new Intent(this, LProductosActivity.class);
        startActivity(iniciar2);
    }


}