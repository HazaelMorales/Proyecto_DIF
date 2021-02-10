package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
//import com.example.dif.funciones.Spinner1;
//import com.example.dif.funciones.Spinner2;
//import com.example.dif.funciones.Spinner3;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class estudio_socioeconomico extends AppCompatActivity implements View.OnClickListener {
    //private AsyncHttpClient cliente1;
    //private AsyncHttpClient cliente2;
    //private AsyncHttpClient cliente3;
    //Spinner spinner1, spinner2, spinner3;
    TextView user, cases, bene;
    EditText ingresom, incap, enfer, totalgas, sermact, tipovivi, servact, support, diag, obser, esta;
    Button registrar;
    public int id_caso,id_beneficiario,id_usuario;
    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(estudio_socioeconomico.this,area_trabajosocial.class);
        startActivity(i2);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudio_socioeconomico);
        //cliente1 = new AsyncHttpClient();
        //cliente2 = new AsyncHttpClient();
        //cliente3 = new AsyncHttpClient();
        //spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner2 = (Spinner) findViewById(R.id.spinner2);
        //spinner3 = (Spinner) findViewById(R.id.spinner3);
        user = (EditText) findViewById(R.id.user);
        cases = (EditText) findViewById(R.id.cases);
        bene = (EditText) findViewById(R.id.bene);
        ingresom = (EditText) findViewById(R.id.ingresom);
        incap = (EditText) findViewById(R.id.incap);
        enfer = (EditText) findViewById(R.id.enfer);
        totalgas = (EditText) findViewById(R.id.totalgas);
        sermact = (EditText) findViewById(R.id.sermact);
        tipovivi = (EditText) findViewById(R.id.tipovivi);
        servact = (EditText) findViewById(R.id.servact);
        support = (EditText) findViewById(R.id.support);
        diag = (EditText) findViewById(R.id.diag);
        obser = (EditText) findViewById(R.id.obser);
        esta = (EditText) findViewById(R.id.esta);
        registrar = (Button) findViewById(R.id.registrar);
        registrar.setOnClickListener(this);
        //usuarioSpinner();
        //casoSpinner();
        //beneficiarioSpinner();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrar:
                insertar_es();
                Intent l = new Intent(estudio_socioeconomico.this, gastos_familiares.class);
                startActivity(l);
                break;
        }

    }

    private void insertar_es() {
        //SharedPreferences ponysUsuario = getSharedPreferences("Ponys", Context.MODE_PRIVATE);
        //String usu = ponysUsuario.getString("id_usuario","");
        //user.setText(usu);
        //SharedPreferences parametrosCaso = getSharedPreferences("caso", Context.MODE_PRIVATE);
        //String cas = parametrosCaso.getString("id_caso","");
        //cases.setText(cas);
        //SharedPreferences ponysBeneficiario = getSharedPreferences("Ponys2", Context.MODE_PRIVATE);
        //String ben = ponysBeneficiario.getString("id_beneficiario","");
        //bene.setText(ben);
        String usu = user.getText().toString().trim();
        String cas = cases.getText().toString().trim();
        String ben = bene.getText().toString().trim();
        String ingresomensual = ingresom.getText().toString().trim();
        String incapacitado = incap.getText().toString().trim();
        String enfermedades = enfer.getText().toString().trim();
        String totalgastos = totalgas.getText().toString().trim();
        String serviciomactual = sermact.getText().toString().trim();
        String tipovivienda = tipovivi.getText().toString().trim();
        String servicioactual = servact.getText().toString().trim();
        String apoyosolicitado = support.getText().toString().trim();
        String diagnosticos = diag.getText().toString().trim();
        String observacion = obser.getText().toString().trim();
        String estados = esta.getText().toString().trim();


        ProgressDialog progressDialog = new ProgressDialog(this);
        if (usu.isEmpty()){
            user.setError("Complete los campos");
        }else if (cas.isEmpty()){
            cases.setError("Complete los campos");
        }else if (ben.isEmpty()){
            bene.setError("Complete los campos");
        }else if(ingresomensual.isEmpty()){
            ingresom.setError("Complete los campos");
        }else if(incapacitado.isEmpty()){
            incap.setError("Complete los campos");
        }else if(enfermedades.isEmpty()){
            enfer.setError("Complete los campos");
        }else if(totalgastos.isEmpty()){
            totalgas.setError("Complete los campos");
        }else if(serviciomactual.isEmpty()){
            sermact.setError("Complete los campos");
        }else if(tipovivienda.isEmpty()){
            tipovivi.setError("Complete los campos");
        }else if(servicioactual.isEmpty()){
            servact.setError("Complete los campos");
        }else if(apoyosolicitado.isEmpty()){
            support.setError("Complete los campos");
        }else if(diagnosticos.isEmpty()){
            diag.setError("Complete los campos");
        }else if(observacion.isEmpty()){
            obser.setError("Complete los campos");
        }else if(estados.isEmpty()){
            esta.setError("Complete los campos");
        }
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/estudio_socioeconomico.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(estudio_socioeconomico.this,"Datos Ingresados Correctamente",Toast.LENGTH_LONG).show();
                        Intent a6 = new Intent(estudio_socioeconomico.this, area_trabajosocial.class);
                        startActivity(a6);
                        finish();
                    } else {
                        Toast.makeText(estudio_socioeconomico.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(estudio_socioeconomico.this, "Datos Ingresados Incorrectamente",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String ,String> params = new HashMap<String,String>();
                    params.put("usuario", usu);
                    params.put("caso", cas);
                    params.put("beneficiario", ben);
                    params.put("ingreso_mensual",ingresomensual);
                    params.put("incapacidad",incapacitado);
                    params.put("enfermedad",enfermedades);
                    params.put("total_gastos",totalgastos);
                    params.put("servicio_mactual",serviciomactual);
                    params.put("tipo_vivienda",tipovivienda);
                    params.put("servicio_actual",servicioactual);
                    params.put("apoyo_solicitado",apoyosolicitado);
                    params.put("diagnostico",diagnosticos);
                    params.put("observaciones",observacion);
                    params.put("estado",estados);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(estudio_socioeconomico.this);
            requestQueue.add(request);

        }
    }


}