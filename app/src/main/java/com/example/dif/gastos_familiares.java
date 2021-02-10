package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class gastos_familiares extends AppCompatActivity implements View.OnClickListener {
    private EditText es, tipogast, monto;
    Button registrargastos;

    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(gastos_familiares.this, area_trabajosocial.class);
        startActivity(i2);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_familiares);
        es = (EditText) findViewById(R.id.es);
        tipogast = (EditText) findViewById(R.id.tipogast);
        monto = (EditText) findViewById(R.id.monto);
        registrargastos = (Button) findViewById(R.id.registrargastos);
        registrargastos.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registrargastos:
                insertar_gasto();
                Intent f = new Intent(gastos_familiares.this, servicios_sociales.class);
                startActivity(f);
                break;
        }
    }

    private void insertar_gasto() {
        String estudio = es.getText().toString().trim();
        String gasto = tipogast.getText().toString().trim();
        String mont = monto.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);

        if (estudio.isEmpty()) {
            es.setError("Complete los campos");
        } else if (gasto.isEmpty()) {
            tipogast.setError("Complete los campos");
        } else if (mont.isEmpty()) {
            monto.setError("Complete los campos");
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/gastos_familiares.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(gastos_familiares.this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show();
                        Intent a6 = new Intent(gastos_familiares.this, area_trabajosocial.class);
                        startActivity(a6);
                    } else {
                        Toast.makeText(gastos_familiares.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(gastos_familiares.this, "Datos Ingresados Incorrectamente", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("estudio_socioeconomico", estudio);
                    params.put("tipo_gasto", gasto);
                    params.put("monto_total", mont);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(gastos_familiares.this);
            requestQueue.add(request);

        }
    }
}


