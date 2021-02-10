package com.example.dif;

public class estados {
    private int id;
    private String estado;

    public estados(){}

    public estados(int id, String estado){
        this.id = id;
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    @Override
    public String toString() {
        return estado;
    }
}
