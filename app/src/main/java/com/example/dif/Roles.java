package com.example.dif;

public class Roles {
    private int id;
    private String rol;

    public Roles(){}

    public Roles(int id, String rol){
        this.id = id;
        this.rol = rol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRol(String rol){
        this.rol = rol;
    }

    @Override
    public String toString() {
        return rol;
    }
}


