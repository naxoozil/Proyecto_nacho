package com.example.nachodelaviuda.proyecto_nacho;

public class ElementoLista {

    private String nombre;
    private String lugar;
    private String imagenId;
    private int rate;
    private String descripcion;

    public ElementoLista(){
    }

    public ElementoLista(String nombre, String lugar, String imagenId, int rate,String descripcion) {
        this.nombre     = nombre;
        this.lugar      = lugar;
        this.imagenId   = imagenId;
        this.rate       = rate;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getImagenId() {
        return imagenId;
    }

    public void setImagenId(String imagenId) {
        this.imagenId = imagenId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
