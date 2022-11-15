package com.example.tecnohogar.models;

public class ElectricistaModel {
    String nombre;
    String descripcion;
    String horario;
    String rating;
    String img_url;

    public ElectricistaModel() {
    }

    public ElectricistaModel(String nombre, String descripcion, String horario, String rating, String img_url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.rating = rating;
        this.img_url = img_url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
