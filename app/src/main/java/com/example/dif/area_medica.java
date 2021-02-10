package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class area_medica extends AppCompatActivity implements View.OnClickListener {

    Button nuevo1,seguir1,salir;
    String id_worker;
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de cerrar sesion?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent a1 = new Intent(area_medica.this, MainActivity.class); //Se crea y abre la activity de login
                        startActivity(a1); // se inicia la activity de login
                        finish(); // se mata la activity de admin

                    }
                }).create().show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_medica);
        nuevo1 = (Button) findViewById(R.id.nuevobene2);
        seguir1 = (Button) findViewById(R.id.seguimiento2);

        nuevo1.setOnClickListener(this);
        seguir1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nuevobene2:
                Intent i = new Intent(area_medica.this,registro_area_medica.class);
                startActivity(i);
                finish();
                break;
            case R.id.seguimiento2:
                Intent j = new Intent(area_medica.this,seguimiento_area_medica.class);
                startActivity(j);
                finish();
                break;
        }

    }
}