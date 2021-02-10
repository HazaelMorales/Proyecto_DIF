package com.example.dif;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class info_area_psicologica extends AppCompatActivity implements View.OnClickListener{
    TextView txtName, txtCurp, txtTelefono, txtEstado, txtMunicipio, txtDomicilio, txtSexo, txtFecha_Nacimiento, txtLugar_Nacimiento, txtFecha_Registro, txtEstadoCivil, txtEscolaridad , txtNombre_Escuela ,txtOcupacion;
    Button aceptar,addnote,vercasos,DatosPsicologicos;
    String valName,valApellidoMa,valApellidoPa,valCurp,valTelefono,valEstado,valMunicipio,valDomicilio,valSexo,valFechaNacimiento,valLugarNacimiento,valFechaRegistro,valEstadoCivil,valEscolaridad,valEscuela,valOcupacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_area_psicologica);

        SharedPreferences preferencias = getSharedPreferences("beneficiario", Context.MODE_PRIVATE);

        txtName = (TextView) findViewById(R.id.beneFullName);
        txtCurp = (TextView) findViewById(R.id.beneCurp);
        txtTelefono = (TextView) findViewById(R.id.beneNumero);
        txtEstado = (TextView) findViewById(R.id.beneEstado);
        txtMunicipio = (TextView) findViewById(R.id.beneMunicipio);
        txtDomicilio = (TextView) findViewById(R.id.beneAddress);
        txtSexo = (TextView) findViewById(R.id.beneSexo);
        txtFecha_Nacimiento = (TextView) findViewById(R.id.beneFechaNacimiento);
        txtLugar_Nacimiento = (TextView) findViewById(R.id.beneLugarNacimiento);
        txtFecha_Registro = (TextView) findViewById(R.id.beneFechaRegistro);
        txtEstadoCivil = (TextView) findViewById(R.id.beneEstadoCivil);
        txtEscolaridad = (TextView) findViewById(R.id.beneEscolaridad);
        txtNombre_Escuela = (TextView) findViewById(R.id.beneEscuela);
        txtOcupacion = (TextView) findViewById(R.id.beneOcupacion);

        aceptar = (Button) findViewById(R.id.aceptar);
        addnote = (Button) findViewById(R.id.addnotes);
        vercasos=(Button) findViewById(R.id.viewnotes);
        DatosPsicologicos =(Button) findViewById(R.id.verDatosPsicologicos);

        aceptar.setOnClickListener(this);
        addnote.setOnClickListener(this);
        vercasos.setOnClickListener(this);
        DatosPsicologicos.setOnClickListener(this);

        valName = preferencias.getString("nombre","");
        valApellidoPa = preferencias.getString("apellidoPa","");
        valApellidoMa = preferencias.getString("apellidoMa","");
        valCurp = preferencias.getString("benCurp","");
        valTelefono = preferencias.getString("benTel","");
        valEstado = preferencias.getString("benEstado","");
        valMunicipio = preferencias.getString("benMuni","");
        valDomicilio = preferencias.getString("benAdd","");
        valSexo = preferencias.getString("benSex","");
        valFechaNacimiento = preferencias.getString("benFecha","");
        valLugarNacimiento = preferencias.getString("benLugar","");
        valFechaRegistro = preferencias.getString("benFecha2","");
        valEstadoCivil = preferencias.getString("benCivil","");
        valEscolaridad = preferencias.getString("benEscolaridad","");
        valEscuela = preferencias.getString("benEscuela","");
        valOcupacion = preferencias.getString("benOcup","");


        txtName.setText(valName+" "+ valApellidoPa + " " + valApellidoMa);
        txtCurp.setText(valCurp);
        txtTelefono.setText(valTelefono);
        txtEstado.setText(valEstado);
        txtMunicipio.setText(valMunicipio);
        txtDomicilio.setText(valDomicilio);
        txtSexo.setText(valSexo);
        txtFecha_Nacimiento.setText(valFechaNacimiento);
        txtLugar_Nacimiento.setText(valLugarNacimiento);
        txtFecha_Registro.setText(valFechaRegistro);
        txtEstadoCivil.setText(valEstadoCivil);
        txtEscolaridad.setText(valEscolaridad);
        txtNombre_Escuela.setText(valEscuela);
        txtOcupacion.setText(valOcupacion);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aceptar:
                Intent i = new Intent(info_area_psicologica.this,area_psicologia.class);
                startActivity(i);
                finish();
                break;
            case R.id.addnotes:
                Intent i1 = new Intent(info_area_psicologica.this,addcase_area_psicologica.class);
                startActivity(i1);
                finish();
                break;
            case R.id.viewnotes:
                Intent i3 = new Intent(info_area_psicologica.this, ListaCasosPsicologia.class);
                startActivity(i3);
                finish();
                break;
            case R.id.verDatosPsicologicos:
                Intent i4 = new Intent(info_area_psicologica.this, MostrarDatosPsicologicos.class);
                startActivity(i4);
                finish();
                break;
        }}
    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(info_area_psicologica.this,area_psicologia.class);
        startActivity(i2);
        finish();
    }

}