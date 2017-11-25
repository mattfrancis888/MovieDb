package com.example.toshiba.moviedb;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.toshiba.moviedb.DaggerTest.DaggerVehicleComponent;
import com.example.toshiba.moviedb.DaggerTest.VehicleComponent;
import com.example.toshiba.moviedb.DaggerTest.VehicleModule;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesAdapter;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesListPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  MovieAPIAndRecyclerViewContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
//        component.provideVehicle();

        Resources resources = getResources();
        APIServiceModel apiServiceModel = new APIServiceModel(resources.getString(R.string.base_url));



        new MainPresenter(apiServiceModel, this, new MoviesListPresenter()).setUpRecyclerView(
                (RecyclerView) findViewById(R.id.recyclerView),
                new LinearLayoutManager(this), new ArrayList()
                );



    }

    @Override
    public String getAPIKey() {
        return getResources().getString(R.string.api_key);
    }
}
