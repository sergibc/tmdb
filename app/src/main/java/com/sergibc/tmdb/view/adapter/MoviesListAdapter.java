package com.sergibc.tmdb.view.adapter;

import com.sergibc.tmdb.R;
import com.sergibc.tmdb.model.MovieItemViewModel;
import com.sergibc.tmdb.model.MovieViewModel;
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

    private MovieViewModel movieViewModel;

    private List<MovieItemViewModel> movieItemViewModels;

    public MoviesListAdapter(Context context) {
        this.context = context;
        this.movieViewModel = null;
        this.movieItemViewModels = new ArrayList<>();
    }

    public void setMovieViewModel(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;
    }

    public void setMovieItemViewModels(List<MovieItemViewModel> movieItemViewModels) {
        this.movieItemViewModels = movieItemViewModels;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        MovieItemViewModel movie = movieItemViewModels.get(position);

        if (movie != null) {
            holder.getMovieTitle().setText(movie.getTitle());
            holder.getMovieYear().setText(movie.getYear());
            holder.getMovieOverview().setText(movie.getOverview());
            if (!TextUtils.isEmpty(movie.getImagePath())) {
                Picasso.with(context)
                        .load("http://image.tmdb.org/t/p/w500" + movie.getImagePath())
                        .into(holder.getMovieImage());
            }
        }
    }

    @Override
    public int getItemCount() {
        return movieItemViewModels.size();
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
}
