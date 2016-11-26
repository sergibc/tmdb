package com.sergibc.tmdb.view.adapter.viewholder;

import com.sergibc.tmdb.R;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * View holder for movie items
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_image)
    ImageView movieImage;

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @BindView(R.id.movie_year)
    TextView movieYear;

    @BindView(R.id.movie_overview)
    TextView movieOverview;

    // TODO review Butterknife
    public MovieViewHolder(View itemView) {
        super(itemView);
//        ButterKnife.bind(this, itemView);
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
