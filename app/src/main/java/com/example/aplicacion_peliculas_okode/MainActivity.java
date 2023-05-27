package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
<<<<<<< HEAD
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
=======

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements CallbackPeliculas {
    APIConnection api = new APIConnection();
    TextView prueba;
    String respuesta;
>>>>>>> parent of 20bd900 (RecycleView implementado)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        rv_movies = findViewById(R.id.rv_movies);
        api.getResponseMovies(movie);
        moviesList = movie.getMovieList();
=======
        prueba = findViewById(R.id.tv1);
        api.getPeliculasPopulares(this);

>>>>>>> parent of 20bd900 (RecycleView implementado)

        TextView prueba = findViewById(R.id.textView2);
        prueba.setText(moviesList.get(0).getTitle());
        /**
        rv_movies.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        movieAdapter = new MovieAdapter(arrayTitulos);
        rv_movies.setAdapter(movieAdapter);**/
    }

<<<<<<< HEAD

=======
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
>>>>>>> parent of 20bd900 (RecycleView implementado)
}