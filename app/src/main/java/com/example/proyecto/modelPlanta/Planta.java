package com.example.proyecto.modelPlanta;

import java.util.List;

public class Planta {

    private String i_perfil;
    private String nombre;
    private String precio;
    private String recomendacion;
    private String stock;

    private List<String> l_adicionales;

    public Planta(){};

    public Planta(String i_perfil, List<String> l_adicionales, String nombre, String precio, String recomendacion, String stock) {
        this.i_perfil = i_perfil;
        this.l_adicionales = l_adicionales;
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


    public List<String> getL_adicionales() {
        return l_adicionales;
    }

    public void setL_adicionales(List<String> l_adicionales) {
        this.l_adicionales = l_adicionales;
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
