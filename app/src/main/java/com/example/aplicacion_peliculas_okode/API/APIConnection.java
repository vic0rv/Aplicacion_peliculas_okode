
package com.example.aplicacion_peliculas_okode.API;

import com.example.aplicacion_peliculas_okode.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIConnection {

    public void getPeliculasPopulares(APIInterface callback) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.themoviedb.org/3/movie/popular?language=es-ES&page=1";
        String autorization = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYjVhMmQwZGYyODNjZjU0MmNjMDljYzQyMGYyNGZhNCIsInN1YiI6IjY0NmY2NzNjMTdjNDQzMDExOWIwZTg2YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TpxLlj869gKpa4THo5b_33_1_Boc8ziDMwXrvvKLqaY";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", autorization)
                .build();

        client.newCall(request).enqueue(new Callback() { //enqueue en lugar de execute porque con execute relantizaba la aplicación
            @Override
            public void onFailure(Call call, IOException e) {
                // En caso de fallo de la solicitud
                callback.onErrorPeliculas(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // En caso de acierto en la solicitud
                if(response.body() != null) {
                    String responseBody = response.body().string();
                    callback.onResponsePeliculas(responseBody);
                }
            }
        });
    }
    public static List<Movie> parseMovieResponse(String responseBody) {
        List<Movie> movies = new ArrayList<>(); int id;
        String title; String originalTitle; String releaseDate; String language;
        String backdrop; double voteAvg; String overview; String posterPath;
        try {
            JSONObject responseJson = new JSONObject(responseBody);
            JSONArray resultsArray = responseJson.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject movieJson = resultsArray.getJSONObject(i);
                id = movieJson.getInt("id");
                title = movieJson.getString("title");
                posterPath = movieJson.getString("poster_path");
                originalTitle = movieJson.getString("original_title");
                releaseDate = movieJson.getString("release_date");
                language = movieJson.getString("original_language");
                backdrop = "https://image.tmdb.org/t/p/w500" + movieJson.getString("backdrop_path");
                voteAvg = movieJson.getDouble("vote_average");
                overview = movieJson.getString("overview");
                Movie movie = new Movie(id, title, posterPath, originalTitle, releaseDate, language, backdrop, voteAvg, overview);
                movies.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
