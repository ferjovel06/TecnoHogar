package com.example.tecnohogar;

public class User {
    public String nombre;
    public String usuario;
    public String email;
    public String telefono;
    public String password;

    // Constructor
    public User() {

    }

    public User (String nombre, String usuario, String email, String telefono, String password) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }
}
