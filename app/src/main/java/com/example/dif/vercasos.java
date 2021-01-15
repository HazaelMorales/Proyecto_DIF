package com.example.dif;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class vercasos extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(vercasos.this,interfazjuridica.class);
        startActivity(i2);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vercasos);
    }
}