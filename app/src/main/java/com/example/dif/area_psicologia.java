package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class area_psicologia extends AppCompatActivity implements View.OnClickListener{
    Button nuevobene2,canalizar2,seguimiento2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_psicologia);
        nuevobene2 = (Button) findViewById(R.id.nuevobene2);
        canalizar2 = (Button) findViewById(R.id.nuevobene2);
        seguimiento2 = (Button) findViewById(R.id.seguimiento2);
        nuevobene2.setOnClickListener(this);
        canalizar2.setOnClickListener(this);
        seguimiento2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nuevobene2:
                Intent i = new Intent(area_psicologia.this,registro_area_psicologica.class);
                startActivity(i);
                break;
            case R.id.canalizar2:
                Intent a = new Intent(area_psicologia.this,canalizar_area_psicologica.class);
                startActivity(a);
                break;
            case R.id.seguimiento2:
                Intent l = new Intent(area_psicologia.this,seguimiento_psicologia.class);
                startActivity(l);
                break;
        }
    }
}