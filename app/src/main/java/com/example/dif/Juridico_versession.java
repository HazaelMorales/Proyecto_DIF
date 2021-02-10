package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Juridico_versession extends AppCompatActivity {
    TextView mcaso,mbene,mestado,mfecha,mproc,mrele,mtele,malte,mactual,msegui,mdetalla,mpent;
    String b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
    String userxd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico_versession);
        mcaso = (TextView) findViewById(R.id.fcaso);
        mbene= (TextView) findViewById(R.id.fbeneficiario);
        mestado= (TextView) findViewById(R.id.festado);
        mfecha= (TextView) findViewById(R.id.ffecha);
        mproc= (TextView) findViewById(R.id.fprocedencia);
        mrele= (TextView) findViewById(R.id.frele);
        mtele= (TextView) findViewById(R.id.ftelefono);
        malte= (TextView) findViewById(R.id.ftelefonoalter);
        mactual= (TextView) findViewById(R.id.fproceso);
        msegui= (TextView) findViewById(R.id.fseguimiento);
        mdetalla= (TextView) findViewById(R.id.fdetallada);
        mpent= (TextView) findViewById(R.id.fpendiente);

        userxd = getIntent().getStringExtra("id_suser");

        b1 = getIntent().getStringExtra("a1");
        b2 = getIntent().getStringExtra("a2");
        b3 = getIntent().getStringExtra("a3");
        b4 = getIntent().getStringExtra("a4");
        b5 = getIntent().getStringExtra("a5");
        b6 = getIntent().getStringExtra("a6");
        b7 = getIntent().getStringExtra("a7");
        b8 = getIntent().getStringExtra("a8");
        b9 = getIntent().getStringExtra("a9");
        b10 = getIntent().getStringExtra("a10");
        b11 = getIntent().getStringExtra("a11");
        b12 = getIntent().getStringExtra("a12");

        mcaso.setText(b1);
        mbene.setText(b2);
        mestado.setText(b3);
        mfecha.setText(b4);
        mproc.setText(b5);
        mrele.setText(b6);
        mtele.setText(b7);
        malte.setText(b8);
        mactual.setText(b9);
        msegui.setText(b10);
        mdetalla.setText(b11);
        mpent.setText(b12);




    }
    public void onBackPressed() {
        Intent a1 = new Intent(Juridico_versession.this,Juridico_Vcasos.class);
        a1.putExtra("id_suser",userxd);
        startActivity(a1);
        finish();//matar  activity actual xd
    }
}