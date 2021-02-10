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
import com.example.dif.funciones.DatePickerFragment;

import java.util.HashMap;
import java.util.Map;

public class addcase_area_psicologica extends AppCompatActivity implements View.OnClickListener {
    EditText fapertura, descripcion,estado;
    int clicker = 0;
    Button aceptar;
    String id_worker;

    @Override
    public void onBackPressed() {
        Intent i2 = new Intent(addcase_area_psicologica.this,area_psicologia.class);
        startActivity(i2);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcase_area_psicologica);

        fapertura = (EditText) findViewById(R.id.fapertura);
        descripcion = (EditText) findViewById(R.id.descripcion);
        estado = (EditText) findViewById(R.id.estado);
        aceptar = (Button) findViewById(R.id.registrarcaso);
        aceptar.setOnClickListener(this);
        fapertura.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registrarcaso:
                insertar_caso();
                Intent i = new Intent(addcase_area_psicologica.this,area_psicologia.class);
                startActivity(i);
                finish();
                break;
            case R.id.fapertura:
                showDatePickerDialog();
                clicker =1;
                break;
        }

    }

    private void showDatePickerDialog() {
        com.example.dif.funciones.DatePickerFragment newActivity = DatePickerFragment.newInstance((view, year, month, dayOfMonth) -> {
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
            /*https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_caso.php*/
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_caso.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(addcase_area_psicologica.this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(addcase_area_psicologica.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(addcase_area_psicologica.this, "Datos subidos incorrectamente", Toast.LENGTH_SHORT).show();
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
            RequestQueue requestQueue = Volley.newRequestQueue(addcase_area_psicologica.this);
            requestQueue.add(request);
        }
    }
}