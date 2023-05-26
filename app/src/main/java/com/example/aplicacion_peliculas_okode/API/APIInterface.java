package com.example.aplicacion_peliculas_okode.API;

import org.json.JSONArray;

public interface APIInterface {

        void onResponse(String responseBody);
        void onFailure(Exception e);

}
