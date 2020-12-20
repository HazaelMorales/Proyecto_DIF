package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class canalizar extends AppCompatActivity implements View.OnClickListener{
Button regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canalizar);
        regresar = (Button) findViewById(R.id.regresar);
        regresar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regresar:
                Intent i = new Intent(canalizar.this,interfazjuridica.class);
                startActivity(i);
                break;
        }
    }
}