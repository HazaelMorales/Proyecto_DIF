package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class area_trabajosocial extends AppCompatActivity implements View.OnClickListener {
    Button nuevobene2,seguimiento2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_trabajosocial);
        nuevobene2 = (Button) findViewById(R.id.nuevobene2);
        seguimiento2 = (Button) findViewById(R.id.seguimiento2);
        nuevobene2.setOnClickListener(this);
        seguimiento2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.nuevobene2:
                Intent i = new Intent(area_trabajosocial.this,registro_trabajo_social.class);
                startActivity(i);
                break;
            case R.id.seguimiento2:
                Intent j = new Intent(area_trabajosocial.this,seguimiento_trabajosocial.class);
                startActivity(j);
                break;
        }
    }
}