package com.example.toshiba.moviedb.MoviesRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.toshiba.moviedb.R;

/**
 * Created by TOSHIBA on 20/11/2017.
 */

public class HolderMoviesView extends RecyclerView.ViewHolder implements InterfaceMoviesView {


    TextView tvMovie;
    public HolderMoviesView(View itemView) {
        super(itemView);
        tvMovie = (TextView) itemView.findViewById(R.id.tvMovie);
    }

    @Override
    public void setTitle(String title) {
        tvMovie.setText(title);
    }
}
