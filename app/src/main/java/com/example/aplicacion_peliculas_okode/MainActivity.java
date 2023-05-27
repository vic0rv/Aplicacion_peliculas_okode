package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;


import com.example.aplicacion_peliculas_okode.API.APIConnection;
import com.example.aplicacion_peliculas_okode.API.APIInterface;
import com.example.aplicacion_peliculas_okode.Adapters.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements APIInterface {
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
        try {
            JSONObject json = new JSONObject(responseBody);
            JSONArray results = json.getJSONArray("results");
            String[] arrayTitulos = new String[results.length()];
            // Recorrer la lista de pelis
            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);
                String title = movie.getString("original_title");
                arrayTitulos[i] = title;
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    rv_movies.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    MovieAdapter movieAdapter = new MovieAdapter(arrayTitulos);
                    rv_movies.setAdapter(movieAdapter);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorPeliculas(IOException e) {
        e.printStackTrace();
        //Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();

    }
}