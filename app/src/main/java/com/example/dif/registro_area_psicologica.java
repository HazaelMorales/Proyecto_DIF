package com.example.dif;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

public class registro_area_psicologica extends AppCompatActivity implements View.OnClickListener{
    private EditText benename;
    private EditText benenamedad;
    private EditText benenamemom;
    private EditText curp;
    private EditText benenum;
    private EditText baneestado;
    private EditText benemunicipio;
    private EditText benedomicilio;
    private EditText benesexo;
    private EditText benefechanacimiento;
    private EditText benelugarnacimiento;
    private EditText benefechregistro;
    private EditText beneestadocivil;
    private EditText beneescolaridad;
    private EditText beneocupacion;
    private EditText benenombreescuela;
    int clicker = 0;

    Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrobeneficiario);
        benename = (EditText) findViewById(R.id.benename);
        benenamedad = (EditText) findViewById(R.id.benenamedad);
        benenamemom = (EditText) findViewById(R.id.benenamemom);
        curp = (EditText) findViewById(R.id.curp);
        benenum = (EditText) findViewById(R.id.benenum);
        baneestado = (EditText) findViewById(R.id.baneestado);
        benemunicipio = (EditText) findViewById(R.id.benemunicipio);
        benedomicilio = (EditText) findViewById(R.id.benedomicilio);
        benesexo = (EditText) findViewById(R.id.benesexo);
        benefechanacimiento = (EditText) findViewById(R.id.benefechanacimiento);
        benelugarnacimiento = (EditText) findViewById(R.id.benelugarnacimiento);
        benefechregistro = (EditText) findViewById(R.id.benefechregistro);
        beneestadocivil = (EditText) findViewById(R.id.beneestadocivil);
        beneescolaridad = (EditText) findViewById(R.id.beneescolaridad);
        beneocupacion = (EditText) findViewById((R.id.beneocupacion));
        benenombreescuela = (EditText) findViewById(R.id.benenombreescuela);
        aceptar = (Button) findViewById(R.id.registrar);
        aceptar.setOnClickListener(this);
        benefechregistro.setOnClickListener(this);
        benefechanacimiento.setOnClickListener(this);

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de salir registro de Beneficiario?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(registro_area_psicologica.this,area_psicologia.class);
                        startActivity(i);
                        finish();//cerrar la aplicacion xd
                    }
                }).create().show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registrar:
                insertar_beneficiarios();

                break;
            case R.id.benefechanacimiento:
                showDatePickerDialog();
                clicker =1;
//                Toast.makeText(registrobeneficiario.this,clicker+"",Toast.LENGTH_LONG).show();
                break;
            case R.id.benefechregistro:
                showDatePickerDialog();
                clicker =2;
//                Toast.makeText(registrobeneficiario.this,clicker+"",Toast.LENGTH_LONG).show();
                break;
        }}
    private void showDatePickerDialog() {
        DatePickerFragment newActivity = DatePickerFragment.newInstance((view, year, month, dayOfMonth) -> {
            final String selectedDate = year + "-"  + (month+1) + "-" + dayOfMonth;
            if (clicker==1) {
                benefechanacimiento.setText(selectedDate);
            }else if(clicker==2){
                benefechregistro.setText(selectedDate);
            }
        });
        newActivity.show(getSupportFragmentManager(), "datePicker");
    }
    private void insertar_beneficiarios() {
        String nombrebene = benename.getText().toString().trim();
        String paternobene = benenamedad.getText().toString().trim();
        String maternobene = benenamemom.getText().toString().trim();
        String Curp = curp.getText().toString().trim();
        String numero = benenum.getText().toString().trim();
        String estado = baneestado.getText().toString().trim();
        String municipio = benemunicipio.getText().toString().trim();
        String domicilio = benedomicilio.getText().toString().trim();
        String sexo = benesexo.getText().toString().trim();
        String fechanacimiento = benefechanacimiento.getText().toString().trim();
        String lugarnacimiento = benelugarnacimiento.getText().toString().trim();
        String fecharegistro = benefechregistro.getText().toString().trim();
        String estadocivil = beneestadocivil.getText().toString().trim();
        String escolaridad = beneescolaridad.getText().toString().trim();
        String ocupacion = beneocupacion.getText().toString().trim();
        String nameescuela = benenombreescuela.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);

        if (nombrebene.isEmpty()) {
            benename.setError("Complete los campos.");
        } else if (paternobene.isEmpty()) {
            benenamemom.setError("Complete los campos");
        } else if (maternobene.isEmpty()) {
            benenamemom.setError("Complete los campos");
        } else if (Curp.isEmpty()) {
            curp.setError("Complete los campos");
        } else if (numero.isEmpty()) {
            benenum.setError("Complete los campos");
        } else if (estado.isEmpty()) {
            baneestado.setError("Complete los campos");
        } else if (municipio.isEmpty()) {
            benemunicipio.setError("Complete los campos");
        } else if (domicilio.isEmpty()) {
            benedomicilio.setError("Complete los campos");
        } else if (sexo.isEmpty()) {
            benesexo.setError("Complete los campos");
        } else if (fechanacimiento.isEmpty()) {
            benefechanacimiento.setError("Complete los campos");
        } else if (lugarnacimiento.isEmpty()) {
            benelugarnacimiento.setError("Complete los campos");
        } else if (fecharegistro.isEmpty()) {
            benefechregistro.setError("Complete los campos");
        } else if (estadocivil.isEmpty()) {
            beneestadocivil.setError("Complete los campos");
        } else if (escolaridad.isEmpty()) {
            beneescolaridad.setError("Complete los campos");
        } else if (ocupacion.isEmpty()) {
            beneocupacion.setError("Complete los campos");
        } else if (nameescuela.isEmpty()) {
            benenombreescuela.setError("Complete los campos");
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_beneficiario.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(registro_area_psicologica.this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(registro_area_psicologica.this,info_area_psicologica.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(registro_area_psicologica.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(registro_area_psicologica.this, "Datos subidos incorrectamente", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("nombres", nombrebene);
                    params.put("AP", paternobene);
                    params.put("AM", maternobene);
                    params.put("curp", Curp);
                    params.put("telefono", numero);
                    params.put("estado", estado);
                    params.put("municipio", municipio);
                    params.put("domicilio", domicilio);
                    params.put("sexo", sexo);
                    params.put("fecha_nacimiento", fechanacimiento);
                    params.put("lugar_nacimiento", lugarnacimiento);
                    params.put("fecha_registro", fecharegistro);
                    params.put("estado_civil", estadocivil);
                    params.put("escolaridad", escolaridad);
                    params.put("nombre_escuela", nameescuela);
                    params.put("ocupacion", ocupacion);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(registro_area_psicologica.this);
            requestQueue.add(request);
        }
    }
}