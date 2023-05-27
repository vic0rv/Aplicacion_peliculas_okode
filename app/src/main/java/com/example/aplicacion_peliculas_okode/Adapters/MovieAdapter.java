package com.example.aplicacion_peliculas_okode.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
        if (position % 2 == 0) {
            holder.cv_row.setBackgroundColor(ContextCompat.getColor(holder.tv_row.getContext(), R.color.blue2));
        } else {
            holder.cv_row.setBackgroundColor(ContextCompat.getColor(holder.tv_row.getContext(), R.color.blue1));
        }
        String title = titles[position];
        holder.tv_row.setText(title);
    }
    @Override
    public int getItemCount() {
        return titles.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_row;
        CardView cv_row;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_row = itemView.findViewById(R.id.tv_row);
            cv_row = itemView.findViewById(R.id.cv_row);
        }
    }
}
