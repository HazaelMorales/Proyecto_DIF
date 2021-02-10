package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

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

public class MostrarDatosPsicologicos extends AppCompatActivity implements View.OnClickListener {
    TextView txtNombre, txtCaso, txtFecha, txtHora, txtMotivo, txtNotas, txtPsicologo, txtConsentimiento;
    Button btnRegresar;

    public String StrId_Psicologia, StrNombre, StrAP, StrAM, StrCaso, StrFecha, StrHora, StrMotivo, StrTipo, StrNotas, StrPsicologo, StrConsetimiento,StrEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos_psicologicos);

        txtNombre = (TextView)findViewById(R.id.beneFullName);
        txtCaso = (TextView)findViewById(R.id.Medico_Caso);
        txtFecha = (TextView)findViewById(R.id.Psico_Fecha);
        txtHora = (TextView)findViewById(R.id.PsicoHora);
        txtMotivo  = (TextView)findViewById(R.id.Psico_motivo);
        txtNotas = (TextView)findViewById(R.id.Psicos_Notas);
        txtPsicologo = (TextView)findViewById(R.id.PsicoPsicologa);
        txtConsentimiento = (TextView)findViewById(R.id.PsicoConsentimiento);

        btnRegresar = (Button) findViewById(R.id.aceptar);
        btnRegresar.setOnClickListener(this);

        cargarDatos("https://checolin00p2.000webhostapp.com/DIF/dif_php/MostrarDatosPsicologia.php");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aceptar:
                Intent i = new Intent(MostrarDatosPsicologicos.this,info_area_psicologica.class);
                startActivity(i);
        }
    }

    private void cargarDatos(String url){
        SharedPreferences datosUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences datosBeneficiario = getSharedPreferences("beneficiario", Context.MODE_PRIVATE);
        SharedPreferences datosCaso = getSharedPreferences("caso", Context.MODE_PRIVATE);
        SharedPreferences datosPsicologia = getSharedPreferences("psicologia", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = datosPsicologia.edit();

        String id_usuario = datosUsuario.getString("id_trabajador","");
        String id_beneficiario = datosBeneficiario.getString("id_ben","");
        String id_caso = datosCaso.getString("id_caso","");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    StrId_Psicologia = jsonObject.getString("id_consulta");
                    StrNombre = jsonObject.getString("nombres");
                    StrAP = jsonObject.getString("AP");
                    StrAM = jsonObject.getString("AM");
                    StrFecha = jsonObject.getString("fecha");
                    StrHora = jsonObject.getString("hora");
                    StrMotivo = jsonObject.getString("motivo_atencion");
                    StrTipo = jsonObject.getString("tipo_registro");
                    StrNotas = jsonObject.getString("notas_sesion");
                    StrPsicologo = jsonObject.getString("psicol_o_psiq");
                    StrConsetimiento = jsonObject.getString("consentimiento");
                    StrEstado = jsonObject.getString("estado");


                    txtNombre.setText(StrNombre+""+StrAP+""+StrAM);
                    txtCaso.setText(id_caso);
                    txtFecha.setText(StrFecha);
                    txtHora.setText(StrHora);
                    txtMotivo.setText(StrMotivo);
                    txtNotas.setText(StrNotas);
                    txtPsicologo.setText(StrPsicologo);
                    txtConsentimiento.setText(StrConsetimiento);

                    editor.putString("id_consulta", StrId_Psicologia);
                    editor.putString("id_usuario", id_usuario);
                    editor.putString("id_caso", id_caso);
                    editor.putString("id_beneficiario", id_beneficiario);
                    editor.putString("fecha", StrFecha);
                    editor.putString("hora", StrHora);
                    editor.putString("motivo", StrMotivo);
                    editor.putString("tipo", StrTipo);
                    editor.putString("notas", StrNotas);
                    editor.putString("Psicologo", StrPsicologo);
                    editor.putString("consentimiento", StrConsetimiento);
                    editor.putString("Estado", StrEstado);
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MostrarDatosPsicologicos.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
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
        requestQueue.add(stringRequest);
    }
}