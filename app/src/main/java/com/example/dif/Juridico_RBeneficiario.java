package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Juridico_RBeneficiario extends AppCompatActivity implements View.OnClickListener {
    private AsyncHttpClient cliente;
    int clicker =0;
    private EditText sfechare,sfechanac,sname,sap,sam,scurp,snum,smunicipio,sdomicilio,slugarnaci,scivil,sescuela,sescolaridad,socupacion;
    private RadioGroup sexo;
    Spinner estados;
    String rgbsexo;
    private String userxd;
    Button sregistrar;
    int id_estado;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico__rbeneficiario);
        sfechare = (EditText) findViewById(R.id.sfecharegistro);
        userxd = getIntent().getStringExtra("id_suser");
        cliente = new AsyncHttpClient();
        sfechanac = (EditText) findViewById(R.id.sfechanaci);
        sexo = (RadioGroup) findViewById(R.id.ssexo);
        sregistrar = (Button) findViewById(R.id.sregistrar);
        sfechanac.setOnClickListener(this);
        sfechare.setOnClickListener(this);
        //////////////////////////////////////////////////
        sname = (EditText) findViewById(R.id.sbenename);
        sap = (EditText) findViewById(R.id.sbenenamepa);
        sam = (EditText) findViewById(R.id.sbenenamema);
        scurp = (EditText) findViewById(R.id.scurp);
        snum = (EditText) findViewById(R.id.sbenenum);
        estados = (Spinner) findViewById(R.id.sestado);
        smunicipio = (EditText) findViewById(R.id.sdomicilio);
        sdomicilio = (EditText) findViewById(R.id.sfechanaci);
        slugarnaci = (EditText) findViewById(R.id.slugarnaci);
        scivil = (EditText) findViewById(R.id.sestadocivil);
        sescuela = (EditText) findViewById(R.id.sescuela);
        sescolaridad = (EditText) findViewById(R.id.sescolaridad);
        socupacion = (EditText) findViewById(R.id.socupacion);
        /////////////////////////////////////////////////////
        sname.setOnClickListener(this);
        sap.setOnClickListener(this);
        sam.setOnClickListener(this);
        scurp.setOnClickListener(this);
        snum.setOnClickListener(this);
        smunicipio.setOnClickListener(this);
        sdomicilio.setOnClickListener(this);
        slugarnaci.setOnClickListener(this);
        scivil.setOnClickListener(this);
        sescuela.setOnClickListener(this);
        sescolaridad.setOnClickListener(this);
        socupacion.setOnClickListener(this);
        sregistrar.setOnClickListener(this);
        EstadosSpinner();

        estados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                id_estado = position+1;
                text = estados.getSelectedItem().toString();

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
        sexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbH){
                    rgbsexo = "Hombre";
                }
                else if(checkedId == R.id.rbM){
                    rgbsexo = "Mujer";
                }
            }
        });


    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de salir registro de Beneficiario?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(Juridico_RBeneficiario.this,Juridico.class);
                        i.putExtra("id_suser",userxd);
                        startActivity(i);
                        finish();//cerrar la aplicacion xd
                    }
                }).create().show();
    }
    private void showDatePickerDialog(){
        com.example.dif.funciones.DatePickerFragment newActivity = DatePickerFragment.newInstance((view, year, month, dayOfMonth) -> {
            final String selectedDate = year + "-"  + (month+1) + "-" + dayOfMonth;
            if (clicker==1) {
                sfechanac.setText(selectedDate);
            }else if(clicker==2){
                sfechare.setText(selectedDate);
            }
        });
        newActivity.show(getSupportFragmentManager(), "datePicker");
    }
    private  void EstadosSpinner(){
        String url = "https://checolin00p2.000webhostapp.com/DIF/dif_php/obtenerEstados.php";
        cliente.post(url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    cargarSpinner(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    private void cargarSpinner(String respuesta) {
        ArrayList<estados> lista = new ArrayList<estados>();
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i = 0; i < jsonArreglo.length(); i++) {
                estados r = new estados();
                r.setEstado(jsonArreglo.getJSONObject(i).getString("estado"));
                lista.add(r);
            }
            ArrayAdapter<estados> a = new ArrayAdapter<estados>(this, android.R.layout.simple_dropdown_item_1line, lista);
            estados.setAdapter(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void insertar_beneficiarios(){
        String nombrebene = sname.getText().toString().trim();
        String paternobene = sap.getText().toString().trim();
        String maternobene = sam.getText().toString().trim();
        String Curp = scurp.getText().toString().trim();
        String numero = snum.getText().toString().trim();
        String estado = text;
        String municipio = smunicipio.getText().toString().trim();
        String domicilio = sdomicilio.getText().toString().trim();
        String sexo = rgbsexo;
        String fechanacimiento = sfechanac.getText().toString().trim();
        String lugarnacimiento = slugarnaci.getText().toString().trim();
        String fecharegistro = sfechare.getText().toString().trim();
        String estadocivil = scivil.getText().toString().trim();
        String nameescuela = sescuela.getText().toString().trim();
        String escolaridad = sescolaridad.getText().toString().trim();
        String ocupacion = socupacion.getText().toString().trim();


        ProgressDialog progressDialog = new ProgressDialog(this);

        if (nombrebene.isEmpty()) {
            sname.setError("Complete los campos.");
        } else if (paternobene.isEmpty()) {
            sap.setError("Complete los campos");
        } else if (maternobene.isEmpty()) {
            sam.setError("Complete los campos");
        } else if (Curp.isEmpty()) {
            scurp.setError("Complete los campos");
        } else if (numero.isEmpty()) {
            snum.setError("Complete los campos");
        } else if (municipio.isEmpty()) {
            smunicipio.setError("Complete los campos");
        } else if (domicilio.isEmpty()) {
            sdomicilio.setError("Complete los campos");
        } else if (fechanacimiento.isEmpty()) {
            sfechanac.setError("Complete los campos");
        } else if (lugarnacimiento.isEmpty()) {
            slugarnaci.setError("Complete los campos");
        } else if (fecharegistro.isEmpty()) {
            sfechare.setError("Complete los campos");
        } else if (estadocivil.isEmpty()) {
            scivil.setError("Complete los campos");
        } else if (escolaridad.isEmpty()) {
            sescolaridad.setError("Complete los campos");
        } else if (ocupacion.isEmpty()) {
            socupacion.setError("Complete los campos");
        } else if (nameescuela.isEmpty()) {
            sescuela.setError("Complete los campos");
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_beneficiario.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(Juridico_RBeneficiario.this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Juridico_RBeneficiario.this,Juridico_verBeneficiario.class);
                        i.putExtra("id_suser",userxd);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(Juridico_RBeneficiario.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Juridico_RBeneficiario.this, "Datos subidos incorrectamente", Toast.LENGTH_SHORT).show();
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
            RequestQueue requestQueue = Volley.newRequestQueue(Juridico_RBeneficiario.this);
            requestQueue.add(request);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sregistrar:
                insertar_beneficiarios();
                break;
            case R.id.sfechanaci:
                showDatePickerDialog();
                clicker =1;
//                Toast.makeText(registrobeneficiario.this,clicker+"",Toast.LENGTH_LONG).show();
                break;
            case R.id.sfecharegistro:
                showDatePickerDialog();
                clicker =2;
//                Toast.makeText(registrobeneficiario.this,clicker+"",Toast.LENGTH_LONG).show();
                break;

    }


    }}