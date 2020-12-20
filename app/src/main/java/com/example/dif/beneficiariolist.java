package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class beneficiariolist extends AppCompatActivity implements View.OnClickListener {
    private String id_beneficiario;
    private String nombres;
    private String AP;
    private String AM;
    private String curp;
    private String telefono;
    private String estado;
    private String municipio;
    private String domicilio;
    private String sexo;
    private SimpleDateFormat fecha_nacimiento;
    private String lugar_nacimiento;
    private SimpleDateFormat fecha_registro;
    private String estado_civil;
    private String escolaridad;
    private String nombre_escuela;
    private String ocupacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarbeneficiarios);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
    public beneficiariolist(String id_beneficiario, String nombres, String AP, String AM, String curp, String telefono, String estado, String municipio, String domicilio, String sexo, SimpleDateFormat fecha_nacimiento, String lugar_nacimiento, SimpleDateFormat fecha_registro, String estado_civil, String escolaridad, String nombre_escuela, String ocupacion, Button aceptar) {
        this.id_beneficiario = id_beneficiario;
        this.nombres = nombres;
        this.AP = AP;
        this.AM = AM;
        this.curp = curp;
        this.telefono = telefono;
        this.estado = estado;
        this.municipio = municipio;
        this.domicilio = domicilio;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.lugar_nacimiento = lugar_nacimiento;
        this.fecha_registro = fecha_registro;
        this.estado_civil = estado_civil;
        this.escolaridad = escolaridad;
        this.nombre_escuela = nombre_escuela;
        this.ocupacion = ocupacion;
    }

    public String getId_beneficiario() {
        return id_beneficiario;
    }

    public void setId_beneficiario(String id_beneficiario) {
        this.id_beneficiario = id_beneficiario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAP() {
        return AP;
    }

    public void setAP(String AP) {
        this.AP = AP;
    }

    public String getAM() {
        return AM;
    }

    public void setAM(String AM) {
        this.AM = AM;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public SimpleDateFormat getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(SimpleDateFormat fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getLugar_nacimiento() {
        return lugar_nacimiento;
    }

    public void setLugar_nacimiento(String lugar_nacimiento) {
        this.lugar_nacimiento = lugar_nacimiento;
    }

    public SimpleDateFormat getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(SimpleDateFormat fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getNombre_escuela() {
        return nombre_escuela;
    }

    public void setNombre_escuela(String nombre_escuela) {
        this.nombre_escuela = nombre_escuela;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

}
