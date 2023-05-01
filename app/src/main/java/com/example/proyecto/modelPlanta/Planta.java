package com.example.proyecto.modelPlanta;

public class Planta {

    private String i_perfil;
    private String images;
    private String nombre;
    private String precio;
    private String recomendacion;
    private String stock;

    public Planta(){};

    public Planta(String i_perfil, String images, String nombre, String precio, String recomendacion, String stock) {
        this.i_perfil = i_perfil;
        this.images = images;
        this.nombre = nombre;
        this.precio = precio;
        this.recomendacion = recomendacion;
        this.stock = stock;
    }

    public String getI_perfil() {
        return i_perfil;
    }

    public void setI_perfil(String i_perfil) {
        this.i_perfil = i_perfil;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
