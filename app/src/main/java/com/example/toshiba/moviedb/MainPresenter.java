package com.example.toshiba.moviedb;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.toshiba.moviedb.InterfaceMainView;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesAdapter;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesListPresenter;

/**
 * Created by TOSHIBA on 21/11/2017.
 */

public class MainPresenter implements InterfaceMainView {

    @Override
    public void setUpRecyclerView(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        MoviesListPresenter moviesListPresenter = new MoviesListPresenter();
        moviesListPresenter.setData("blue");
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MoviesAdapter(moviesListPresenter));
    }
}
