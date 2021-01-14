package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dif.funciones.Beneficiario;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class mostrarbene_secre extends AppCompatActivity implements View.OnClickListener {
    private AsyncHttpClient cliente;
    ListView libenefi;
    Button mostrarbene;
    public int id_beneficiario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarbene_secre);
        cliente = new AsyncHttpClient();
        libenefi = (ListView) findViewById(R.id.libenefi);
        mostrarbene = (Button) findViewById(R.id.submit);
        mostrarbene.setOnClickListener(this);
        listabeneficiarios();
        libenefi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                id_beneficiario = position+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
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
                r.setRol(jsonArreglo.getJSONObject(i).getString("nombres"));
                lista.add(r);
            }
            ArrayAdapter<Beneficiario> a = new ArrayAdapter<Beneficiario>(this, android.R.layout.simple_list_item_activated_1,lista);
            libenefi.setAdapter(a);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                Intent i = new Intent(mostrarbene_secre.this,areajuridica_secre.class);
                startActivity(i);
                break;
        }
    }
}