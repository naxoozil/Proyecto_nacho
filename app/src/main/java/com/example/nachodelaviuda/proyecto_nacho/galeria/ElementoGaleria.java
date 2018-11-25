package com.example.nachodelaviuda.proyecto_nacho.galeria;

public class ElementoGaleria {

    private String nombre;
    private String foto;

    public ElementoGaleria() {
    }

    public ElementoGaleria(String foto, String nombre) {

        this.foto = foto;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
