package com.example.dif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class areajuridicaseguimiento extends AppCompatActivity implements View.OnClickListener{
    TextView txtName, txtCurp, txtTelefono, txtEstado, txtMunicipio, txtDomicilio, txtSexo, txtFecha_Nacimiento, txtLugar_Nacimiento, txtFecha_Registro, txtEstadoCivil, txtEscolaridad , txtNombre_Escuela ,txtOcupacion;
    Button aceptar,addnote,canalizar;
    String valName,valApellidoMa,valApellidoPa,valCurp,valTelefono,valEstado,valMunicipio,valDomicilio,valSexo,valFechaNacimiento,valLugarNacimiento,valFechaRegistro,valEstadoCivil,valEscolaridad,valEscuela,valOcupacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areajuridicaseguimiento);

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

        valName = getIntent().getStringExtra("nombre");
        valApellidoPa = getIntent().getStringExtra("apellidoPa");
        valApellidoMa = getIntent().getStringExtra("apellidoMa");
        valCurp = getIntent().getStringExtra("benCurp");
        valTelefono = getIntent().getStringExtra("benTel");
        valEstado = getIntent().getStringExtra("benEstado");
        valMunicipio = getIntent().getStringExtra("benMuni");
        valDomicilio = getIntent().getStringExtra("benAdd");
        valSexo = getIntent().getStringExtra("benSex");
        valFechaNacimiento = getIntent().getStringExtra("benFecha");
        valLugarNacimiento = getIntent().getStringExtra("benLugar");
        valFechaRegistro = getIntent().getStringExtra("benFecha2");
        valEstadoCivil = getIntent().getStringExtra("benCivil");
        valEscolaridad = getIntent().getStringExtra("benEscolaridad");
        valEscuela = getIntent().getStringExtra("benEscuela");
        valOcupacion = getIntent().getStringExtra("benOcup");

        aceptar = (Button) findViewById(R.id.aceptar);
        addnote = (Button) findViewById(R.id.addnotes);
        canalizar= (Button) findViewById(R.id.cnalzar);
        aceptar.setOnClickListener(this);
        addnote.setOnClickListener(this);
        canalizar.setOnClickListener(this);

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
                Intent i = new Intent(areajuridicaseguimiento.this,mostrarbeneficiarios.class);
                startActivity(i);
                break;
            case R.id.addnotes:
                Intent i1 = new Intent(areajuridicaseguimiento.this,casos.class);
                startActivity(i1);
                break;
            case R.id.cnalzar:
                Intent i2 = new Intent(areajuridicaseguimiento.this,canalizar.class);
                startActivity(i2);
                break;
    }}
}