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
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Juridico_verBeneficiario extends AppCompatActivity {
    private AsyncHttpClient cliente;
    private String userxd,benexd;
    ListView slist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico_verbeneficiario);
        slist = (ListView) findViewById(R.id.slibenefi);
        userxd = getIntent().getStringExtra("id_suser");
        cliente = new AsyncHttpClient();
        listabeneficiarios();
        slist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sg = parent.getItemAtPosition(position).toString();
                benexd = getNumeros(sg);
                Toast.makeText(Juridico_verBeneficiario.this,benexd,Toast.LENGTH_LONG).show();
                Intent a1 = new Intent(Juridico_verBeneficiario.this,Juridico_opcaso.class);
                a1.putExtra("id_sbene",benexd);
                a1.putExtra("id_suser",userxd);
                startActivity(a1);
                finish();

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent a1 = new Intent(Juridico_verBeneficiario.this,Juridico.class);
        a1.putExtra("id_suser",userxd);
        startActivity(a1);
        finish();//matar  activity actual xd
    }
    private void listabeneficiarios(){
        String url = "https://checolin00p2.000webhostapp.com/DIF/dif_php/obtenerBeneficiarios.php";
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
                r.setRol(jsonArreglo.getJSONObject(i).getString("id_beneficiario")+" "+jsonArreglo.getJSONObject(i).getString("nombres") + " " + jsonArreglo.getJSONObject(i).getString("AP")+ " " + jsonArreglo.getJSONObject(i).getString("AM"));
                lista.add(r);
            }
            ArrayAdapter<Beneficiario> a = new ArrayAdapter<Beneficiario>(this, android.R.layout.simple_list_item_activated_1,lista);
            slist.setAdapter(a);
        }catch (Exception e){
            e.printStackTrace();
        }
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

}