package com.example.dif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class interfazjuridica extends AppCompatActivity implements View.OnClickListener{
 Button nuevo,seguir,salir;
    @Override
    public void onBackPressed() {
        Intent a1 = new Intent(interfazjuridica.this,areajuridica.class);
        startActivity(a1);
        finish();//matar  activity actual xd
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfazjuridica);
        nuevo = (Button) findViewById(R.id.nuevobene);
        seguir = (Button) findViewById(R.id.seguimiento);

        salir = (Button) findViewById(R.id.salir);
        nuevo.setOnClickListener(this);
        seguir.setOnClickListener(this);

        salir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nuevobene:
                Intent i = new Intent(interfazjuridica.this,registrobeneficiario.class);
                startActivity(i);
                finish();
                break;
            case R.id.seguimiento:
                Intent j = new Intent(interfazjuridica.this,mostrarbeneficiarios.class);
                startActivity(j);
                finish();
                break;

            case R.id.salir:
                Intent a1 = new Intent(interfazjuridica.this,areajuridica.class);
                startActivity(a1);
                finish();
                break;
        }

    }


}