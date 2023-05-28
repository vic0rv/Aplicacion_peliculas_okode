package com.example.aplicacion_peliculas_okode.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion_peliculas_okode.MovieDetails;
import com.example.aplicacion_peliculas_okode.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<String> titles; private ArrayList<String> images;
    private ArrayList<String> titlesToSeek; private ArrayList<String> imagesToSeek;
    OnMovieClickListener listener;

    public interface OnMovieClickListener{
        void onMovieClick(String title);
    }
    public MovieAdapter(ArrayList<String> titles,ArrayList<String> images, OnMovieClickListener listener) {
        this.titles = titles;
        this.images = images;
        titlesToSeek = new ArrayList<>();
        titlesToSeek.addAll(titles);
        imagesToSeek = new ArrayList<>();
        imagesToSeek.addAll(images);
        this.listener = listener;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_view, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.cv_row.setBackgroundColor(ContextCompat.getColor(holder.tv_row.getContext(), R.color.black));
        } else {
            holder.cv_row.setBackgroundColor(ContextCompat.getColor(holder.tv_row.getContext(), R.color.black));
        }
        String title = titles.get(position);
        String image = "https://image.tmdb.org/t/p/w500" + images.get(position);
        holder.tv_row.setText(title);
        Picasso.get().load(image).into(holder.iv_row);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieClick(title);
            }
        });
    }
    @SuppressLint("NotifyDataSetChanged")
    public void search(String text) {
        if (text.isEmpty()) {
            titles.clear();
            titles.addAll(titlesToSeek);
            images.clear();
            images.addAll(imagesToSeek);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<String> matchingTitles = titlesToSeek.stream()
                        .filter(title -> title.toLowerCase().contains(text.toLowerCase()))
                        .collect(Collectors.toList());
                titles.clear();
                titles.addAll(matchingTitles);

                List<String> matchingImages = titlesToSeek.stream()
                        .filter(title -> title.toLowerCase().contains(text.toLowerCase()))
                        .map(title -> imagesToSeek.get(titlesToSeek.indexOf(title)))
                        .collect(Collectors.toList());

                images.clear();
                images.addAll(matchingImages);
            } else {
                titles.clear();
                images.clear();
                for (String title : titlesToSeek) {
                    if (title.toLowerCase().contains(text.toLowerCase())) {
                        titles.add(title);
                        images.add(imagesToSeek.get(titlesToSeek.indexOf(title)));
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return titles.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_row;
        CardView cv_row;
        ImageView iv_row;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_row = itemView.findViewById(R.id.tv_row);
            cv_row = itemView.findViewById(R.id.cv_row);
            iv_row = itemView.findViewById(R.id.iv_row);

        }
    }
}
