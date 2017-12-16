package com.example.toshiba.moviedb;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesAdapter;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesListPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        RecyclerViewSetUp,
        MoviesPresenter.MovieAPIPresenterListener {

    int pageCount = 1;
    MoviesListPresenter moviesListPresenter;
    MoviesPresenter moviesPresenter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
//        component.provideVehicle();


        moviesListPresenter = new MoviesListPresenter();
        moviesPresenter = new MoviesPresenter(this, this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        getRecyclerViewData();


    }

    @Override
    public void getRecyclerViewData() {
        moviesPresenter.
                getMovies(pageCount);
    }

    @Override
    public void moviesDataRetrieved(int pageCount, List<String> movieIds, List<String> movies, List<String> posters,
                                    List<String> ratings, List<String> descriptions) {

        if (pageCount == 1) {
            moviesListPresenter.setData(movieIds, movies, posters, ratings, descriptions);
            setUpRecyclerView(
                    recyclerView,
                    new LinearLayoutManager(this)
            );
        } else {
            int lastItemSize = moviesListPresenter.getMoviesSize();
            moviesListPresenter.addData(movieIds, movies, posters, ratings, descriptions);
            int newItemSize = moviesListPresenter.getMoviesSize();
            moviesAdapter.notifyItemRangeInserted(lastItemSize, newItemSize);
            progressDialog.dismiss();
        }

    }

    @Override
    public void moviesDataRetrievalFail() {
        Toast.makeText(MainActivity.this, "Failed to retrieve movie data", Toast.LENGTH_LONG);
        progressDialog.dismiss();
    }


    @Override
    public void setUpRecyclerView(RecyclerView recyclerView, final LinearLayoutManager linearLayoutManager) {
        recyclerView.setLayoutManager(linearLayoutManager);
        moviesAdapter = new MoviesAdapter(moviesListPresenter);
        recyclerView.setAdapter(moviesAdapter);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //1 for down
                if (!recyclerView.canScrollVertically(1)) {
                    Log.d("loveu", "gonna miss ya");
                    progressDialog = ProgressDialogUtil.showProgressDialog(MainActivity.this, "Loading...");
                    pageCount++;
                    moviesPresenter.
                            getMovies(pageCount);

                }
            }
        });

    }


}
