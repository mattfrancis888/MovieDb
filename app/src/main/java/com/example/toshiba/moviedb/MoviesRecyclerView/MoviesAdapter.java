package com.example.toshiba.moviedb.MoviesRecyclerView;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.toshiba.moviedb.R;

/**
 * Created by TOSHIBA on 20/11/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<HolderMoviesView>{

    private final MoviesListPresenter moviesListPresenter;

    public MoviesAdapter(MoviesListPresenter moviesListPresenter) {
        this.moviesListPresenter = moviesListPresenter;
    }

    @Override
    public HolderMoviesView onCreateViewHolder(ViewGroup parent, int viewType) {
       return new HolderMoviesView(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_adapter_item, parent, false));
    }

    @Override
    public void onBindViewHolder(HolderMoviesView holder, int position) {
        moviesListPresenter.onBindAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return moviesListPresenter.getMoviesSize();
    }
}
