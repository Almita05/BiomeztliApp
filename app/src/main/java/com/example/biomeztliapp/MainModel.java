package com.example.biomeztliapp;

public class MainModel {

    private String descripcion;
    private String nombre;
    private String precaucion;
    private String propiedades;
    private String uso;
    private String imagen;

    // Constructor por defecto requerido para Firebase
    public MainModel() {
    }

    public MainModel(String descripcion, String nombre, String precaucion, String propiedades, String uso, String imagen) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.precaucion = precaucion;
        this.propiedades = propiedades;
        this.uso = uso;
        this.imagen = imagen;
    }

    // Métodos getter y setter para cada propiedad

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecaucion() {
        return precaucion;
    }

    public void setPrecaucion(String precaucion) {
        this.precaucion = precaucion;
    }

    public String getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(String propiedades) {
        this.propiedades = propiedades;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
