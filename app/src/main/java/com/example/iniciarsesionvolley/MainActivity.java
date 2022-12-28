package com.example.iniciarsesionvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import Model.User;
import Utiles.ErrorLog;

public class MainActivity extends AppCompatActivity {
    private TextView correo, clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recoger variables
        correo = (TextView) findViewById(R.id.txtCorreo);
        clave = (TextView) findViewById(R.id.txtClave);

    }

    private TextView responseTV;

    public void loginPrueba(View view){
        try{
            final String parClave = correo.getText().toString().trim();
            final String parCorreo = clave.getText().toString().trim();
            responseTV = (TextView) findViewById(R.id.lblDatos);
            String urlService = "https://api.uealecpeterson.net/public/login";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlService,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                            tokenParseo(response);
                            //responseTV.setText("Método probar: " + response);
                            //response.toString()
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                            responseTV.setText("Mal: ");
                        }
                    }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("correo", parClave);
                    params.put("clave", parCorreo);
                    return params;
                }
            };
            requestQueue.add(stringRequest);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }

    public String tokenParseo(String data){
        try {
            //Enviar token a un label
            responseTV = (TextView) findViewById(R.id.lblDatos);
            //Pattern patron = Pattern.compile("(ID\\:\\s[0-9])");
            String data1 = "{" + data.replaceAll(".*\\{", "");
            JSONObject objectJson = new JSONObject(data1);
            JSONArray JSONlista = objectJson.getJSONArray("access_token");
            /*JSONObject banco;
            String lista = "";
            for (int i = 0; i < JSONlista.length(); i++){
                banco = JSONlista.getJSONObject(i);
                lista = lista + "token: "  + banco.getString("access_token").toString();
            }*/
            ErrorLog.info(data1);
            responseTV.setText("Método probar: ");
            return "Token: ";
        }catch (Exception e){
            ErrorLog.error(e.getMessage());
            return "";
        }
    }

}