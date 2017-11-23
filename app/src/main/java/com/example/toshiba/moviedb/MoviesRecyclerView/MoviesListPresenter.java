package com.example.toshiba.moviedb.MoviesRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOSHIBA on 20/11/2017.
 */

public class MoviesListPresenter {

    private List<String> movies = new ArrayList<>();

    public void setData(List list){
        movies = list;
    }

    public void onBindAtPosition(InterfaceMoviesView interfaceMoviesView, int position){
        interfaceMoviesView.setTitle(movies.get(position));
    }

    public int getMoviesSize() {
        return movies.size();
    }
}
