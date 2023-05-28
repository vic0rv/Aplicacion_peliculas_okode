package com.example.aplicacion_peliculas_okode.Models;


import java.io.Serializable;

public class Movie implements Serializable {
    int id;
    String title;
    String overview;
    String original_language;
    String poster_path;
    String release_date;
    double vote_average;
    String original_title;
    String backdrop;

    public Movie(){
    }
    public Movie(int id, String title,String posterPath,String originalTitle,String releaseDate,String language,String backdrop,Double voteAvg,String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.original_language = language;
        this.poster_path = posterPath;
        this.release_date = releaseDate;
        this.vote_average = voteAvg;
        this.original_title = originalTitle;
        this.backdrop = backdrop;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public String getOriginal_title() {
        return original_title;
    }

    public String getBackdrop() {
        return backdrop;
    }
}
