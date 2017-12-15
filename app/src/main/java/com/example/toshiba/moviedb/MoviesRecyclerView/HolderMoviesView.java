package com.example.toshiba.moviedb.MoviesRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.toshiba.moviedb.R;

/**
 * Created by TOSHIBA on 20/11/2017.
 */

public class HolderMoviesView extends RecyclerView.ViewHolder implements InterfaceMoviesView {


    TextView tvMovieTitle;
    ImageView ivMoviePoster;
    TextView tvMovieGenres;
    TextView tvMovieRating;
    TextView tvMovieDesc;
    public HolderMoviesView(View itemView) {
        super(itemView);
        tvMovieTitle = (TextView) itemView.findViewById(R.id.tvMovieTitle);
        ivMoviePoster = (ImageView) itemView.findViewById(R.id.ivMoviePoster);
        tvMovieRating = (TextView) itemView.findViewById(R.id.tvMovieRating);
        tvMovieDesc = (TextView) itemView.findViewById(R.id.tvMovieDesc);

    }

    @Override
    public void setTitle(String title) {
        tvMovieTitle.setText(title);
    }

    @Override
    public void setPoster(String fullPosterPath) {
        Glide.with(itemView.getContext()).load(fullPosterPath).into(ivMoviePoster);
    }


    @Override
    public void setRating(String rating) {
        tvMovieRating.setText(rating);
    }

    @Override
    public void setDescription(String description) {
        tvMovieDesc.setText(description);
    }
}
