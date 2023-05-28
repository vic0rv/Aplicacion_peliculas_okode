package com.example.aplicacion_peliculas_okode.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion_peliculas_okode.Models.Movie;
import com.example.aplicacion_peliculas_okode.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private List<Movie> movieList;
    private List<Movie> moviesToDisplay;
    OnMovieClickListener listener;

    public interface OnMovieClickListener{
        void onMovieClick(Movie movie);
    }
    public MovieAdapter(List<Movie> movieList, OnMovieClickListener listener) {
        this.movieList = movieList;
        moviesToDisplay = new ArrayList<>(movieList);
        moviesToDisplay.addAll(movieList);
        this.listener = listener;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_view, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = moviesToDisplay.get(position);
        String title = movie.getTitle();
        String image = "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();
        holder.tv_row.setText(title);
        Picasso.get().load(image).into(holder.iv_row);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClick(movie);
            }
        });
    }
    public void search(String text) {
        if (text.isEmpty()) {
            moviesToDisplay.clear();
            moviesToDisplay.addAll(movieList);
        } else {
            List<Movie> matchingMovies = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                matchingMovies = movieList.stream()
                        .filter(movie -> movie.getTitle().toLowerCase().contains(text.toLowerCase()))
                        .collect(Collectors.toList());
            }
            moviesToDisplay.clear();
            if(matchingMovies != null) {
                moviesToDisplay.addAll(matchingMovies);
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return moviesToDisplay.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_row;
        CardView cv_row;
        ImageView iv_row;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_row = itemView.findViewById(R.id.tv_row);
            cv_row = itemView.findViewById(R.id.cv_row);
            iv_row = itemView.findViewById(R.id.iv_row);

        }
    }
}
