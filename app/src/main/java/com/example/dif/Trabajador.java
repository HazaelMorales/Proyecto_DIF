package com.example.dif;

public class Trabajador {

    private int id;
    private String nombres;

    public Trabajador(){}

    public Trabajador(int id_usuario, String nombres){
        this.id = id_usuario;
        this.nombres = nombres;
    }

    public void setId(int id_usuario) {
        this.id = id_usuario;
    }

    public void setRol(String nombres){
        this.nombres = nombres;
    }

    @Override
    public String toString() {
        return nombres;
    }
}