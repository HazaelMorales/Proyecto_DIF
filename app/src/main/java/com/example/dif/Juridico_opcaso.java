package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Juridico_opcaso extends AppCompatActivity implements View.OnClickListener {
    private String benexd,userxd;
    private Button saddcaso,svercasos,salir,vercasp;
    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(Juridico_opcaso.this,Juridico_verBeneficiario.class);
        i2.putExtra("id_suser",userxd);
        startActivity(i2);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico_opcaso);

        benexd = getIntent().getStringExtra("id_sbene");//variable donde se cargo el valorde la varibale pasada
        userxd = getIntent().getStringExtra( "id_suser");
        saddcaso = (Button) findViewById(R.id.saddcaso);
        svercasos = (Button) findViewById(R.id.ssegcaso);
        vercasp = (Button) findViewById(R.id.svercasito);
        salir = (Button) findViewById(R.id.scerrar);
        saddcaso.setOnClickListener(this);
        svercasos.setOnClickListener(this);
        salir.setOnClickListener(this);
        vercasp.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.saddcaso:
                Intent i2 = new Intent(Juridico_opcaso.this,Juridico_sncaso.class);
                i2.putExtra("id_sbene",benexd);
                i2.putExtra("id_suser",userxd);
                startActivity(i2);
                finish();
                break;
            case R.id.scerrar:
                onBackPressed();
                break;
            case R.id.svercasito:
                Intent i3 = new Intent(Juridico_opcaso.this,Juridico_vercasos.class);
                i3.putExtra("id_sbene",benexd);
                i3.putExtra("id_suser",userxd);
                startActivity(i3);
                finish();
                break;
        }
    }
}