package com.example.toshiba.moviedb;

import com.example.toshiba.moviedb.MoviesRecyclerView.Model.Movie;

import java.util.List;

/**
 * Created by TOSHIBA on 22/12/2017.
 */

public interface MoviesView {
    void updateRecyclerViewAdapter(int pageCount,List<Movie> movies);
    void errorInUpdatingRecyclerViewAdapter();
    void showMovieTitlesInSearchBar(List<String> titles, List<String> movieIds);
    void moviesTitleRetrievalFail();
}
