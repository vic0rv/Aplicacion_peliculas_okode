package com.example.aplicacion_peliculas_okode.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion_peliculas_okode.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private String[] titles;

    public MovieAdapter(String[] titles) {
        this.titles = titles;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = titles[position];
        //holder.textViewTitle.setText(title); CAMBIAR
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardRow = itemView.findViewById(R.id.cardRowMovie);
        }
    }
}


