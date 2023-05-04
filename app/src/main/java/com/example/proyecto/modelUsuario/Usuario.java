package com.example.proyecto.modelUsuario;

public class Usuario {
    /*apellido
"apellido1"
(string)
password
"contraseña2"
correo
"correo@correo.com"
nombre
"nombre1"
rol
"manager"*/
    String apellido;
    String password;
    String correo;
    String nombre;
    String rol;

    String direccion;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario(){
    }

    public Usuario(String password, String apellido,  String correo,String direccion,String nombre, String rol) {
        this.apellido = apellido;
        this.password = password;
        this.correo = correo;
        this.nombre = nombre;
        this.rol = rol;
        this.direccion=direccion;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
