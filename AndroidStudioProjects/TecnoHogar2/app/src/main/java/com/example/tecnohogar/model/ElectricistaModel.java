package com.example.tecnohogar.model;

public class ElectricistaModel {

    String elName, elDes, elHorario;

    public ElectricistaModel(){}

    public ElectricistaModel(String elName, String elDes, String elHorario) {
        this.elName = elName;
        this.elDes = elDes;
        this.elHorario = elHorario;
    }

    public String getElName() {
        return elName;
    }

    public void setElName(String elName) {
        this.elName = elName;
    }

    public String getElDes() {
        return elDes;
    }

    public void setElDes(String elDes) {
        this.elDes = elDes;
    }

    public String getElHorario() {
        return elHorario;
    }

    public void setElHorario(String elHorario) {
        this.elHorario = elHorario;
    }
}
