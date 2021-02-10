package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MostrarDatosMedicos extends AppCompatActivity implements View.OnClickListener {
    TextView txtNombre, txtCaso, txtMotivo, txtInterrogatorio, txtTension,
            txtCardiaca, txtRespiratoria, txtTemperatura, txtSaturacion,
            txtTalla, txtPeso, txtIMC, txtObs, txtDiagnostico, txtAnalisis,
            txtPlan, txtFechaRegistro;

    Button btnRegresar;

    public String StrId_Medico,StrNombre,StrAP,StrAM,StrMotivo,StrInterrogatorio,StrTension,StrCardiaca,StrRespiratoria,
            StrTemperatura, StrSaturacion, StrTalla, StrPeso, StrIMC, StrObs, StrDiagnostico, StrAnalisis,
            StrPlan, StrFechaRegistro,StrEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos_medicos);
        txtNombre = (TextView)findViewById(R.id.beneFullName);
        txtCaso = (TextView)findViewById(R.id.Medico_Caso);
        txtMotivo = (TextView)findViewById(R.id.Medico_Motivo);
        txtInterrogatorio = (TextView)findViewById(R.id.Medico_Interrogatorio);
        txtTension  = (TextView)findViewById(R.id.Medico_Tension);
        txtCardiaca = (TextView)findViewById(R.id.Medico_Cardiaca);
        txtRespiratoria = (TextView)findViewById(R.id.Medico_Respiratoria);
        txtTemperatura = (TextView)findViewById(R.id.Medico_Temperatura);
        txtSaturacion = (TextView)findViewById(R.id.Medico_Saturacion);
        txtTalla  = (TextView)findViewById(R.id.Medico_Talla);
        txtPeso = (TextView)findViewById(R.id.Medico_Peso);
        txtIMC = (TextView)findViewById(R.id.Medico_IMC);
        txtObs = (TextView)findViewById(R.id.Medico_Obs);
        txtDiagnostico = (TextView)findViewById(R.id.Medico_Diagnostico);
        txtAnalisis = (TextView)findViewById(R.id.Medico_Analisis);
        txtPlan = (TextView)findViewById(R.id.Medico_Plan);
        txtFechaRegistro = (TextView)findViewById(R.id.Medico_Fecha);

        btnRegresar = (Button) findViewById(R.id.aceptar);

        btnRegresar.setOnClickListener(this);

        cargarDatos("https://checolin00p2.000webhostapp.com/DIF/dif_php/MostrarDatosMedicos.php");



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aceptar:
                Intent i = new Intent(MostrarDatosMedicos.this,info_area_medica.class);
                startActivity(i);
        }
    }
    public void cargarDatos(String url){
        SharedPreferences datosUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences datosBeneficiario = getSharedPreferences("beneficiario", Context.MODE_PRIVATE);
        SharedPreferences datosCaso = getSharedPreferences("caso", Context.MODE_PRIVATE);
        SharedPreferences datosMedico = getSharedPreferences("medico", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datosMedico.edit();

        String id_usuario = datosUsuario.getString("id_trabajador","");
        String id_beneficiario = datosBeneficiario.getString("id_ben","");
        String id_caso = datosCaso.getString("id_caso","");


        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    StrId_Medico = jsonObject.getString("id_consulta");
                    StrNombre = jsonObject.getString("nombres");
                    StrAP = jsonObject.getString("AP");
                    StrAM = jsonObject.getString("AM");
                    StrMotivo = jsonObject.getString("motivo_envio");
                    StrInterrogatorio = jsonObject.getString("interrogatorio");
                    StrTension = jsonObject.getString("tension_arterial");
                    StrCardiaca = jsonObject.getString("frec_cardiaca");
                    StrRespiratoria = jsonObject.getString("frec_respiratoria");
                    StrTemperatura = jsonObject.getString("temperatura");
                    StrSaturacion = jsonObject.getString("sat_02");
                    StrTalla = jsonObject.getString("talla");
                    StrPeso = jsonObject.getString("peso");
                    StrIMC = jsonObject.getString("imc");
                    StrObs = jsonObject.getString("obs_fisicas");
                    StrDiagnostico = jsonObject.getString("diagnostico");
                    StrAnalisis = jsonObject.getString("analisis");
                    StrPlan = jsonObject.getString("plan_seguimiento");
                    StrFechaRegistro = jsonObject.getString("fecha_registro");
                    StrEstado = jsonObject.getString("estado");

                    txtNombre.setText(StrNombre+StrAP+StrAM);
                    txtCaso.setText(id_caso);
                    txtMotivo.setText(StrMotivo);
                    txtInterrogatorio.setText(StrInterrogatorio);
                    txtTension.setText(StrTension);
                    txtCardiaca.setText(StrCardiaca);
                    txtRespiratoria.setText(StrRespiratoria);
                    txtTemperatura.setText(StrTemperatura);
                    txtSaturacion.setText(StrSaturacion);
                    txtTalla.setText(StrTalla);
                    txtPeso.setText(StrPeso);
                    txtIMC.setText(StrIMC);
                    txtObs.setText(StrObs);
                    txtDiagnostico.setText(StrDiagnostico);
                    txtAnalisis.setText(StrAnalisis);
                    txtPlan.setText(StrPlan);
                    txtFechaRegistro.setText(StrFechaRegistro);


                    editor.putString("id_consulta", StrId_Medico);
                    editor.putString("id_usuario", id_usuario);
                    editor.putString("id_caso", id_caso);
                    editor.putString("id_beneficiario", id_beneficiario);
                    editor.putString("motivo", StrMotivo);
                    editor.putString("interrogatorio", StrInterrogatorio);
                    editor.putString("tension_arterial", StrTension);
                    editor.putString("frec_Cardiaca", StrCardiaca);
                    editor.putString("frec_Respiratoria", StrRespiratoria);
                    editor.putString("Temperatura", StrTemperatura);
                    editor.putString("Talla", StrTalla);
                    editor.putString("Peso", StrPeso);
                    editor.putString("IMC", StrIMC);
                    editor.putString("OBS", StrObs);
                    editor.putString("Plan", StrPlan);
                    editor.putString("Estado", StrEstado);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MostrarDatosMedicos.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parametros = new HashMap<String, String>();
                parametros.put("id_caso",id_caso);
                parametros.put("id_usuario",id_usuario);
                parametros.put("id_beneficiario",id_beneficiario);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}