package com.example.aplicacion_peliculas_okode.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIConnection {

    public void getResponseMovies(APIInterface callback) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYjVhMmQwZGYyODNjZjU0MmNjMDljYzQyMGYyNGZhNCIsInN1YiI6IjY0NmY2NzNjMTdjNDQzMDExOWIwZTg2YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TpxLlj869gKpa4THo5b_33_1_Boc8ziDMwXrvvKLqaY")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Fallo de la solicitud
                callback.onFailure(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Exito en la solicitud
                String responseBody = response.body().string();
                callback.onResponse(responseBody);
            }
        });
    }
}
