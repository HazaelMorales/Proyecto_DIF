package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class General_Dir extends AppCompatActivity implements View.OnClickListener {
    Button medica,juridica,psicologica,TSocial,menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general__dir);
        medica = (Button) findViewById(R.id.btnmed);
        juridica = (Button) findViewById(R.id.btnjur);
        psicologica = (Button) findViewById(R.id.btnpsi);
        TSocial = (Button) findViewById(R.id.btnsoc);
        menu = (Button) findViewById(R.id.btnmen);
        medica.setOnClickListener(this);
        juridica.setOnClickListener(this);
        psicologica.setOnClickListener(this);
        TSocial.setOnClickListener(this);
        menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnmed:
                Intent a = new Intent(General_Dir.this, General_Dir.class);
                startActivity(a);
                break;
            case R.id.btnjur:
                Intent a1 = new Intent(General_Dir.this, General_Dir.class);
                startActivity(a1);
                break;
            case R.id.btnpsi:
                Intent a2 = new Intent(General_Dir.this, General_Dir.class);
                startActivity(a2);
                break;
            case R.id.btnsoc:
                Intent a3 = new Intent(General_Dir.this, General_Dir.class);
                startActivity(a3);
                break;
            case R.id.btnmen:
                Intent a4 = new Intent(General_Dir.this, general.class);
                startActivity(a4);
                break;
        }
    }
}