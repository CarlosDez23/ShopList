package com.example.shoplist.modelo;

import android.graphics.Bitmap;

public class Producto {

    private int id;
    private String nombre;
    private Bitmap imagen;

    public Producto() {
    }

    public Producto(int id, String nombre, Bitmap imagen) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Producto(String nombre, Bitmap imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}
