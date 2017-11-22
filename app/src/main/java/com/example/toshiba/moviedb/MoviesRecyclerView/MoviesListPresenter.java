package com.example.toshiba.moviedb.MoviesRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOSHIBA on 20/11/2017.
 */

public class MoviesListPresenter {

    private final List<String> movies = new ArrayList<>();

    public void setData(String title){
        movies.add(title);
    }

    public void onBindAtPosition(InterfaceMoviesView interfaceMoviesView, int position){
        interfaceMoviesView.setTitle(movies.get(position));
    }

    public int getMoviesSize() {
        return movies.size();
    }
}
