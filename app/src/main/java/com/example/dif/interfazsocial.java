package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class interfazsocial extends AppCompatActivity implements View.OnClickListener{
    Button nuevo,seguir,canalizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfazsocial);
        nuevo = (Button) findViewById(R.id.nuevobene2);
        seguir = (Button) findViewById(R.id.seguimiento2);
        canalizar = (Button) findViewById(R.id.canalizar2);
        nuevo.setOnClickListener(this);
        seguir.setOnClickListener(this);
        canalizar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nuevobene2:
                Intent i = new Intent(interfazsocial.this,registrobeneficiario.class);
                startActivity(i);
                break;
            case R.id.seguimiento2:
                Intent j = new Intent(interfazsocial.this,mostrarbeneficiarios.class);
                startActivity(j);
                break;
            case R.id.canalizar2:
                Intent k = new Intent(interfazsocial.this,canalizar.class);
                startActivity(k);
                break;
        }

    }
}