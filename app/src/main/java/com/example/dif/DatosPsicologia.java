package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dif.funciones.DatePickerFragment;

import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DatosPsicologia extends AppCompatActivity implements View.OnClickListener {
    EditText EtFecha, EtHora, EtMotivo,
            EtTipo, EtNotas, EtPsiquiatra,
            EtConsentimiento, EtEstado;
    Button btnRegistrar;
    TextView textPrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_psicologia);

        EtFecha = (EditText) findViewById(R.id.PsiFechaRegistro);
        EtHora = (EditText) findViewById(R.id.PsiHoraConsulta);
        EtMotivo = (EditText) findViewById(R.id.PsiMotivo);
        EtTipo = (EditText) findViewById(R.id.PsiTipo);
        EtNotas = (EditText) findViewById(R.id.PsiNotas);
        EtPsiquiatra = (EditText) findViewById(R.id.PsiPsiquiatra);
        EtConsentimiento = (EditText) findViewById(R.id.PsiConsentimiento);
        EtEstado = (EditText) findViewById(R.id.PsiEstado);
        textPrueba = (TextView) findViewById(R.id.text2);

        btnRegistrar = (Button) findViewById(R.id.registrar);
        btnRegistrar.setOnClickListener(this);
        EtFecha.setOnClickListener(this);
        EtHora.setOnClickListener(this);

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de salir registro de Beneficiario?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(DatosPsicologia.this,area_psicologia.class);
                        startActivity(i);
                        finish();//cerrar la aplicacion xd
                    }
                }).create().show();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registrar:
                Ingresar_Datos_Medicos("https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_Datos_Psicologia.php");
                /*Intent i2 = new Intent(DatosPsicologia.this,ListaCasosPsicologia.class);
                startActivity(i2);*/
                break;
            case R.id.PsiFechaRegistro:
                showDatePickerDialog();
                break;
            case R.id.PsiHoraConsulta:
                ObtenerHora();
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

    private void ObtenerHora(){
        final Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minutos = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                EtHora.setText(hourOfDay+":"+minute);
            }
        },hora,minutos,false);
        timePickerDialog.show();
    }

    private void Ingresar_Datos_Medicos(String url){
        SharedPreferences datosUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences datosBeneficiario = getSharedPreferences("beneficiario", Context.MODE_PRIVATE);
        SharedPreferences datosCaso = getSharedPreferences("caso", Context.MODE_PRIVATE);

        String id_usuario = datosUsuario.getString("id_trabajador","");
        String id_beneficiario = datosBeneficiario.getString("id_beneficiario","");
        String id_caso = datosCaso.getString("id_caso","");


        String fecha = EtFecha.getText().toString().trim();
        String hora = EtHora.getText().toString().trim();
        String motivo = EtMotivo.getText().toString().trim();
        String tipoRegistro = EtTipo.getText().toString().trim();
        String notas = EtNotas.getText().toString().trim();
        String Nombre_Psicologo = EtPsiquiatra.getText().toString().trim();
        String consentimiento = EtConsentimiento.getText().toString().trim();
        String estado = EtEstado.getText().toString().trim();

        textPrueba.setText(id_beneficiario);

        ProgressDialog progressDialog = new ProgressDialog(this);

        if (fecha.isEmpty()) {
            EtFecha.setError("Complete los campos.");
        } else if (hora.isEmpty()) {
            EtHora.setError("Complete los campos");
        } else if (motivo.isEmpty()) {
            EtMotivo.setError("Complete los campos");
        } else if (tipoRegistro.isEmpty()) {
            EtTipo.setError("Complete los campos");
        } else if (notas.isEmpty()) {
            EtNotas.setError("Complete los campos");
        } else if (Nombre_Psicologo.isEmpty()) {
            EtPsiquiatra.setError("Complete los campos");
        } else if (consentimiento.isEmpty()) {
            EtConsentimiento.setError("Complete los campos");
        } else if (estado.isEmpty()) {
            EtEstado.setError("Complete los campos");
        } else{
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(DatosPsicologia.this, response, Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(DatosPsicologia.this, error.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();
                    parametros.put("id_usuario",id_usuario);
                    parametros.put("id_beneficiario",id_beneficiario);
                    parametros.put("id_caso",id_caso);
                    parametros.put("fecha", fecha);
                    parametros.put("hora",hora);
                    parametros.put("motivo_atencion",motivo);
                    parametros.put("tipo_registro",tipoRegistro);
                    parametros.put("notas_sesion",notas);
                    parametros.put("psicologia_psiquiatra",Nombre_Psicologo);
                    parametros.put("consentimiento",consentimiento);
                    parametros.put("estado",estado);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}