package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.SearchView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.example.aplicacion_peliculas_okode.API.APIConnection;
import com.example.aplicacion_peliculas_okode.API.APIInterface;
import com.example.aplicacion_peliculas_okode.Adapters.MovieAdapter;
import com.example.aplicacion_peliculas_okode.Models.Movie;


public class MainActivity extends AppCompatActivity implements APIInterface, SearchView.OnQueryTextListener, MovieAdapter.OnMovieClickListener{
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList;
    MovieAdapter.OnMovieClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = findViewById(R.id.sv_movie);
        searchView.setOnQueryTextListener(this);

        recyclerView = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        movieList = new ArrayList<>();
        listener = this;

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.rojo1)));
        }

        APIConnection apiConnection = new APIConnection();
        apiConnection.getPeliculasPopulares(this);

    }

    @Override
    public void onResponsePeliculas(String responseBody) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<Movie> movies = APIConnection.parseMovieResponse(responseBody);
                movieList.addAll(movies);
                movieAdapter = new MovieAdapter(movieList, listener);
                recyclerView.setAdapter(movieAdapter);
                movieAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onErrorPeliculas(IOException e) {
        e.printStackTrace();
    }

    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        movieAdapter.search(newText);
        return false;
    }
}