package com.example.tecnohogar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Registro extends AppCompatActivity {

    // Variables
    EditText nombre, usuario, correo, telefono, contrasena;
    TextView Iniciar;
    Button btnRegistrar;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Hooks
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        nombre = findViewById(R.id.ct_nombre);
        usuario = findViewById(R.id.ct_usuarioregistro);
        correo = findViewById(R.id.ct_correoregistro);
        telefono = findViewById(R.id.ct_numtel);
        contrasena = findViewById(R.id.ct_password);

        btnRegistrar = findViewById(R.id.btn_registrarse);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // Los checkfields verifican que no haya campos vacíos
               checkField(nombre);
               checkField(usuario);
               checkField(correo);
               checkField(telefono);
               checkField(contrasena);

               if (valid) {
                   // Inicia el proceso de registro
                   fAuth.createUserWithEmailAndPassword(correo.getText().toString(), contrasena.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                       @Override
                       public void onSuccess(AuthResult authResult) {
                           FirebaseUser user = fAuth.getCurrentUser();
                           Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                           DocumentReference df = fStore.collection("Users").document(user.getUid());
                           Map<String,Object> userInfo = new HashMap<>();
                           userInfo.put("nombre",nombre.getText().toString());
                           userInfo.put("userName",usuario.getText().toString());
                           userInfo.put("userEmail",correo.getText().toString());
                           userInfo.put("telefono",telefono.getText().toString());
                           userInfo.put("password",contrasena.getText().toString());
                           // Especifica si el usuario es un trabajador

                           userInfo.put("isWorker", "0");

                           df.set(userInfo);

                           startActivity(new Intent(getApplicationContext(), home.class));
                           finish();
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(Registro.this, "Error al crear la cuenta.", Toast.LENGTH_SHORT).show();
                       }
                   });
               }
           }
       });


        // Redirigir a la pantalla de inicio
        Iniciar = findViewById(R.id.ct_iniciar);

        Iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Iniciar = new Intent(Registro.this, login.class);
                startActivity(Iniciar);
            }
        });
    }

    public boolean checkField(EditText textField){
        if (textField.getText().toString().isEmpty()){
            textField.setError("Llene todos los campos");
            valid = false;
        } else {
            valid = true;
        }

        return valid;
    }

    // Funcion para ocultar teclado
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}