package com.example.toshiba.moviedb.MoviesRecyclerView;

import com.example.toshiba.moviedb.MoviesRecyclerView.Model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOSHIBA on 20/11/2017.
 */

public class MoviesListPresenter {
    private List<String> movieIds = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<String> posters = new ArrayList<>();
    private List<String> ratings = new ArrayList<>();
    private List<String> descriptions = new ArrayList<>();


    public void updateData(List<Movie> movies) {
        for(Movie movie : movies){
            movieIds.add(movie.getMovieId());
            titles.add(movie.getTitle());
            posters.add(movie.getPoster());
            ratings.add(movie.getRating());
            descriptions.add(movie.getDescriptions());
        }
    }


    public void onBindAtPosition(InterfaceMoviesView interfaceMoviesView, int position) {
        interfaceMoviesView.movieLayoutClicked(movieIds.get(position));
        interfaceMoviesView.setTitle(titles.get(position));
        interfaceMoviesView.setPoster(posters.get(position));
        interfaceMoviesView.setRating(ratings.get(position));
        interfaceMoviesView.setDescription(descriptions.get(position));

    }

    public int getMoviesSize() {
        return titles.size();
    }
}
