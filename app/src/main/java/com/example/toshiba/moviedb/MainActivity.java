package com.example.toshiba.moviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIServiceModel apiServiceModel = new APIServiceModel(getResources().getString(R.string.base_url)
                +getResources().getString(R.string.api_key));

        new MovieAPIPresenter(apiServiceModel).getMovies();

        //create method that returns data

        new MainPresenter().setUpRecyclerView(
                (RecyclerView) findViewById(R.id.recyclerView),
                new LinearLayoutManager(this), new ArrayList()
                );



    }
}
