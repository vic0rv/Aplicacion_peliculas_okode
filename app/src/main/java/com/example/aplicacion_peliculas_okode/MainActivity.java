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
import com.example.aplicacion_peliculas_okode.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    APIConnection api = new APIConnection();

    RecyclerView rv_movies;
    MovieAdapter movieAdapter;
    Movie movie = new Movie();
    ArrayList<Movie> moviesList = new ArrayList<Movie>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_movies = findViewById(R.id.rv_movies);
        api.getResponseMovies(movie);
        moviesList = movie.getMovieList();

        TextView prueba = findViewById(R.id.textView2);
        prueba.setText(moviesList.get(0).getTitle());
        /**
        rv_movies.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        movieAdapter = new MovieAdapter(arrayTitulos);
        rv_movies.setAdapter(movieAdapter);**/
    }


}