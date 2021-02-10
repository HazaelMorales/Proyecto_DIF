package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dif.funciones.Beneficiario;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Juridico_vercasos extends AppCompatActivity {
    private AsyncHttpClient cliente;
    private String casoxd,idxd,userxd;
    ListView slist;
    public void onBackPressed() {
        Intent a1 = new Intent(Juridico_vercasos.this,Juridico.class);
        a1.putExtra("id_sbene",idxd);
        startActivity(a1);
        finish();//matar  activity actual xd
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juridico_vercasos);
        slist = (ListView) findViewById(R.id.ofelia);
        cliente = new AsyncHttpClient();
        idxd = getIntent().getStringExtra("id_sbene");//variable donde se cargo el valorde la varibale pasada
        casoxd = getIntent().getStringExtra("id_scaso");
        userxd = getIntent().getStringExtra("id_suser");
        listabCasos();
        slist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sg = parent.getItemAtPosition(position).toString();
                casoxd = getNumeros(sg);
                Toast.makeText(Juridico_vercasos.this,casoxd,Toast.LENGTH_LONG).show();
                Intent a1 = new Intent(Juridico_vercasos.this,Juridico_newsession.class);
                a1.putExtra("id_scaso",casoxd);
                a1.putExtra("id_sbene",idxd);
                a1.putExtra("id_suser",userxd);
                startActivity(a1);
                finish();

            }
        });
    }
    private void listabCasos(){
        String url = "https://checolin00p2.000webhostapp.com/DIF/dif_php/sobtenerCaso.php";
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
        ArrayList<JuridicoCasos> lista = new ArrayList<JuridicoCasos>();
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for(int i=0; i<jsonArreglo.length();i++){
                JuridicoCasos r = new JuridicoCasos();
                r.setRol(jsonArreglo.getJSONObject(i).getString("id_caso")+" "+jsonArreglo.getJSONObject(i).getString("fecha_apertura") + " " + jsonArreglo.getJSONObject(i).getString("descripcion_general"));
                lista.add(r);
            }
            ArrayAdapter<JuridicoCasos> a = new ArrayAdapter<JuridicoCasos>(this, android.R.layout.simple_list_item_activated_1,lista);
            slist.setAdapter(a);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static String getNumeros(String cadena){
        char [] cadena_div = cadena.toCharArray();
        String n = "";
        for(int i = 0; i < 2; i++ ){
            if(cadena_div[i] != ' '){
                n+=cadena_div[i];
            }
        }
        return n;
    }
}