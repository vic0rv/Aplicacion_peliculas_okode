package com.example.aplicacion_peliculas_okode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIConnection {
    public static void main(String[] args){
        try{
            URL url = new URL("https://api.themoviedb.org/3/movie/11?api_key=0b5a2d0df283cf542cc09cc420f24fa4");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lee la respuesta de la API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Procesa la respuesta de la API
                System.out.println(response.toString());
            } else {
                // Maneja el error de la respuesta
                System.out.println("Error: " + responseCode);
            }

            // Cierra la conexión
            connection.disconnect();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
