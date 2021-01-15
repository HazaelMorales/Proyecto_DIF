package com.example.dif;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class areajuridica extends AppCompatActivity implements View.OnClickListener{
    Button salir,dir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areajuridica);
        salir = (Button) findViewById(R.id.btnbye);
        dir = (Button) findViewById(R.id.direc);
        salir.setOnClickListener(this);
        dir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.direc:
                Intent i = new Intent(areajuridica.this,interfazjuridica.class);
                startActivity(i);
                finish();
                break;
            case R.id.btnbye:
                Intent j = new Intent(areajuridica.this,MainActivity.class);
                startActivity(j);
                finish();
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
                        Intent a1 = new Intent(areajuridica.this, MainActivity.class); //Se crea y abre la activity de login
                        startActivity(a1); // se inicia la activity de login
                        finish(); // se mata la activity de admin

                    }
                }).create().show();
    }
}