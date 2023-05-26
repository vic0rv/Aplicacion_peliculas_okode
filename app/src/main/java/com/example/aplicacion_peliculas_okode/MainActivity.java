package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.aplicacion_peliculas_okode.API.APIConnection;

import com.example.aplicacion_peliculas_okode.API.APIInterface;
import com.example.aplicacion_peliculas_okode.Adapters.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity implements APIInterface {
    APIConnection api = new APIConnection();
    RecyclerView rv_movies;
    MovieAdapter movieAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_movies = findViewById(R.id.rv_movies);
        api.getResponseMovies(this);

    }

    @Override
    public void onResponse(String responseBody) {
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
                        movieAdapter = new MovieAdapter(arrayTitulos);
                        rv_movies.setAdapter(movieAdapter);
                    }
                });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
    }
}