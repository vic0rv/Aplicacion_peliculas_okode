package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements CallbackPeliculas {
    APIConnection api = new APIConnection();
    TextView prueba;
    String respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prueba = findViewById(R.id.tv1);
        api.getPeliculasPopulares(this);


    }

    @Override
    public void onResponsePeliculas(String responseBody) {
        respuesta = responseBody;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                prueba.setText(respuesta);
            }
        });
    }

    @Override
    public void onErrorPeliculas(IOException e) {
        respuesta = e.toString();
        //Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();

    }
}