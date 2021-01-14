package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dif.funciones.Trabajador;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class trabajadores extends AppCompatActivity implements View.OnClickListener{
    private AsyncHttpClient cliente;
    ListView libenefi;
    Button menu;
    public int id_beneficiario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajadores);
        cliente = new AsyncHttpClient();
        libenefi = (ListView) findViewById(R.id.libenefi);
        menu = (Button) findViewById(R.id.submit);
        menu.setOnClickListener(this);
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
        String url = "https://checolin00p2.000webhostapp.com/DIF/dif_php/obtenerTrabajadores.php";
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
        ArrayList<Trabajador> lista = new ArrayList<Trabajador>();
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for(int i=0; i<jsonArreglo.length();i++){
                Trabajador r = new Trabajador();
                r.setRol(jsonArreglo.getJSONObject(i).getString("nombres" ));
                lista.add(r);
            }
            ArrayAdapter<Trabajador> a = new ArrayAdapter<Trabajador>(this, android.R.layout.simple_list_item_activated_1,lista);
            libenefi.setAdapter(a);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                Intent i = new Intent(trabajadores.this,admin.class);
                startActivity(i);
                break;
        }
    }
}