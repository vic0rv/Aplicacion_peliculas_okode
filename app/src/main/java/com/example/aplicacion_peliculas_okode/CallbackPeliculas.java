package com.example.aplicacion_peliculas_okode;

import java.io.IOError;
import java.io.IOException;

public interface CallbackPeliculas {
    void onResponsePeliculas(String responseBody);
    void onErrorPeliculas(IOException e);
}
