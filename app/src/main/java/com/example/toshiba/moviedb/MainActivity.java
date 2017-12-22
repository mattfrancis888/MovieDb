package com.example.toshiba.moviedb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.toshiba.moviedb.MovieInfo.MovieInfoActivity;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesAdapter;
import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesListPresenter;
import com.example.toshiba.moviedb.Util.KeyboardUtil;
import com.example.toshiba.moviedb.Util.ProgressDialogUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
       MoviesView {

    int pageCount = 1;
    MoviesListPresenter moviesListPresenter;
    MoviesPresenter moviesPresenter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    MoviesAdapter moviesAdapter;
    AutoCompleteTextView actvSearchBar;
    ArrayList<String> moviesTitle;
    ArrayList<String> moviesIds;
    ArrayAdapter<String> actvSearchBarAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KeyboardUtil.hideKeyboard(this);
//        VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
//        component.provideVehicle();
        initViews();

        moviesListPresenter = new MoviesListPresenter();
        moviesPresenter = new MoviesPresenter(this, this);

        getRecyclerViewData();
        setUpSearchBar();



    }


    @Override
    public void updateRecyclerViewAdapter(int pageCount, List<String> movieIds, List<String> movies, List<String> posters,
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
    public void errorInUpdatingRecyclerViewAdapter() {
        Toast.makeText(MainActivity.this, "Failed to retrieve movie data", Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
    }

    @Override
    public void showMovieTitlesInSearchBar(List<String> titles, List<String> movieIds) {
        progressBar.setVisibility(View.GONE);
        actvSearchBarAdapter.clear();
        actvSearchBarAdapter.addAll(titles);
        actvSearchBarAdapter.getFilter().filter(actvSearchBar.getText().toString(), null);

        //movie ids
        moviesIds.clear();
        moviesIds.addAll(movieIds);
    }

    @Override
    public void moviesTitleRetrievalFail() {
        Toast.makeText(MainActivity.this, "Failed to search movies, check your internet connection and try again", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        KeyboardUtil.hideKeyboard(this);
    }

    public void initViews(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        actvSearchBar = (AutoCompleteTextView) findViewById(R.id.actvSearchBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void getRecyclerViewData() {
        moviesPresenter.
                getMovies(pageCount);
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

    @Override
    public void setUpSearchBar(){
        moviesTitle = new ArrayList<>();
        moviesIds = new ArrayList<>();
        actvSearchBarAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, moviesTitle);
        actvSearchBar.setAdapter(actvSearchBarAdapter);

        actvSearchBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                String selection = (String)parent.getItemAtPosition(position);
                moviesPresenter.getMoviesByTitle(selection);

                Intent intent = new Intent(MainActivity.this, MovieInfoActivity.class);
                intent.putExtra(getResources().getString(R.string.movie_id) , moviesIds.get(position));
                startActivity(intent);
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ///do something


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                progressBar.setVisibility(View.VISIBLE);
                moviesPresenter.getMoviesByTitle(actvSearchBar.getText().toString());
            }
        };
        actvSearchBar.addTextChangedListener(textWatcher);
    }
}
