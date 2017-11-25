package com.example.toshiba.moviedb;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.toshiba.moviedb.InterfaceMainView;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesAdapter;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesListPresenter;

import java.util.List;

/**
 * Created by TOSHIBA on 21/11/2017.
 */

public class MainPresenter implements MovieAPIAndRecyclerViewContract.Presenter, MovieAPIPresenter.MovieAPIPresenterListener {
    APIServiceModel apiServiceModel;
    MovieAPIAndRecyclerViewContract.View view;
    MoviesListPresenter moviesListPresenter;

    public MainPresenter(APIServiceModel apiServiceModel,
                         MovieAPIAndRecyclerViewContract.View view,
                         MoviesListPresenter moviesListPresenter){
        this.apiServiceModel = apiServiceModel;
        this.view = view;
        this.moviesListPresenter = moviesListPresenter;
    }
    @Override
    public void setUpRecyclerView(final RecyclerView recyclerView, final LinearLayoutManager linearLayoutManager,
                                  List dataList) {


        Log.d("blue", "setUpRecyclerView");
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                setRecyclerViewData();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("blue", "setUpRecyclerView on post execute");
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(new MoviesAdapter(moviesListPresenter));
            }
        }.execute();


    }

    @Override
    public List setRecyclerViewData() {
        Log.d("blue", "setRecyclerViewData");
        new MovieAPIPresenter(apiServiceModel, this).
                getMovies(view.getAPIKey(), "en-US", "popularity.desc", "1");


        return null;
    }


    @Override
    public void moviesReady(List movies) {
        Log.d("blue", "moviesReady");
        moviesListPresenter.setData(movies);
    }
}
