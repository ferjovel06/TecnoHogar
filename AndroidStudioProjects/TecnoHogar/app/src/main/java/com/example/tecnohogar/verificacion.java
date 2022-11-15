package com.example.tecnohogar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class verificacion extends AppCompatActivity {

    // Variables
    ImageView atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion);

        // Boton atras
        atras = findViewById(R.id.iv_atras);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Atras = new Intent(verificacion.this, home.class);
                startActivity(Atras);
            }
        });
    }
}