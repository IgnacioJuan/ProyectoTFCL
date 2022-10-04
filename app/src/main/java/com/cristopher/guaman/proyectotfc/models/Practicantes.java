package com.cristopher.guaman.proyectotfc.models;

public class Practicantes {
    public String cedula;
    public String nombre_completo;
    public String car_descripcion;
    public String per_correo;

    public Practicantes(String cedula, String nombre_completo, String car_descripcion, String per_correo) {
        this.cedula = cedula;
        this.nombre_completo = nombre_completo;
        this.car_descripcion = car_descripcion;
        this.per_correo = per_correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getCar_descripcion() {
        return car_descripcion;
    }

    public void setCar_descripcion(String car_descripcion) {
        this.car_descripcion = car_descripcion;
    }

    public String getPer_correo() {
        return per_correo;
    }

    public void setPer_correo(String per_correo) {
        this.per_correo = per_correo;
    }
}


