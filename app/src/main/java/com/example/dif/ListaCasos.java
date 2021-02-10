package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class ListaCasos extends AppCompatActivity {
    private AsyncHttpClient cliente;
    ListView licasos;
    Button regresar;
    TextView txt2;
    @Override
    public void onBackPressed() {
        Intent a1 = new Intent(ListaCasos.this,area_medica.class);
        startActivity(a1);
        finish();//matar  activity actual xd
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_casos);
        cliente = new AsyncHttpClient();
        licasos = (ListView) findViewById(R.id.licasos);
        regresar = (Button) findViewById(R.id.submit);
        listaCasos();
        licasos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String hola = parent.getItemAtPosition(position).toString();
                enviarDatos(hola);
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaCasos.this,area_medica.class);
                startActivity(i);
                finish();
            }
        });


    }
    private void listaCasos(){
        /*String url = "https://checolin00p2.000webhostapp.com/DIF/dif_php/obtenerBeneficiarios.php";*/
        String url = "https://checolin00p2.000webhostapp.com/DIF/dif_php/obtenerCasos.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    mostrar(new String(responseBody));
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }
    private void mostrar(String respuesta){
        ArrayList<Beneficiario> lista = new ArrayList<Beneficiario>();
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for(int i=0; i<jsonArreglo.length();i++){
                Beneficiario r = new Beneficiario();
                r.setRol(jsonArreglo.getJSONObject(i).getString("id_caso") + " " +  jsonArreglo.getJSONObject(i).getString("descripcion_general"));
                lista.add(r);
            }
            ArrayAdapter<Beneficiario> a = new ArrayAdapter<Beneficiario>(this, android.R.layout.simple_list_item_activated_1,lista);
            licasos.setAdapter(a);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void enviarDatos(String a){
        String posicion = a;
        SharedPreferences parametrosCaso = getSharedPreferences("caso", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = parametrosCaso.edit();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/mostrarCasos.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String id_caso = jsonObject.getString("id_caso");
                    String fecha = jsonObject.getString("fecha_apertura");
                    String descripcion_general = jsonObject.getString("descripcion_general");
                    String estado = jsonObject.getString("estado");

                    editor.putString("id_caso",id_caso);
                    editor.putString("fecha_apertura",fecha);
                    editor.putString("descripcion",descripcion_general);
                    editor.putString("estado",estado);
                    editor.commit();
                    Intent i2 = new Intent(ListaCasos.this, vercasos.class);
                    finish();
                    startActivity(i2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListaCasos.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("id_case",posicion);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ListaCasos.this);
        requestQueue.add(stringRequest);
    }

}