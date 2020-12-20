package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class general extends AppCompatActivity implements View.OnClickListener{
    Button buscar,contactar,salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        buscar =(Button)  findViewById(R.id.btnsearch);
        contactar =(Button)  findViewById(R.id.btndir);
        salir =(Button)  findViewById(R.id.btnbye);
        buscar.setOnClickListener(this);
        contactar.setOnClickListener(this);
        salir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsearch:
                Intent a = new Intent(general.this, registro.class);
                startActivity(a);
                break;
            case R.id.btndir:
                Intent a1 = new Intent(general.this, General_Dir.class);
                startActivity(a1);
                break;
            case R.id.btnbye:
                Intent a2 = new Intent(general.this, MainActivity.class);
                startActivity(a2);
                break;
        }

    }
}