package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class interfazjuridica extends AppCompatActivity implements View.OnClickListener{
 Button nuevo,seguir,canalizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfazjuridica);
        nuevo = (Button) findViewById(R.id.nuevobene);
        seguir = (Button) findViewById(R.id.seguimiento);
        canalizar = (Button) findViewById(R.id.canalizar);
        nuevo.setOnClickListener(this);
        seguir.setOnClickListener(this);
        canalizar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nuevobene:
                Intent i = new Intent(interfazjuridica.this,registrobeneficiario.class);
                startActivity(i);
                break;
            case R.id.seguimiento:
                Intent j = new Intent(interfazjuridica.this,mostrarbeneficiarios.class);
                startActivity(j);
                break;
            case R.id.canalizar:
                Intent k = new Intent(interfazjuridica.this,canalizar.class);
                startActivity(k);
                break;
        }

    }
}