package com.example.tecnohogar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tecnohogar.adapters.ElectricistaAdapters;
import com.example.tecnohogar.models.ElectricistaModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView electricistasRec;

    // Popular items
    List<ElectricistaModel> electricistaModelList;
    ElectricistaAdapters electricistaAdapters;

    //Variables
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private String UserId;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ImageView ivPerfil;

    TextView tvNombre;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_home, container, false);

        electricistasRec = root.findViewById(R.id.el_rec); //Aqui me quede

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
        return  root;
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