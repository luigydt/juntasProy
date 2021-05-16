package com.example.proyandroid;

public class Participante {
    private String nombre;
    private int estado;

    public Participante(String nombre, int estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEstado() {
        return estado;
    }
}
