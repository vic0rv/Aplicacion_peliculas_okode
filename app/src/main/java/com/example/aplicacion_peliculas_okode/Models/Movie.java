package com.example.aplicacion_peliculas_okode.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie{
    int id;
    String title;
    int[] genre_ids;
    String overview;
    String original_language;
    String poster_path;
    String release_date;
    double vote_average;

    private ArrayList<Movie> movieList = new ArrayList<>();
    public Movie(){

    };
    public Movie(int id, String title, int[] genre_ids, String overview, String original_language, String poster_path, String release_date, double vote_average) {
        this.id = id;
        this.title = title;
        this.genre_ids = genre_ids;
        this.overview = overview;
        this.original_language = original_language;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public ArrayList<Movie> getMovieList(JSONArray results){
        ArrayList<Movie> movieList = new ArrayList<>();
        try {
            // Recorrer la lista de pelis
            for (int i = 0; i < results.length(); i++) {
                JSONObject JSONmovie = results.getJSONObject(i);

                //Array con los generos de la película
                JSONArray JSONid_genders = JSONmovie.getJSONArray("genre_ids");
                int[] id_genders = new int[JSONid_genders.length()];
                for (int j = 0; j < JSONid_genders.length(); j++) {
                    id_genders[j] = JSONid_genders.getInt(j);
                }

                Movie movie = new Movie();
                movie.setId(JSONmovie.getInt("id"));
                movie.setTitle(JSONmovie.getString("title"));
                movie.setGenre_ids(id_genders);
                movie.setOverview(JSONmovie.getString("overview"));
                movie.setOriginal_language(JSONmovie.getString("original_language"));
                movie.setPoster_path(JSONmovie.getString("poster_path"));
                movie.setRelease_date(JSONmovie.getString("release_date"));
                movie.setVote_average(JSONmovie.getDouble("vote_average"));

                movieList.add(movie);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }


}
