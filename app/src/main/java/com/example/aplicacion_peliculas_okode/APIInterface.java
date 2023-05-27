package com.example.aplicacion_peliculas_okode;

import java.io.IOException;

public interface APIInterface {
    void onResponsePeliculas(String responseBody);
    void onErrorPeliculas(IOException e);
}
}
