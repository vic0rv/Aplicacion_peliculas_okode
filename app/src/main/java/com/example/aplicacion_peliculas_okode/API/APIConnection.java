
package com.example.aplicacion_peliculas_okode.API;

import java.io.IOException;

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
                String responseBody = response.body().string();
                callback.onResponsePeliculas(responseBody);
            }
        });

    }
}
