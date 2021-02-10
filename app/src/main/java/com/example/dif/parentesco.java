package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class parentesco extends AppCompatActivity implements View.OnClickListener {
    private EditText es, tipogast, monto;
    Button registrarparentesco;

    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(parentesco.this, seguimiento_trabajosocial.class);
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
        registrarparentesco = (Button) findViewById(R.id.registrarparentesco);
        registrarparentesco.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registrarparentesco:
                insertar_gasto();
                Intent f = new Intent(parentesco.this, area_trabajosocial.class);
                startActivity(f);
                break;
        }
    }

    private void insertar_gasto() {
        //SharedPreferences ponysBeneficiario = getSharedPreferences("Rarity", Context.MODE_PRIVATE);
        //String estudio = ponysBeneficiario.getString("id_beneficiario","");
        //es.setText(estudio);
        String estudio = es.getText().toString().trim();
        String gasto = tipogast.getText().toString().trim();
        String mont = monto.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);

        if (gasto.isEmpty()) {
            tipogast.setError("Complete los campos");
        } else if (mont.isEmpty()) {
            monto.setError("Complete los campos");
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/parentesco.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(parentesco.this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show();
                        Intent a6 = new Intent(parentesco.this, area_trabajosocial.class);
                        startActivity(a6);
                    } else {
                        Toast.makeText(parentesco.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(parentesco.this, "Datos Ingresados Incorrectamente", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("beneficiario", estudio);
                    params.put("beneficiario_dependiente", gasto);
                    params.put("parentesco", mont);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(parentesco.this);
            requestQueue.add(request);

        }
    }
}
