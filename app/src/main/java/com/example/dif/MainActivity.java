package com.example.dif;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Quieres Salir?")
                .setMessage("Estas seguro de salir de la Aplicacion?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();//cerrar la aplicacion xd
                    }
                }).create().show();
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

    private void ValidarUsuarios() {
        SharedPreferences ponysUsuario = getSharedPreferences("Ponys", Context.MODE_PRIVATE);
        SharedPreferences.Editor pony = ponysUsuario.edit();
        SharedPreferences preferencias = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor mario = preferencias.edit();


        String user_email = email.getText().toString().trim();
        String user_pass = password.getText().toString().trim();

        if (user_email.isEmpty()) {
            email.setError("Ingrese correo");
        } else if (user_pass.isEmpty()) {
            password.setError("Ingrese contraseña");
        } else {
            StringRequest request = new StringRequest(Request.Method.POST, "https://checolin00p2.000webhostapp.com/DIF/dif_php/login.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonjObject = new JSONObject(response);
                        String Rol = jsonjObject.getString("rol");
                        String idusaer = jsonjObject.getString("id_usuario");
                        String id_trabajador = jsonjObject.getString("id_usuario");
                        String nombre_trabajador = jsonjObject.getString("nombres");
                        String ApellidoPa_trabajador = jsonjObject.getString("AP");
                        switch (Rol) {
                            case "1":
                                Intent a = new Intent(MainActivity.this, admin.class);
                                startActivity(a);
                                finish();
                                break;
                            case "2":
                                Toast.makeText(MainActivity.this,"IDk",Toast.LENGTH_LONG).show();
                                break;
                            case "3":
                                Intent a2 = new Intent(MainActivity.this, Juridico.class);
                                a2.putExtra("id_suser",idusaer);
                                startActivity(a2);
                                finish();
                                break;
                            case "4":
                                Intent a3 = new Intent(MainActivity.this, area_trabajosocial.class);
                                startActivity(a3);
                                finish();
                                break;
                            case "5":
                                mario.putString("id_trabajador",id_trabajador);
                                mario.commit();
                                Intent a4 = new Intent(MainActivity.this, area_psicologia.class);
                                startActivity(a4);
                                finish();
                                break;
                            case "6":
                                mario.putString("id_trabajador",id_trabajador);
                                mario.putString("nombres_trabajador",nombre_trabajador);
                                mario.putString("ApellidoPa",ApellidoPa_trabajador);
                                mario.commit();
                                Intent a5 = new Intent(MainActivity.this, area_medica.class);
                                startActivity(a5);
                                finish();
                                break;
                            case "7":
                                Toast.makeText(MainActivity.this,"IDk",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "No existe Usuario o Contraseña", Toast.LENGTH_LONG).show();
                                break;
                        }
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "Usuario o Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();

                    parametros.put("correo", user_email);
                    parametros.put("password", user_pass);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);
        }
    }
}