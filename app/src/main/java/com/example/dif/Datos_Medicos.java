package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Datos_Medicos extends AppCompatActivity implements View.OnClickListener {
    EditText EtMotivo, EtInterrogatorio, EtTensionArterial,
            EtFrecCardiaca, EtFrecRespiratoria, EtTemperatura,
            EtSaturacion, EtTalla, EtPeso, EtIMC, EtFisicas,
            EtDiagnostico,EtAnalisis, EtPlan, EtFecha, EtEstado;
    Button btnRegistrar;
    TextView txt5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos__medicos);

        EtMotivo = (EditText) findViewById(R.id.EditMotivo);
        EtInterrogatorio = (EditText) findViewById(R.id.EditInterrogatorio);
        EtTensionArterial = (EditText) findViewById(R.id.EditTension);
        EtFrecCardiaca = (EditText) findViewById(R.id.EditCardiaca);
        EtFrecRespiratoria = (EditText) findViewById(R.id.EditRespiracion);
        EtTemperatura = (EditText) findViewById(R.id.EditTemperatura);
        EtSaturacion = (EditText) findViewById(R.id.EditSaturacion);
        EtTalla = (EditText) findViewById(R.id.EditTalla);
        EtPeso = (EditText) findViewById(R.id.EditPeso);
        EtIMC = (EditText) findViewById(R.id.EditImc);
        EtFisicas = (EditText) findViewById(R.id.EditObsFisicas);
        EtDiagnostico = (EditText) findViewById(R.id.EditDiagnostico);
        EtAnalisis = (EditText) findViewById(R.id.EditAnalisis);
        EtPlan = (EditText) findViewById(R.id.EditSeguimiento);
        EtFecha = (EditText) findViewById(R.id.EditFechaRegistro);
        EtEstado = (EditText) findViewById(R.id.EditEstado);

        btnRegistrar = (Button) findViewById(R.id.registrar);
        btnRegistrar.setOnClickListener(this);
        EtFecha.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de salir registro de Beneficiario?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(Datos_Medicos.this,area_medica.class);
                        startActivity(i);
                        finish();//cerrar la aplicacion xd
                    }
                }).create().show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registrar:
                Ingresar_Datos_Medicos("https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_Datos_Medicos.php");
                Intent i2 = new Intent(Datos_Medicos.this,ListaCasos.class);
                startActivity(i2);
                break;
            case R.id.EditFechaRegistro:
                showDatePickerDialog();
                break;
        }
    }
    private void showDatePickerDialog() {
        com.example.dif.funciones.DatePickerFragment newActivity = DatePickerFragment.newInstance((view, year, month, dayOfMonth) -> {
            final String selectedDate = year + "-"  + (month+1) + "-" + dayOfMonth;
            EtFecha.setText(selectedDate);
        });
        newActivity.show(getSupportFragmentManager(), "datePicker");
    }

    private void Ingresar_Datos_Medicos(String url){
        SharedPreferences datosUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences datosBeneficiario = getSharedPreferences("beneficiario", Context.MODE_PRIVATE);
        SharedPreferences datosCaso = getSharedPreferences("caso", Context.MODE_PRIVATE);

        String id_usuario = datosUsuario.getString("id_trabajador","");
        String id_beneficiario = datosBeneficiario.getString("id_ben","");
        String id_caso = datosCaso.getString("id_caso","");


        String motivo = EtMotivo.getText().toString().trim();
        String interrogatorio = EtInterrogatorio.getText().toString().trim();
        String tensionArterial = EtTensionArterial.getText().toString().trim();
        String frec_Cardiaca = EtFrecCardiaca.getText().toString().trim();
        String frec_Respiratoria = EtFrecRespiratoria.getText().toString().trim();
        String temperatura = EtTemperatura.getText().toString().trim();
        String saturacion = EtSaturacion.getText().toString().trim();
        String talla = EtTalla.getText().toString().trim();
        String peso = EtPeso.getText().toString().trim();
        String imc = EtIMC.getText().toString().trim();
        String obs_fisicas = EtFisicas.getText().toString().trim();
        String diagnostico = EtDiagnostico.getText().toString().trim();
        String analasis = EtAnalisis.getText().toString().trim();
        String plan_seguimiento = EtPlan.getText().toString().trim();
        String Fecha_registro = EtFecha.getText().toString().trim();
        String estado = EtEstado.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);

        if (motivo.isEmpty()) {
            EtMotivo.setError("Complete los campos.");
        } else if (interrogatorio.isEmpty()) {
            EtInterrogatorio.setError("Complete los campos");
        } else if (tensionArterial.isEmpty()) {
            EtTensionArterial.setError("Complete los campos");
        } else if (frec_Cardiaca.isEmpty()) {
            EtFrecCardiaca.setError("Complete los campos");
        } else if (frec_Respiratoria.isEmpty()) {
            EtFrecRespiratoria.setError("Complete los campos");
        } else if (temperatura.isEmpty()) {
            EtTemperatura.setError("Complete los campos");
        } else if (saturacion.isEmpty()) {
            EtSaturacion.setError("Complete los campos");
        } else if (talla.isEmpty()) {
            EtTalla.setError("Complete los campos");
        } else if (peso.isEmpty()) {
            EtPeso.setError("Complete los campos");
        } else if (imc.isEmpty()) {
            EtIMC.setError("Complete los campos");
        } else if (obs_fisicas.isEmpty()) {
            EtFisicas.setError("Complete los campos");
        } else if (diagnostico.isEmpty()) {
            EtDiagnostico.setError("Complete los campos");
        } else if (analasis.isEmpty()) {
            EtAnalisis.setError("Complete los campos");
        } else if (plan_seguimiento.isEmpty()) {
            EtPlan.setError("Complete los campos");
        } else if (Fecha_registro.isEmpty()) {
            EtFecha.setError("Complete los campos");
        } else if (estado.isEmpty()) {
            EtEstado.setError("Complete los campos");
        }else{
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(Datos_Medicos.this, response, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Datos_Medicos.this, error.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();
                    parametros.put("id_usuario",id_usuario);
                    parametros.put("id_beneficiario",id_beneficiario);
                    parametros.put("id_caso",id_caso);
                    parametros.put("motivo_envio",motivo);
                    parametros.put("interrogatorio",interrogatorio);
                    parametros.put("tension_arterial",tensionArterial);
                    parametros.put("frec_cardiaca",frec_Cardiaca);
                    parametros.put("frec_respiratoria",frec_Respiratoria);
                    parametros.put("temperatura",temperatura);
                    parametros.put("sat_O2",saturacion);
                    parametros.put("talla",talla);
                    parametros.put("peso",peso);
                    parametros.put("imc",imc);
                    parametros.put("obs_fisicas",obs_fisicas);
                    parametros.put("diagnostico",diagnostico);
                    parametros.put("analisis",analasis);
                    parametros.put("plan_seguimiento",plan_seguimiento);
                    parametros.put("fecha_registro",Fecha_registro);
                    parametros.put("estado",estado);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

}