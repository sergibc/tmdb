package com.sergibc.tmdb.view.adapter;

import com.sergibc.tmdb.R;
import com.sergibc.tmdb.data.net.ApiConstants;
import com.sergibc.tmdb.model.MovieItemViewModel;
import com.sergibc.tmdb.view.adapter.viewholder.MovieViewHolder;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for movies
 */
public class MoviesListAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Context context;

    private List<MovieItemViewModel> movieItemViewModels;

    public MoviesListAdapter(Context context) {
        this.context = context;
        this.movieItemViewModels = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        MovieItemViewModel movie = movieItemViewModels.get(position);

        if (movie != null) {
            holder.getMovieTitle().setText(getStringField(movie.getTitle()));
            holder.getMovieYear().setText(getStringField(movie.getYear()));
            holder.getMovieOverview().setText(getStringField(movie.getOverview()));
            if (!TextUtils.isEmpty(movie.getImagePath())) {
                Picasso.with(context)
                        .load(ApiConstants.IMAGES_SERVICE + movie.getImagePath())
                        .fit()
                        .centerCrop()
                        .into(holder.getMovieImage());
            } else {
                Picasso.with(context)
                        .load(R.drawable.logo)
                        .into(holder.getMovieImage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return movieItemViewModels.size();
    }

    public List<MovieItemViewModel> getItems() {
        return movieItemViewModels;
    }

    public void setItems(List<MovieItemViewModel> items) {
        this.movieItemViewModels = items;
    }

    public void addItems(List<MovieItemViewModel> items) {
        if (movieItemViewModels == null) {
            movieItemViewModels = new ArrayList<>();
        }

        if (items != null && !items.isEmpty()) {
            movieItemViewModels.addAll(items);
            notifyDataSetChanged();
        }
    }

    public void clearData() {
        if (movieItemViewModels == null) {
            movieItemViewModels = new ArrayList<>();
        } else {
            movieItemViewModels.clear();
        }

        notifyDataSetChanged();
    }

    private String getStringField(String original) {
        return TextUtils.isEmpty(original) ? context.getString(R.string.not_available) : original;
    }
}
