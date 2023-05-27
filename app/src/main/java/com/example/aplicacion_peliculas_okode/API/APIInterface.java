package com.example.aplicacion_peliculas_okode.API;

import com.example.aplicacion_peliculas_okode.Models.Movie;

import org.json.JSONArray;

import java.util.ArrayList;

public interface APIInterface {

        void onResponseGetMovies(String responseBody);
        void onFailureMovies(Exception e);
}
