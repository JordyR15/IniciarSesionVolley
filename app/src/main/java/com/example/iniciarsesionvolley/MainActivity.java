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

import java.util.HashMap;
import java.util.Map;

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
            String urlService = "https://api.uealecpeterson.net/public/login";
            responseTV = (TextView) findViewById(R.id.lblDatos);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlService,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                            responseTV.setText("Sesión iniciada: " + response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
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

}