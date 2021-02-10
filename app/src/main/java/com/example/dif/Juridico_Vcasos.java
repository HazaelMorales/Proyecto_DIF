package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Juridico_Vcasos extends AppCompatActivity implements View.OnClickListener{
    private AsyncHttpClient cliente;
    private ListView jvercasos;
    private String userxd;
    @Override
    public void onBackPressed() {
        Intent a1 = new Intent(Juridico_Vcasos.this,Juridico.class);
        a1.putExtra("id_suser",userxd);
        startActivity(a1);
        finish();//matar  activity actual xd
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico__vcasos);
        cliente = new AsyncHttpClient();
        userxd = getIntent().getStringExtra("id_suser");
        jvercasos = (ListView) findViewById(R.id.slistcasos);
        vercasosjuridico();
        jvercasos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String hola = parent.getItemAtPosition(position).toString();
                enviarDatos(hola);


            }
        });
        }
        private void vercasosjuridico(){
        String url = "https://checolin00p2.000webhostapp.com/DIF/dif_php/obtenerSesionesJuridico.php";
        cliente.post(url,new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){}
                mostrar(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        }
        private void mostrar(String respuesta){

            ArrayList <JSesiones> lista = new ArrayList<JSesiones>();
            try {
                JSONArray jsonArreglo = new JSONArray(respuesta);
                for(int i=0; i<jsonArreglo.length();i++){
                    JSesiones r = new JSesiones();
                    r.setRol(jsonArreglo.getJSONObject(i).getString("id_atencion") +" "+ jsonArreglo.getJSONObject(i).getString("nombres"));
                    lista.add(r);
                }
                ArrayAdapter<JSesiones> a = new ArrayAdapter<JSesiones>(this, android.R.layout.simple_list_item_activated_1,lista);
                jvercasos.setAdapter(a);
            }catch (Exception e){
                e.printStackTrace();
        }
    }
    private void enviarDatos(String a){
        String posicion = a;

        StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/mostrarDatosSessiones.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String ocaso = jsonObject.getString("caso");
                    String oname = jsonObject.getString("nombres")+ " "+ jsonObject.getString("AP")+" "+ jsonObject.getString("AM");
                    String oestado = jsonObject.getString("estate");
                    String ofecha = jsonObject.getString("fecha_registroj");
                    String oprocedencia = jsonObject.getString("procedencia");
                    String orelevantes = jsonObject.getString("observaciones_relevantes");
                    String otele = jsonObject.getString("telefonoj");
                    String otelealt = jsonObject.getString("contacto_alternativo");
                    String oprocact = jsonObject.getString("proceso_actual_iniciado");
                    String oseguimiento = jsonObject.getString("opciones_seguimiento");
                    String odetallada = jsonObject.getString("descripcion_detallada");
                    String opend = jsonObject.getString("proceso_pendiente");
                    Toast.makeText(Juridico_Vcasos.this,"onexd",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Juridico_Vcasos.this, Juridico_versession.class);
                    i.putExtra("id_suser",userxd);
                    i.putExtra("a1",ocaso);
                    i.putExtra("a2",oname);
                    i.putExtra("a3",oestado);
                    i.putExtra("a4",ofecha);
                    i.putExtra("a5",oprocedencia);
                    i.putExtra("a6",orelevantes);
                    i.putExtra("a7",otele);
                    i.putExtra("a8",otelealt);
                    i.putExtra("a9",oprocact);
                    i.putExtra("a10",oseguimiento);
                    i.putExtra("a11",odetallada);
                    i.putExtra("a12",opend);
                    startActivity(i);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Juridico_Vcasos.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parametros = new HashMap<String, String>();
                parametros.put("id_session",posicion);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private static String getNumeros(String cadena){
        char [] cadena_div = cadena.toCharArray();
        String n = "";
        for(int i = 0; i < cadena_div.length; i++ ){
            if(Character.isDigit(cadena_div[i])){
                n+=cadena_div[i];
            }
        }
        return n;
    }

    @Override
    public void onClick(View v) {

    }
}

