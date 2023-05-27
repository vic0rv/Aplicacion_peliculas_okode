package com.example.aplicacion_peliculas_okode.Models;

import java.util.ArrayList;

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

    public String[] getAllTitles(ArrayList<Movie> movieLista){
        String[] titlesList = new String[movieLista.size()];
        for (int i = 0; i< movieLista.size();i++){
            titlesList[i] = movieLista.get(i).getTitle();
        }
        return titlesList;
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


    /**public void onResponseGetMovies(String responseBody) {
        int id;
        String title;
        String overview;
        String original_language;
        String poster_path;
        String release_date;
        double vote_average;
        ArrayList<Movie> movieList = new ArrayList<>();
        try {

            JSONObject json = new JSONObject(responseBody);
            JSONArray results = json.getJSONArray("results");

            // Recorrer la lista de pelis
            for (int i = 0; i < results.length(); i++) {
                JSONObject JSONmovie = results.getJSONObject(i);
                JSONArray JSONid_genders = JSONmovie.getJSONArray("genre_ids");

                //Valores para crear objeto movie

                id = JSONmovie.getInt("id");
                title = JSONmovie.getString("title");

                //Array con los generos de la película
                int[] id_genders = new int[JSONid_genders.length()];
                for (int j = 0; j < JSONid_genders.length(); j++) {
                    id_genders[j] = JSONid_genders.getInt(j);
                }

                overview = JSONmovie.getString("overview");
                original_language = JSONmovie.getString("original_language");
                poster_path = JSONmovie.getString("poster_path");
                release_date = JSONmovie.getString("release_date");
                vote_average = JSONmovie.getDouble("vote_average");

                Movie movie = new Movie(id,title,id_genders,overview,original_language,poster_path,release_date,vote_average);
                movieList.add(movie);
            }
        this.movieList = movieList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return movieList;
    }

    public void onFailureMovies(Exception e) {
        e.printStackTrace();
    }
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }**/


}
