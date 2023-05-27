package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;


import com.example.aplicacion_peliculas_okode.API.APIConnection;
import com.example.aplicacion_peliculas_okode.API.APIInterface;
import com.example.aplicacion_peliculas_okode.Adapters.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements APIInterface, SearchView.OnQueryTextListener{
    APIConnection api = new APIConnection();
    RecyclerView rv_movies;
    SearchView movieSeeker;
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_movies = findViewById(R.id.rv_movies);
        movieSeeker = findViewById(R.id.sv_movie);


        api.getPeliculasPopulares(this);
        movieSeeker.setOnQueryTextListener(this);

    }

    @Override
    public void onResponsePeliculas(String responseBody) {
        try {
            JSONObject json = new JSONObject(responseBody);
            JSONArray results = json.getJSONArray("results");
            ArrayList<String> arrayTitulos = new ArrayList<>();
            ArrayList<String> arrayPosters = new ArrayList<>();
            // Recorrer la lista de pelis
            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);
                String title = movie.getString("title");
                String poster = movie.getString("poster_path");
                arrayTitulos.add(title);
                arrayPosters.add(poster);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    rv_movies.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    movieAdapter = new MovieAdapter(arrayTitulos,arrayPosters);
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

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        movieAdapter.search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        movieAdapter.search(newText);
        return false;
    }
}