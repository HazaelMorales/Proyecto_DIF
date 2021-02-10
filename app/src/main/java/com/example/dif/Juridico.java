package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Juridico extends AppCompatActivity implements View.OnClickListener{
    private Button sverbene,srbeneficiario,sversesion,ssalir;
    String userxd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico);
        sverbene = (Button) findViewById(R.id.sverbene);
        srbeneficiario = (Button) findViewById(R.id.snbeneficiario);
        sversesion = (Button) findViewById(R.id.svsesion);
        ssalir = (Button) findViewById(R.id.scerrar);//Union de Botones con sus respectivos ID
        sverbene.setOnClickListener(this);
        srbeneficiario.setOnClickListener(this);
        sversesion.setOnClickListener(this);
        ssalir.setOnClickListener(this);// Implementacion del metodo Onclick de los botones de esta clase
        userxd = getIntent().getStringExtra("id_suser");
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de cerrar sesion?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent a1 = new Intent(Juridico.this, MainActivity.class); //Se crea y abre la activity de login
                        startActivity(a1); // se inicia la activity de login
                        finish(); // se mata la activity de admin

                    }
                }).create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.svsesion:
                Intent a1 = new Intent(Juridico.this,Juridico_Vcasos.class);
                a1.putExtra("id_suser",userxd);
                startActivity(a1);
                finish();
                break;
            case R.id.snbeneficiario:
                Intent a2 = new Intent(Juridico.this,Juridico_RBeneficiario.class);
                a2.putExtra("id_suser",userxd);
                startActivity(a2);
                finish();
                break;
            case R.id.sverbene:
                Intent a3 = new Intent(Juridico.this,Juridico_verBeneficiario.class);
                a3.putExtra("id_suser",userxd);
                startActivity(a3);
                finish();
                break;
            case R.id.scerrar:
                onBackPressed();
                break;
        }

    }
}