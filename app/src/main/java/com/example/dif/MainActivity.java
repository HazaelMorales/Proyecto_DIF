package com.example.dif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button iniciar,registrar;
    EditText email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.correo);
        password = (EditText) findViewById(R.id.contra);
        iniciar = (Button) findViewById(R.id.login);
        registrar = (Button) findViewById(R.id.changepass);
        iniciar.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                ValidarUsuarios();
                break;
            case R.id.changepass:
                Toast.makeText(MainActivity.this, "Pues Acuerdate", Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void ValidarUsuarios(){
        String user_email = email.getText().toString().trim();
        String user_pass = password.getText().toString().trim();

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.4/dif_php/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    try {
                        JSONObject jsonjObject = new JSONObject(response);
                        String Rol = jsonjObject.getString("rol");
                        switch (Rol) {
                            case "1":
                                Intent a = new Intent(MainActivity.this, admin.class);
                                startActivity(a);
                                break;
                            case "2":
                                Intent a1 = new Intent(MainActivity.this, general.class);
                                startActivity(a1);
                                break;
                            case "3":
                                Intent a2 = new Intent(MainActivity.this, areajuridica.class);
                                startActivity(a2);
                                break;
                            case "4":
                                Toast.makeText(MainActivity.this, "En proceso", Toast.LENGTH_LONG).show();
                                break;
                            case "5":
                                Toast.makeText(MainActivity.this, "En proceso", Toast.LENGTH_LONG).show();
                                break;
                            case "7":
                                Intent a6 = new Intent(MainActivity.this, areajuridica_secre.class);
                                startActivity(a6);
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "No existe Usuario", Toast.LENGTH_LONG).show();
                                break;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Usuario o Contraseña Incorrecta",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> parametros = new HashMap<String, String>();

                parametros.put("correo",user_email);
                parametros.put("password",user_pass);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}