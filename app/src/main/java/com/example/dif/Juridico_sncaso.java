package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.example.dif.funciones.Beneficiario;
import com.example.dif.funciones.DatePickerFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.microedition.khronos.egl.EGLDisplay;

import cz.msebera.android.httpclient.Header;

public class Juridico_sncaso extends AppCompatActivity implements View.OnClickListener {

    private String benexd,userxd;
    EditText sfecha,sdescrip;
    Button sumitxd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico_sncaso);
        benexd = getIntent().getStringExtra("id_sbene");//variable donde se cargo el valorde la varibale pasada
        userxd = getIntent().getStringExtra("id_sbene");
        sfecha = (EditText) findViewById(R.id.sfapertura);
        sdescrip = (EditText) findViewById(R.id.sdescripcion);
        sumitxd = (Button) findViewById(R.id.sregistrarcaso);
        sumitxd.setOnClickListener(this);
        sfecha.setOnClickListener(this);

    }
    @Override
    public void onBackPressed() {
    Intent i = new Intent(Juridico_sncaso.this,Juridico_opcaso.class);
    i.putExtra("id_sbene",benexd);
    i.putExtra("id_suser",userxd);
    startActivity(i);
    finish();//cerrar la aplicacion xd
    }
    private void showDatePickerDialog(){
        com.example.dif.funciones.DatePickerFragment newActivity = DatePickerFragment.newInstance((view, year, month, dayOfMonth) -> {
            final String selectedDate = year + "-"  + (month+1) + "-" + dayOfMonth;
            sfecha.setText(selectedDate);

        });
        newActivity.show(getSupportFragmentManager(), "datePicker");
    }

    private void insertar_caso() {

        String fechapertura = sfecha.getText().toString().trim();
        String desc = sdescrip.getText().toString().trim();
        String numestado = "1";
        ProgressDialog progressDialog = new ProgressDialog(this);

        if (fechapertura.isEmpty()) {
            sfecha.setError("Complete los campos");
        }else if (desc.isEmpty()) {
            sdescrip.setError("Complete los campos");
        }else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_caso.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(Juridico_sncaso.this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Juridico_sncaso.this,Juridico_vercasos.class);
                        i.putExtra("id_sbene",benexd);
                        i.putExtra("id_suser",userxd);
                        startActivity(i);
                        finish();//cerrar la aplicacion xd
                    } else {
                        Toast.makeText(Juridico_sncaso.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Juridico_sncaso.this, "Datos subidos incorrectamente", Toast.LENGTH_SHORT).show();
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
            RequestQueue requestQueue = Volley.newRequestQueue(Juridico_sncaso.this);
            requestQueue.add(request);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sfapertura:
                showDatePickerDialog();
                //agregar el intenet para agregar sesion
                break;
            case R.id.sregistrarcaso:
                insertar_caso();
                break;

        }
    }
}