package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplicacion_peliculas_okode.Models.Movie;
import com.squareup.picasso.Picasso;


public class MovieDetails extends AppCompatActivity {
    Movie movieClicked;
    ImageView ivBackdrop;
    TextView tvTitle;
    TextView tvOriginalTitle;
    TextView tvDate;
    TextView tvLanguage;
    TextView tvVote;
    TextView tvOverview;

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

        movieClicked = (Movie) getIntent().getSerializableExtra("movie");
        setData();
    }

    public void setData() {
        tvTitle.setText(movieClicked.getTitle());
        tvOriginalTitle.setText(movieClicked.getOriginal_title());
        tvDate.setText(movieClicked.getRelease_date());
        tvLanguage.setText(movieClicked.getOriginal_language());
        Picasso.get().load(movieClicked.getBackdrop()).into(ivBackdrop);
        tvVote.setText(movieClicked.getVote_average() + " / 10");
        tvOverview.setText(movieClicked.getOverview());

    }
}