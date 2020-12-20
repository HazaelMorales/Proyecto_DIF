package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class areasocial extends AppCompatActivity implements View.OnClickListener{
    Button secre2,dir2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areasocial);
        secre2 = (Button) findViewById(R.id.secre2);
        dir2 = (Button) findViewById(R.id.direc2);
        secre2.setOnClickListener(this);
        dir2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.secre2:
                Intent i = new Intent(areasocial.this,mostrarbeneficiarios.class);
                startActivity(i);
                break;
            case R.id.direc2:
                Intent j = new Intent(areasocial.this,interfazsocial.class);
                startActivity(j);
                break;
        }
    }
}