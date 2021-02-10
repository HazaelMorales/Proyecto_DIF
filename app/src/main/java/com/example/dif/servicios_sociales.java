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

public class servicios_sociales extends AppCompatActivity implements View.OnClickListener {
    //private AsyncHttpClient cliente1;
    //private AsyncHttpClient cliente2;
    //private AsyncHttpClient cliente3;
    //Spinner spinner1, spinner2, spinner3;

    EditText service, bene2, canaintra, canainsti, refe, autoridad, acciones, areas, status;
    Button registrarservicio;
    public int id_caso,id_beneficiario,id_usuario;
    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(servicios_sociales.this,gastos_familiares.class);
        startActivity(i2);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios_sociales);
        //cliente1 = new AsyncHttpClient();
        //cliente2 = new AsyncHttpClient();
        //cliente3 = new AsyncHttpClient();
        //spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner2 = (Spinner) findViewById(R.id.spinner2);
        //spinner3 = (Spinner) findViewById(R.id.spinner3);
        service = (EditText) findViewById(R.id.service);
        bene2 = (EditText) findViewById(R.id.bene2);
        canaintra = (EditText) findViewById(R.id.canaintra);
        canainsti = (EditText) findViewById(R.id.canainsti);
        refe = (EditText) findViewById(R.id.refe);
        autoridad = (EditText) findViewById(R.id.autoridad);
        acciones = (EditText) findViewById(R.id.acciones);
        areas = (EditText) findViewById(R.id.areas);
        status = (EditText) findViewById(R.id.status);
        registrarservicio = (Button) findViewById(R.id.registrarservicio);
        registrarservicio.setOnClickListener(this);
        //usuarioSpinner();
        //casoSpinner();
        //beneficiarioSpinner();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrarservicio:
                insertar_es();
                Intent l = new Intent(servicios_sociales.this, area_trabajosocial.class);
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
        String usu2 = service.getText().toString().trim();
        String ben2 = bene2.getText().toString().trim();
        String estados = canaintra.getText().toString().trim();
        String fecha = canainsti.getText().toString().trim();
        String enfermedades = refe.getText().toString().trim();
        String totalgastos = autoridad.getText().toString().trim();
        String serviciomactual = acciones.getText().toString().trim();
        String tipovivienda = areas.getText().toString().trim();
        String servicioactual = status.getText().toString().trim();


        ProgressDialog progressDialog = new ProgressDialog(this);
        if (usu2.isEmpty()){
            service.setError("Complete los campos");
        }else if (ben2.isEmpty()){
            bene2.setError("Complete los campos");
        }else if(estados.isEmpty()){
            canaintra.setError("Complete los campos");
        }else if(fecha.isEmpty()){
            canainsti.setError("Complete los campos");
        }else if(enfermedades.isEmpty()){
            refe.setError("Complete los campos");
        }else if(totalgastos.isEmpty()){
            autoridad.setError("Complete los campos");
        }else if(serviciomactual.isEmpty()){
            acciones.setError("Complete los campos");
        }else if(tipovivienda.isEmpty()){
            areas.setError("Complete los campos");
        }else if(servicioactual.isEmpty()){
            status.setError("Complete los campos");
        }else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/servicios_sociales.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(servicios_sociales.this,"Datos Ingresados Correctamente",Toast.LENGTH_LONG).show();
                        Intent a6 = new Intent(servicios_sociales.this, area_trabajosocial.class);
                        startActivity(a6);
                        finish();
                    } else {
                        Toast.makeText(servicios_sociales.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(servicios_sociales.this, "Datos Ingresados Incorrectamente",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String ,String> params = new HashMap<String,String>();
                    params.put("servicio", usu2);
                    params.put("beneficiario", ben2);
                    params.put("canalizacion_intrainstitucional",estados);
                    params.put("canalizacion_instituciones",fecha);
                    params.put("referido",enfermedades);
                    params.put("autoridad_contacto",totalgastos);
                    params.put("acciones_emprendidas",serviciomactual);
                    params.put("areas_seguimiento",tipovivienda);
                    params.put("estatus",servicioactual);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(servicios_sociales.this);
            requestQueue.add(request);

        }
    }


}