package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class areajuridicaseguimiento extends AppCompatActivity implements View.OnClickListener{
    Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areajuridicaseguimiento);
        aceptar = (Button) findViewById(R.id.aceptar);
        aceptar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aceptar:
                Intent i = new Intent(areajuridicaseguimiento.this,mostrarbeneficiarios.class);
                startActivity(i);
                break;
    }}
}