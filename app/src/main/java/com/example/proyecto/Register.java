package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Register extends AppCompatActivity {
    EditText etcorreo, etpass1,etpass2;

    Button btn_reg;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etcorreo=findViewById(R.id.email);
        etpass1=findViewById(R.id.pass1);
        etpass2=findViewById(R.id.pass2);
        auth=FirebaseAuth.getInstance();

        btn_reg=findViewById(R.id.btn_reg);



        btn_reg.setOnClickListener(v -> {
            checkCredentials();


        });

    }

    private void checkCredentials() {
        String correo=etcorreo.getText().toString();
        String pass1=etpass1.getText().toString();
        String pass2=etpass2.getText().toString();
        if(correo.isEmpty()){
            showError(etcorreo,"Rellene el campo de correo");
        } else if (!correo.contains("@")) {
            showError(etcorreo,"Correo no valido");
            
        } else if (pass1.isEmpty() || pass1.length()<7) {
            showError(etpass1,"Contraseña insegura");
            
        } else if (pass2.isEmpty() || !pass2.equals(pass1)) {
            showError(etpass2,"Cotraseñas no coinciden");
            
        } else {
            auth.createUserWithEmailAndPassword(correo,pass2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        sendVerificationEmail();

                    }else {
                        Toast.makeText(Register.this,"Error en el registro"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(e instanceof FirebaseAuthUserCollisionException){
                        showError(etpass2,"Correo ya registrado");
                    }
                    else {
                        Toast.makeText(Register.this,"Algo salio mal",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendVerificationEmail() {
        if(auth.getCurrentUser()!=null){
            auth.getCurrentUser().sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register.this,"Se envio un correo de verificación",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,Login.class));
                            }else {
                                Toast.makeText(Register.this,"Ocurrio un problema al enviar correo de verificación",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this,Login.class));


                            }
                        }
                    });
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }




}
