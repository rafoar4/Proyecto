package com.example.proyecto.flujos.manager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.proyecto.Carrito;
import com.example.proyecto.R;
import com.example.proyecto.flujos.cliente.ClienteActivity;
import com.example.proyecto.flujos.cliente.LProductosActivity;
import com.example.proyecto.modelPlanta.Planta;
import com.example.proyecto.modelUsuario.Usuario;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LClientesActivity extends AppCompatActivity {

    FirebaseFirestore db;
    ListaClientesAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Usuario> usuarios;
    ArrayList<Usuario> clientes;

    Button b_regresar;
    ImageButton casa,tienda,servicios,contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lclientes);
        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();

        usuarios = new ArrayList<Usuario>();
        EventChangeListener();

        adapter= new ListaClientesAdapter(LClientesActivity.this);
        casa=findViewById(R.id.irCasa_lcli);
        casa.setOnClickListener(view -> {
            Intent intent_Casa  =new Intent(LClientesActivity.this, ManagerActivity.class);
            startActivity(intent_Casa);
        });

        servicios=findViewById(R.id.Reportes_lcli);
        servicios.setOnClickListener(view -> {
            Intent intent=new Intent(LClientesActivity.this, LReportesActivity.class);
            startActivity(intent);
        });
        contacto=findViewById(R.id.irClientes_lcli);
        contacto.setOnClickListener(view -> {
            Intent intent=new Intent(LClientesActivity.this, LClientesActivity.class);
            startActivity(intent);
        });





    }

    private void EventChangeListener() {
        db.collection("usuarios")
                .addSnapshotListener((value, error) -> {
                    if (error!=null){
                        Log.e("msg","Firestore error");
                        return;
                    }
                    for (DocumentChange dc: value.getDocumentChanges()){
                        Log.e("msg", String.valueOf(dc.getDocument().getData()));
                        if (dc.getType()==DocumentChange.Type.ADDED){
                            Usuario u=new Usuario();
                            u= dc.getDocument().toObject(Usuario.class);
                            Log.e("TAG", "onEvent: "+u.getNombre() );
                            if(u.getRol().equals("cliente")){
                                usuarios.add(u);
                            }

                            Log.e("TAG", "onCreate: "+usuarios.size());
                            Log.e("msg","no error");
                        }

                    }
                    /*clientes = usuarios.stream()
                            .filter(usuario -> usuario.getRol().equals("cliente"))
                            .collect(Collectors.toCollection(ArrayList::new));
                    Log.e("TAG", "onCreate: "+usuarios.size());*/
                    adapter.setArrayList(usuarios);
                    recyclerView.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();

                });
        Log.e("TAG4", "onCreate: "+usuarios.size());


    }
}