package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class vercasos_area_psicologia extends AppCompatActivity {
    TextView fecha_apert, descripcion, estado;
    String fecha,descripcion1,estado1;
    Button back;
    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(vercasos_area_psicologia.this,area_psicologia.class);
        startActivity(i2);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vercasos_area_psicologia);
        SharedPreferences parametrosCaso = getSharedPreferences("caso", Context.MODE_PRIVATE);

        fecha_apert = (TextView) findViewById(R.id.fapertura);
        descripcion = (TextView) findViewById(R.id.descripcion);
        back = (Button) findViewById(R.id.registrarcaso);

        fecha = parametrosCaso.getString("fecha_apertura","");
        descripcion1 = parametrosCaso.getString("descripcion","");

        fecha_apert.setText(fecha);
        descripcion.setText(descripcion1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(vercasos_area_psicologia.this,DatosPsicologia.class);
                startActivity(i);
                finish();
            }
        });
    }
}