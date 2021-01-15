package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity implements View.OnClickListener{
    Button registrar,verxd,eladios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        registrar = (Button) findViewById(R.id.btnrgs);
        verxd = (Button) findViewById(R.id.btnuslist);
        eladios = (Button) findViewById(R.id.btnbye);
        registrar.setOnClickListener(this);
        verxd.setOnClickListener(this);
        eladios.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnrgs:
                Intent a = new Intent(admin.this, registro.class);
                startActivity(a);//Se crea y abre la nueva pantalla de registro de trabajadores
                finish();// se mata la pantalla actual de admin
                break;
            case R.id.btnuslist:
                Intent a1 = new Intent(admin.this, trabajadores.class);
                startActivity(a1); //Se crea y abre la nueva pantalla de trabajadores
                finish(); // se mata la pantalla actual de admin
                break;
            case R.id.btnbye:
                onBackPressed(); //Al precionar el boton de cerrar sesion o el boton de regresar
                                // se cierra abre el activity de login
                break;
        }
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de cerrar sesion?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent a1 = new Intent(admin.this, MainActivity.class); //Se crea y abre la activity de login
                        startActivity(a1); // se inicia la activity de login
                        finish(); // se mata la activity de admin

                    }
                }).create().show();
    }
}