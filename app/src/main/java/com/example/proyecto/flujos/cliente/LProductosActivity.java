package com.example.proyecto.flujos.cliente;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.proyecto.Carrito;
import com.example.proyecto.ContactoActivity;
import com.example.proyecto.Login;
import com.example.proyecto.R;
import com.example.proyecto.modelPlanta.Planta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LProductosActivity extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView recyclerView;
    ArrayList<Planta> plantas;
    ListaSingPlantasAdapter adapter;

    Button logout;

    FirebaseAuth auth;
    ImageButton casa,tienda,servicios,contacto;

    ProgressDialog progressDialog;

    EditText searchEditText;
    ImageButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Cargando Imagenes");
        progressDialog.show();
        setContentView(R.layout.activity_lproductos);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();
        plantas=new ArrayList<Planta>();
        adapter= new ListaSingPlantasAdapter(LProductosActivity.this,plantas);

        recyclerView.setAdapter(adapter);
        EventChangeListener();

        logout=findViewById(R.id.logout);

        auth=FirebaseAuth.getInstance();
        logout.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(LProductosActivity.this, Login.class));
        });

        //--
        casa=findViewById(R.id.irCasa);
        casa.setOnClickListener(view -> {
            Intent intent_Casa  =new Intent(LProductosActivity.this, ClienteActivity.class);
            startActivity(intent_Casa);
        });
        tienda=findViewById(R.id.irTienda);
        tienda.setOnClickListener(view -> {
            Intent intent_tienda=new Intent(LProductosActivity.this,LProductosActivity.class);
            startActivity(intent_tienda);
        });
        servicios=findViewById(R.id.irServicios);
        servicios.setOnClickListener(view -> {
            Intent intent=new Intent(LProductosActivity.this, Carrito.class);
            startActivity(intent);
        });
        contacto=findViewById(R.id.irContacto);
        contacto.setOnClickListener(view -> {
            Intent intent=new Intent(LProductosActivity.this, ContactoActivity.class);
            startActivity(intent);
        });

        searchEditText = findViewById(R.id.et_search2);
        btnSearch = findViewById(R.id.btn_search2);

        btnSearch.setOnClickListener(v -> {

            String searchTerm = searchEditText.getText().toString().trim().toLowerCase();
            searchPlantas(searchTerm);

        });


    }

    private void searchPlantas(String searchTerm) {
        progressDialog.setMessage("Buscando plantas...");
        progressDialog.show();
        db.clearPersistence();

        db.collection("plantas")
                .orderBy("nombre")
                .startAt(searchTerm)
                .endAt(searchTerm + "\uf8ff")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            progressDialog.dismiss();
                            Log.e("msg", "Firestore error");
                            return;
                        }

                        plantas.clear();

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                plantas.add(dc.getDocument().toObject(Planta.class));
                                Log.e("msg", dc.getDocument().toObject(Planta.class).getI_perfil());
                            }
                        }

                        adapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                });
    }

    private void EventChangeListener() {
        db.collection("plantas")
                .orderBy("stock", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Log.e("msg","firestore error");
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                Log.e("msg",dc.getDocument().toObject(Planta.class).getI_perfil());
                                plantas.add(dc.getDocument().toObject(Planta.class));
                            }
                        }
                        adapter.notifyDataSetChanged();
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }
                });
    }
}