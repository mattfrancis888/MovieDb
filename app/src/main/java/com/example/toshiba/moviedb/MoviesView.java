package com.example.toshiba.moviedb;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by TOSHIBA on 22/12/2017.
 */

public interface MoviesView {
    void updateRecyclerViewAdapter(int pageCount, List<String> movieIds, List<String> movies, List<String> posters,
                                   List<String> ratings, List<String> descriptions);
    void errorInUpdatingRecyclerViewAdapter();
    void showMovieTitlesInSearchBar(List<String> titles, List<String> movieIds);
    void moviesTitleRetrievalFail();
}
