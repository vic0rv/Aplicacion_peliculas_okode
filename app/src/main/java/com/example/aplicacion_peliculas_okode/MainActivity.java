package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.aplicacion_peliculas_okode.API.APIConnection;
import com.example.aplicacion_peliculas_okode.API.CallbackPeliculas;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements CallbackPeliculas {
    APIConnection api = new APIConnection();
    TextView prueba;
    String respuesta;
    RecyclerView rv_movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_movies = findViewById(R.id.rv_movies);

        prueba = findViewById(R.id.textView2);
        api.getPeliculasPopulares(this);


        /**
         rv_movies.setLayoutManager(new LinearLayoutManager(MainActivity.this));
         movieAdapter = new MovieAdapter(arrayTitulos);
         rv_movies.setAdapter(movieAdapter);**/
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
        e.printStackTrace();
    }
}