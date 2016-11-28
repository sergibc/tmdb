package com.sergibc.tmdb.view.adapter.viewholder;

import com.sergibc.tmdb.R;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * View holder for movie items
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView movieImage;

    private TextView movieTitle;

    private TextView movieYear;

    private TextView movieOverview;

    public MovieViewHolder(View itemView) {
        super(itemView);
        movieImage = (ImageView) itemView.findViewById(R.id.movie_image);
        movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
        movieYear = (TextView) itemView.findViewById(R.id.movie_year);
        movieOverview = (TextView) itemView.findViewById(R.id.movie_overview);
    }

    public ImageView getMovieImage() {
        return movieImage;
    }

    public TextView getMovieTitle() {
        return movieTitle;
    }

    public TextView getMovieYear() {
        return movieYear;
    }

    public TextView getMovieOverview() {
        return movieOverview;
    }
}
