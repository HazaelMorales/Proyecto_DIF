package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity implements View.OnClickListener{
    Button registrar,verxd,eladios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        registrar = (Button) findViewById(R.id.btnrgs);
        verxd = (Button) findViewById(R.id.btnuslist);
        eladios = (Button) findViewById(R.id.btnbye);
        registrar.setOnClickListener(this);
        verxd.setOnClickListener(this);
        eladios.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnrgs:
                Intent a = new Intent(admin.this, registro.class);
                startActivity(a);
                break;
            case R.id.btnuslist:
                Intent a1 = new Intent(admin.this, trabajadores.class);
                startActivity(a1);
                break;
            case R.id.btnbye:
                Intent a2 = new Intent(admin.this, MainActivity.class);
                startActivity(a2);
                break;
        }
    }
}