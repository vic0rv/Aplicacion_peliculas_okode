
package com.example.aplicacion_peliculas_okode;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIConnection {

    public void getPeliculasPopulares(APIInterface callback) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwYjVhMmQwZGYyODNjZjU0MmNjMDljYzQyMGYyNGZhNCIsInN1YiI6IjY0NmY2NzNjMTdjNDQzMDExOWIwZTg2YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TpxLlj869gKpa4THo5b_33_1_Boc8ziDMwXrvvKLqaY")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Manejo de errores en caso de fallo de la solicitud
                callback.onErrorPeliculas(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseBody = response.body().string();
                callback.onResponsePeliculas(responseBody);
            }
        });

    }
}
