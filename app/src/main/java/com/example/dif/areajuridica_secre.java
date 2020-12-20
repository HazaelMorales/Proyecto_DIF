package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class areajuridica_secre extends AppCompatActivity implements View.OnClickListener {

    Button salir,secre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areajuridica_secre);
        salir = (Button) findViewById(R.id.btnbye);
        secre = (Button) findViewById(R.id.secre);
        salir.setOnClickListener(this);
        secre.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.secre:
                Intent i = new Intent(areajuridica_secre.this,mostrarbene_secre.class);
                startActivity(i);
                break;
            case R.id.btnbye:
                Intent j = new Intent(areajuridica_secre.this,MainActivity.class);
                startActivity(j);
                break;
        }
    }
}