package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplicacion_peliculas_okode.API.APIConnection;
import com.example.aplicacion_peliculas_okode.API.APIInterface;
import com.example.aplicacion_peliculas_okode.Adapters.MovieAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MovieDetails extends AppCompatActivity implements APIInterface {
    APIConnection api; APIInterface callback;
    String clickedTitle; String title; String originalTitle; String releaseDate; String language;
    String backdrop; Double voteAvg; String overview; ImageView ivBackdrop;
    TextView tvTitle; TextView tvOriginalTitle; TextView tvDate; TextView tvLanguage; TextView tvVote; TextView tvOverview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvTitle = findViewById(R.id.tv_title);
        tvOriginalTitle = findViewById(R.id.tv_originalTitle);
        tvDate = findViewById(R.id.tv_date);
        tvLanguage = findViewById(R.id.tv_language);
        tvVote = findViewById(R.id.tv_vote);
        tvOverview = findViewById(R.id.tv_overview);
        ivBackdrop = findViewById(R.id.iv_backdrop);

        api = new APIConnection();
        callback = this;

        clickedTitle = (String) getIntent().getSerializableExtra("title");
        api.getPeliculasPopulares(callback);
    }

    @Override
    public void onResponsePeliculas(String responseBody) {
        try {
            JSONObject json = new JSONObject(responseBody);
            JSONArray results = json.getJSONArray("results");
            // Recorrer la lista de pelis
            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);
                title = movie.getString("title");
                if (title.toLowerCase().equals(clickedTitle.toLowerCase())) {
                    originalTitle = movie.getString("original_title");
                    releaseDate = movie.getString("release_date");
                    language = movie.getString("original_language");
                    backdrop = "https://image.tmdb.org/t/p/w500" + movie.getString("backdrop_path");
                    voteAvg = movie.getDouble("vote_average");
                    overview = movie.getString("overview");
                    break;
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvTitle.setText(title);
                    tvOriginalTitle.setText(originalTitle);
                    tvDate.setText(releaseDate);
                    tvLanguage.setText(language);
                    Picasso.get().load(backdrop).into(ivBackdrop);
                    tvVote.setText(voteAvg.toString() + " / 10");
                    tvOverview.setText(overview);
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
}