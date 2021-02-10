package com.example.dif;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class vercasos extends AppCompatActivity implements View.OnClickListener {
    TextView fecha_apert, descripcion, estado;
    String fecha,descripcion1,estado1;
    Button agregar_reporte,ver_reporte;
    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(vercasos.this,ListaCasos.class);
        startActivity(i2);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vercasos);
        SharedPreferences parametrosCaso = getSharedPreferences("caso", Context.MODE_PRIVATE);

        fecha_apert = (TextView) findViewById(R.id.fapertura);
        descripcion = (TextView) findViewById(R.id.descripcion);
        estado = (TextView) findViewById(R.id.estado);
        agregar_reporte = (Button) findViewById(R.id.Agregar_reporte_medico);


        fecha = parametrosCaso.getString("fecha_apertura","");
        descripcion1 = parametrosCaso.getString("descripcion","");

        fecha_apert.setText(fecha);
        descripcion.setText(descripcion1);

        agregar_reporte.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Agregar_reporte_medico:
                Intent i = new Intent(vercasos.this,Datos_Medicos.class);
                startActivity(i);
                finish();
                break;
        }
    }
}