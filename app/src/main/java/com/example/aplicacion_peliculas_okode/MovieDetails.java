package com.example.aplicacion_peliculas_okode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        String title = (String) getIntent().getSerializableExtra("title");
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
}