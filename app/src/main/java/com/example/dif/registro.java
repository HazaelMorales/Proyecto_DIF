package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dif.funciones.Roles;
import com.loopj.android.http.*;

import org.json.JSONArray;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class registro extends AppCompatActivity{
        private AsyncHttpClient cliente;
        EditText editnombre,editapPaterno,editapMaterno,editcorreo,editcontrasena;
        Button btnregistrar;
        Spinner roles;

        public int id_role;
        String new_password;
        private Handler rdatos = new Handler();
    @Override
    public void onBackPressed() {
        Intent b = new Intent(registro.this,admin.class);
        startActivity(b);
        finish();//cerrar la aplicacion x
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editnombre = (EditText) findViewById(R.id.workname);
        editapPaterno = (EditText) findViewById(R.id.workApPaterno);
        editapMaterno = (EditText) findViewById(R.id.workApMaterno);
        editcorreo = (EditText) findViewById(R.id.correo);
        editcontrasena = (EditText) findViewById(R.id.contra);
        cliente = new AsyncHttpClient();
        roles = (Spinner) findViewById(R.id.roles);
        btnregistrar = (Button) findViewById(R.id.login);

        rolesSpinner();
        btnregistrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               insertar();
               if (id_role==(4)){
                   Intent a = new Intent(registro.this,admin.class);
                   startActivity(a);
                   finish();
               }else if (id_role == (5)){
                   Intent b = new Intent(registro.this,admin.class);
                   startActivity(b);
                   finish();
               }
           }
         });
         roles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                id_role = position+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
         });
    }

    private void insertar(){

        String nombre = editnombre.getText().toString().trim();
        String AP = editapPaterno.getText().toString().trim();
        String AM = editapMaterno.getText().toString().trim();
        String correo = editcorreo.getText().toString().trim();
        String password = editcontrasena.getText().toString().trim();
        String id_Roles = Integer.toString(id_role);

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes("utf8"));
            new_password = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e){
            e.printStackTrace();
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        if(nombre.isEmpty()){
            editnombre.setError("Complete los campos");
        }else if(AP.isEmpty()){
            editapPaterno.setError("Complete los campos");
        }else if(AM.isEmpty()){
            editapMaterno.setError("Complete los campos");
        }else if(correo.isEmpty()){
            editcorreo.setError("Complete los campos");
        }else if(password.isEmpty()){
            editcontrasena.setError("Complete los campos");
        }
        else{
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/ingresar_usuarios.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equalsIgnoreCase("Datos Insertados")) {
                        Toast.makeText(registro.this,"Datos Ingresados Correctamente",Toast.LENGTH_LONG).show();
                        Intent a6 = new Intent(registro.this, admin.class);
                        startActivity(a6);
                        finish();
                    } else {
                        Toast.makeText(registro.this, response, Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(registro.this, "Datos Ingresados Incorrectamente",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String ,String> params = new HashMap<String,String>();
                    params.put("nombres",nombre);
                    params.put("AP",AP);
                    params.put("AM",AM);
                    params.put("correo",correo);
                    params.put("password",new_password);
                    params.put("rol",id_Roles);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(registro.this);
            requestQueue.add(request);

        }
    }
    private void rolesSpinner(){
        String url = "https://checolin00p2.000webhostapp.com/DIF/dif_php/obtenerDatos.php";
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
    private void cargarSpinner(String respuesta){
        ArrayList <Roles> lista = new ArrayList<Roles>();
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for(int i=0; i<jsonArreglo.length();i++){
                Roles r = new Roles();
                r.setRol(jsonArreglo.getJSONObject(i).getString("rol"));
                lista.add(r);
            }
            ArrayAdapter <Roles> a = new ArrayAdapter<Roles>(this, android.R.layout.simple_dropdown_item_1line,lista);
            roles.setAdapter(a);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}