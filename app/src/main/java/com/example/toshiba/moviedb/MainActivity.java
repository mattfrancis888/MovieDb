package com.example.toshiba.moviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesAdapter;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesListPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        RecyclerViewSetUp,
        MovieAPIPresenter.MovieAPIPresenterListener{

    MoviesListPresenter moviesListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
//        component.provideVehicle();


        moviesListPresenter = new MoviesListPresenter();
        getRecyclerViewData();


    }

    @Override
    public void getRecyclerViewData() {
        new MovieAPIPresenter(this, this).
                getMovies();
    }

    @Override
    public void moviesDataRetrieved(List<String> movies, List<String> posters,
                                    List<String> ratings, List<String> descriptions) {
        moviesListPresenter.setData(movies, posters, ratings, descriptions);

        setUpRecyclerView(
                (RecyclerView) findViewById(R.id.recyclerView),
                new LinearLayoutManager(this)
        );

    }

    @Override
    public void setUpRecyclerView(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        Log.d("blue", "setUpRecyclerView");
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MoviesAdapter(moviesListPresenter));
    }



}
