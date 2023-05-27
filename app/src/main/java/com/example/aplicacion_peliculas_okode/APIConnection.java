<<<<<<< HEAD:app/src/main/java/com/example/aplicacion_peliculas_okode/API/APIConnection.java
package com.example.aplicacion_peliculas_okode.API;

import com.example.aplicacion_peliculas_okode.Models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
=======
package com.example.aplicacion_peliculas_okode;
>>>>>>> parent of 20bd900 (RecycleView implementado):app/src/main/java/com/example/aplicacion_peliculas_okode/APIConnection.java

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIConnection {

<<<<<<< HEAD:app/src/main/java/com/example/aplicacion_peliculas_okode/API/APIConnection.java
    public void getResponseMovies(APIInterface callback) {
        ArrayList<Movie> moviesList = new ArrayList<Movie>();
=======

    public void getPeliculasPopulares(CallbackPeliculas callback) {
>>>>>>> parent of 20bd900 (RecycleView implementado):app/src/main/java/com/example/aplicacion_peliculas_okode/APIConnection.java
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder().url("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYjVhMmQwZGYyODNjZjU0MmNjMDljYzQyMGYyNGZhNCIsInN1YiI6IjY0NmY2NzNjMTdjNDQzMDExOWIwZTg2YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TpxLlj869gKpa4THo5b_33_1_Boc8ziDMwXrvvKLqaY")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
<<<<<<< HEAD:app/src/main/java/com/example/aplicacion_peliculas_okode/API/APIConnection.java
                // Fallo de la solicitud
                callback.onFailureMovies(e);
=======
                // Manejo de errores en caso de fallo de la solicitud
                callback.onErrorPeliculas(e);
>>>>>>> parent of 20bd900 (RecycleView implementado):app/src/main/java/com/example/aplicacion_peliculas_okode/APIConnection.java
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseBody = response.body().string();
<<<<<<< HEAD:app/src/main/java/com/example/aplicacion_peliculas_okode/API/APIConnection.java
                callback.onResponseGetMovies(responseBody);
=======
                callback.onResponsePeliculas(responseBody);
>>>>>>> parent of 20bd900 (RecycleView implementado):app/src/main/java/com/example/aplicacion_peliculas_okode/APIConnection.java
            }
        });

    }
}
