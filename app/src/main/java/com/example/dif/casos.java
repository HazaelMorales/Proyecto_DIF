package com.example.dif;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dif.funciones.DatePickerFragment;

import java.util.HashMap;
import java.util.Map;

public class casos extends AppCompatActivity implements View.OnClickListener{

    private EditText fapertura;
    private EditText descripcion;
    private EditText estado;
    int clicker = 0;
    Button aceptar;
    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(casos.this,interfazjuridica.class);
        startActivity(i2);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casos);

        fapertura = (EditText) findViewById(R.id.fapertura);
        descripcion = (EditText) findViewById(R.id.descripcion);
        estado = (EditText) findViewById(R.id.estado);
        aceptar = (Button) findViewById(R.id.registrarcaso);
        aceptar.setOnClickListener(this);
        fapertura.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrarcaso:
                insertar_caso();
                Intent i = new Intent(casos.this,interfazjuridica.class);
                startActivity(i);
                finish();
                break;
            case R.id.fapertura:
                showDatePickerDialog();
                clicker =1;
//                Toast.makeText(casos.this,clicker+"",Toast.LENGTH_LONG).show();
                break;
        }}
    private void showDatePickerDialog() {
        DatePickerFragment newActivity = DatePickerFragment.newInstance((view, year, month, dayOfMonth) -> {
            final String selectedDate = year + "-"  + (month+1) + "-" + dayOfMonth;
            if (clicker==1) {
                fapertura.setText(selectedDate);
            }
        });
        newActivity.show(getSupportFragmentManager(), "datePicker");
    }
    private void insertar_caso() {

        String fechapertura = fapertura.getText().toString().trim();
        String desc = descripcion.getText().toString().trim();
        String numestado = estado.getText().toString().trim();
        ProgressDialog progressDialog = new ProgressDialog(this);

        if (fechapertura.isEmpty()) {
            fapertura.setError("Complete los campos");
        }else if (desc.isEmpty()) {
            descripcion.setError("Complete los campos");
        }else if (numestado.isEmpty()) {
            estado.setError("Complete los campos");
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_caso.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(casos.this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(casos.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(casos.this, "Datos subidos incorrectamente", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("fecha_apertura", fechapertura);
                    params.put("descripcion_general", desc);
                    params.put("estado", numestado);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(casos.this);
            requestQueue.add(request);
        }
    }
}
