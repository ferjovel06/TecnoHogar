package com.example.tecnohogar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tecnohogar.adapter.ElectricistasAdapter;
import com.example.tecnohogar.model.ElectricistaModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import java.util.ArrayList;


public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    RecyclerView mRecycler;
    ElectricistasAdapter mAdapter;


    FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private String UserId;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ImageView ivPerfil;

    TextView tvNombre;



    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Recycler Electricista
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.el_rec);
        mRecycler.setLayoutManager(new LinearLayoutManager(home.this));
        Query query = mFirestore.collection("Electricistas");

        FirestoreRecyclerOptions<ElectricistaModel> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<ElectricistaModel>().setQuery(query, ElectricistaModel.class).build();

        mAdapter = new ElectricistasAdapter(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

        // Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        mAuth = FirebaseAuth.getInstance();
        UserId = mAuth.getCurrentUser().getUid();
        tvNombre = findViewById(R.id.tv_nombre);
        // Tool Bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Navigation Drawer Menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(home.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        // Redirigir a pantalla del perfil
        ivPerfil = findViewById(R.id.iv_perfil);

        ivPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Perfil = new Intent(home.this, perfil.class);
                startActivity(Perfil);
            }
        });

        // Asigna el nombre del usuario a la casilla correspondiente
        mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("Users").document(UserId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nombre = documentSnapshot.getString("nombre");

                tvNombre.setText(nombre);
             }
        });


    }

    // OnClick Ver Mas Electricista
    public void verMasElectricista(View view) {
        Intent verMasElectricista = new Intent(home.this, Electricistas.class);
        startActivity(verMasElectricista);
    }


    // Funcion para ocultar teclado
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    // Previene que se salga de la app al presionar atras
    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    // Funciones de los items del menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;

            case R.id.nav_categorias:
                Intent categoria = new Intent(home.this, categorias.class);
                startActivity(categoria);
                break;

            case R.id.nav_verificar:
                Intent verificar = new Intent(home.this, agenda.class);
                startActivity(verificar);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}