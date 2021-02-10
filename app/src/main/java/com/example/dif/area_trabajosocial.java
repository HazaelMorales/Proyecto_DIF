package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class area_trabajosocial extends AppCompatActivity implements View.OnClickListener {
    Button nuevobene2,seguimiento2,exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_trabajosocial);
        nuevobene2 = (Button) findViewById(R.id.nuevobene2);
        seguimiento2 = (Button) findViewById(R.id.seguimiento2);
        //es = (Button) findViewById(R.id.es);
        exit = (Button) findViewById(R.id.exit);
        nuevobene2.setOnClickListener(this);
        seguimiento2.setOnClickListener(this);
        //es.setOnClickListener(this);
        exit.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de salir registro del área sin cerrar sesión?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(area_trabajosocial.this,MainActivity.class);
                        startActivity(i);
                        finish();//cerrar la aplicacion xd
                    }
                }).create().show();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.nuevobene2:
                Intent i = new Intent(area_trabajosocial.this,registro_trabajo_social.class);
                startActivity(i);
                break;
            case R.id.seguimiento2:
                Intent j = new Intent(area_trabajosocial.this,seguimiento_trabajosocial.class);
                startActivity(j);
                break;
            //case R.id.es:
            //    Intent l = new Intent(area_trabajosocial.this,ListaCasos.class);
            //    startActivity(l);
            //    break;
            case R.id.exit:
                Intent e = new Intent(area_trabajosocial.this,MainActivity.class);
                startActivity(e);
                finish();//cerrar la aplicacion xd

                break;
        }
    }
}