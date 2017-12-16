package com.example.toshiba.moviedb.MoviesRecyclerView;

import android.app.Activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.toshiba.moviedb.MovieInfo.MovieInfoActivity;
import com.example.toshiba.moviedb.R;

/**
 * Created by TOSHIBA on 20/11/2017.
 */

public class HolderMoviesView extends RecyclerView.ViewHolder implements InterfaceMoviesView {

    LinearLayout movieLayout;
    TextView tvMovieTitle;
    ImageView ivMoviePoster;
    TextView tvMovieRating;
    TextView tvMovieDesc;


    public HolderMoviesView(View itemView) {
        super(itemView);
        movieLayout = (LinearLayout) itemView.findViewById(R.id.movieLayout);
        tvMovieTitle = (TextView) itemView.findViewById(R.id.tvMovieTitle);
        ivMoviePoster = (ImageView) itemView.findViewById(R.id.ivMoviePoster);
        tvMovieRating = (TextView) itemView.findViewById(R.id.tvMovieRating);
        tvMovieDesc = (TextView) itemView.findViewById(R.id.tvMovieDesc);

    }

    @Override
    public void movieLayoutClicked(final String movieId) {
        movieLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), MovieInfoActivity.class);
                intent.putExtra("MOVIE_ID" ,movieId);
                ((Activity) itemView.getContext()).startActivity(intent);

            }
        });
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
