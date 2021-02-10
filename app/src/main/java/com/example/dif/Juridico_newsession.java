package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.loopj.android.http.AsyncHttpClient;

import java.util.HashMap;
import java.util.Map;

public class Juridico_newsession extends AppCompatActivity implements View.OnClickListener {
    private AsyncHttpClient cliente;
    EditText fech,proc,rele,numpropio,numalter,procact,opcsegui,descdet,procpend;
    private String userxd,casoxd,benexd;
    Button sregistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico_newsession);
        userxd=getIntent().getStringExtra("id_suser");
        casoxd=getIntent().getStringExtra("id_scaso");
        benexd=getIntent().getStringExtra("id_sbene");
        fech = (EditText) findViewById(R.id.sfecharef);
        proc= (EditText) findViewById(R.id.sprocedencia);
        rele= (EditText) findViewById(R.id.sobservacionrele);
        numpropio= (EditText) findViewById(R.id.stelefonof);
        numalter= (EditText) findViewById(R.id.stelefonoalterf);
        procact= (EditText) findViewById(R.id.sprocesactf);
        opcsegui= (EditText) findViewById(R.id.sopcionseguif);
        descdet= (EditText) findViewById(R.id.sdescripciondetalladaf);
        procpend= (EditText) findViewById(R.id.sprocesopendf);
        sregistrar = (Button) findViewById(R.id.registrarf);
        sregistrar.setOnClickListener(this);
        fech.setOnClickListener(this);

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de salir registro de Beneficiario?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(Juridico_newsession.this,Juridico_Vcasos.class);
                        i.putExtra("id_suser",userxd);
                        startActivity(i);
                        finish();//cerrar la aplicacion xd
                    }
                }).create().show();
    }
    private void showDatePickerDialog(){
        com.example.dif.funciones.DatePickerFragment newActivity = DatePickerFragment.newInstance((view, year, month, dayOfMonth) -> {
            final String selectedDate = year + "-"  + (month+1) + "-" + dayOfMonth;

                fech.setText(selectedDate);

        });
        newActivity.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registrarf:
                insertar_sesiones();
//                Intent i = new Intent(Juridico_newsession.this,Juridico_Vcasos.class);
//                i.putExtra("id_suser",userxd);
//                startActivity(i);
//                finish();
                break;
            case R.id.sfecharef:
                showDatePickerDialog();
//                Toast.makeText(registrobeneficiario.this,clicker+"",Toast.LENGTH_LONG).show();
                break;

        }
    }
    private void insertar_sesiones(){
        String usuario = userxd;
        String caso = casoxd;
        String beneficiario = benexd;
        String estadoxd = "1";
        String fecha = fech.getText().toString().trim();
        String procedencia = proc.getText().toString().trim();;
        String observacionrele = rele.getText().toString().trim();
        String telefono = numpropio.getText().toString().trim();
        String telefonoalt = numalter.getText().toString().trim();
        String procesoactual = procact.getText().toString().trim();
        String opcionesseguimiento = opcsegui.getText().toString().trim();
        String descripdetalla = descdet.getText().toString().trim();
        String procesopend = procpend.getText().toString().trim();


        ProgressDialog progressDialog = new ProgressDialog(this);

        if (fecha.isEmpty()) {
            fech.setError("Complete los campos.");
        } else if (procedencia.isEmpty()) {
            proc.setError("Complete los campos");
        } else if (observacionrele.isEmpty()) {
            rele.setError("Complete los campos");
        } else if (telefono.isEmpty()) {
            numpropio.setError("Complete los campos");
        } else if (telefonoalt.isEmpty()) {
            numalter.setError("Complete los campos");
        } else if (procesoactual.isEmpty()) {
            procact.setError("Complete los campos");
        } else if (opcionesseguimiento.isEmpty()) {
            opcsegui.setError("Complete los campos");
        } else if (descripdetalla.isEmpty()) {
            descdet.setError("Complete los campos");
        } else if (procesopend.isEmpty()) {
            procpend.setError("Complete los campos");
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_session.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(Juridico_newsession.this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Juridico_newsession.this,Juridico_Vcasos.class);
                        i.putExtra("id_suser",userxd);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(Juridico_newsession.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Juridico_newsession.this, "Datos subidos incorrectamente", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("usuario", usuario);
                    params.put("caso", caso);
                    params.put("beneficiario", beneficiario);
                    params.put("estado", estadoxd);
                    params.put("fecha_registro", fecha);
                    params.put("procedencia", procedencia);
                    params.put("observaciones_relevantes", observacionrele);
                    params.put("telefono", telefono);
                    params.put("contacto_alternativo", telefonoalt);
                    params.put("proceso_actual_iniciado", procesoactual);
                    params.put("opciones_seguimiento", opcionesseguimiento);
                    params.put("descripcion_detallada", descripdetalla);
                    params.put("proceso_pendiente", procesopend);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Juridico_newsession.this);
            requestQueue.add(request);
        }
    }
}