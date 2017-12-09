package com.example.toshiba.moviedb;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesListPresenter;

import java.util.List;

/**
 * Created by TOSHIBA on 25/11/2017.
 */

public interface RecyclerViewSetUp {
        void getRecyclerViewData();
        void setUpRecyclerView(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager);

}
