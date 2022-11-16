package com.example.tecnohogar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class perfil extends AppCompatActivity {

    // Variables
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private String UserId;

    TextView usuario_perfil, nombre_perfil, correo_perfil, tel_perfil;

    ImageView flechaAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Ir atras
        flechaAtras = findViewById(R.id.flecha_atras);

        flechaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atras = new Intent(perfil.this, home.class);
                startActivity(atras);
            }
        });

        // Hooks
        mAuth = FirebaseAuth.getInstance();
        UserId = mAuth.getCurrentUser().getUid();

        usuario_perfil = findViewById(R.id.nom_usuario);
        nombre_perfil = findViewById(R.id.nombre_perfil);
        correo_perfil = findViewById(R.id.correo_perfil);
        tel_perfil = findViewById(R.id.tel_perfil);

        // Asignar casillas
        mFirestore = FirebaseFirestore.getInstance();
        mFirestore.collection("Users").document(UserId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String userName = documentSnapshot.getString("userName");
                String nombre = documentSnapshot.getString("nombre");
                String correo = documentSnapshot.getString("userEmail");
                String telefono = documentSnapshot.getString("telefono");

                usuario_perfil.setText(userName);
                nombre_perfil.setText(nombre);
                correo_perfil.setText(correo);
                tel_perfil.setText(telefono);
            }
        });

        // Cerrar sesión
        TextView logout = findViewById(R.id.tv_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(perfil.this, login.class);
        startActivity(intent);
        finish();
    }
}