package com.example.proyecto.flujos.manager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.proyecto.R;
import com.example.proyecto.modelPlanta.Planta;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {

    FirebaseFirestore db;
    ListaPlantasAdapter adapter;
    RecyclerView recyclerView;

    ArrayList<Planta> plantas;

    ProgressDialog progressDialog;

    Button b_addPlanta,irCompras,irClientes,irReportes;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("fgaaaaaaaa");
        progressDialog.show();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        db= FirebaseFirestore.getInstance();
        plantas = new ArrayList<Planta>();
        adapter= new ListaPlantasAdapter(ManagerActivity.this,plantas);

        recyclerView.setAdapter(adapter);
        EventChangeListener();

        b_addPlanta=findViewById(R.id.addPlant);
        b_addPlanta.setOnClickListener(view -> {
            Intent intent= new Intent(ManagerActivity.this,AddPlantaActivity.class);
            startActivity(intent);
        });

        irClientes=findViewById(R.id.irClientes);
        irClientes.setOnClickListener(view -> {
            Intent intent=new Intent(ManagerActivity.this,LClientesActivity.class);
            startActivity(intent);
        });

        irCompras=findViewById(R.id.irCompras);
        irCompras.setOnClickListener(view -> {
            Intent intent=new Intent(ManagerActivity.this,LComprasActivity.class);
            startActivity(intent);
        });

        irReportes=findViewById(R.id.irReportes);
        irReportes.setOnClickListener(view -> {
            Intent intent=new Intent(ManagerActivity.this,LReportesActivity.class);
            startActivity(intent);
        });









    }
    private void EventChangeListener(){
        db.collection("plantas")
                .orderBy("nombre", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error!=null){
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Log.e("msg","Firestore error");
                            return;
                        }
                        for (DocumentChange dc: value.getDocumentChanges()){
                            if (dc.getType()==DocumentChange.Type.ADDED){
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