package com.example.dif;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dif.funciones.Beneficiario;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class mostrarbeneficiarios extends AppCompatActivity implements  View.OnClickListener {
    private AsyncHttpClient cliente;
    ListView libenefi;
    Button mostrarbene;
    public int id_beneficiario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarbeneficiarios);
        cliente = new AsyncHttpClient();
        libenefi = (ListView) findViewById(R.id.libenefi);
        mostrarbene = (Button) findViewById(R.id.submit);
        mostrarbene.setOnClickListener(this);
        listabeneficiarios();
        libenefi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent a1 = new Intent(mostrarbeneficiarios.this,areajuridicaseguimiento.class);
                startActivity(a1);
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
        ArrayList <Beneficiario> lista = new ArrayList<Beneficiario>();
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
                Intent i = new Intent(mostrarbeneficiarios.this,interfazjuridica.class);
                startActivity(i);
                break;
        }
    }
}

/*--------------------------------------------------------------------------------------------------------------------------
 segundo intento de conectar y mostrar los datos :,v
public class mostrarbeneficiarios extends AppCompatActivity {
    private Context context;
    private static String url ="http://192.168.0.103/dif_1/obtenerBeneficiarios.php";
    private static final String nombres = "nombre";
    ArrayList<HashMap<String,String>> jsonlist = new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarbeneficiarios);
        new GetJSONActivity(mostrarbeneficiarios.this).execute();
    }
    private class GetJSONActivity extends AsyncTask<String, Void, String>{
        public GetJSONActivity(mostrarbeneficiarios activity){
            context =activity;
        }
        protected  String doInBackground(final String... args){
            JSONParser jParser = new JSONParser();
            JSONArray json = jParser.GetJSONfromUrl(url);
            for (int i=0; i< json.length();i++){
                try {
                    JSONObject c = json.getJSONObject(i);
                    String vpers = c.getString(nombres);
                    HashMap<String,String> map = new HashMap<String, String>();
                    map.put(nombres,vpers);
                    jsonlist.add(map);
                }catch (JSONException e){
                    e.printStackTrace();
                    return "Error creando variables";
                }
            }
            return "EXITO";
        }
        protected void onPostExecute(String success){
            ListAdapter adapter = new SimpleAdapter(context, jsonlist,
                    R.layout.items, new String[]  {nombres},
                    new int[] { R.id.benefi});
            setListView(adapter);
            getListView();
        }
        private void setListView(ListAdapter adapter) {
        }
        private void getListView() {
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mostrarbeneficiarios.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
*/

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------
 Primer intento de intentar conectar y mostrar los datos :,v
public class mostrarbeneficiarios extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrarbeneficiarios);
;
    }
    public void btnAgg (View view){
        startActivity(new Intent(getApplicationContext(), registrobeneficiario.class));
    }
    private String mostrar(){
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://192.168.0.103/dif_1/obtenerBeneficiarios.php");
        String resultado = "";
        HttpResponse response;
        try{
            response = httpclient.execute(httppost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultado;
    }
    private String convertStringToString(InputStream is) throws IOException{
        if(is !=null){
            StringBuilder sb = new StringBuilder();
            String line;
            try{
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                while((line = reader.readLine()) != null){
                    sb.append(line).append("")
                }
            }
        }
    }
}
*/