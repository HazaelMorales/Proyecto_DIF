package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                break;
            case R.id.btnbye:
                Intent j = new Intent(areajuridica.this,MainActivity.class);
                startActivity(j);
                break;
        }
    }
}