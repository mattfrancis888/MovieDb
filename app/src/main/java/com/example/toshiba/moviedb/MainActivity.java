package com.example.toshiba.moviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainPresenter().setUpRecyclerView(
                (RecyclerView) findViewById(R.id.recyclerView),
                new LinearLayoutManager(this)
                );

    }
}
