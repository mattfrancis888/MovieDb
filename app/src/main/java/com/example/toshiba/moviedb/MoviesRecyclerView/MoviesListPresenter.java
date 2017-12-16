package com.example.toshiba.moviedb.MoviesRecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOSHIBA on 20/11/2017.
 */

public class MoviesListPresenter {
    private List<String> movieIds;
    private List<String> titles;
    private List<String> images;
    private List<String> ratings;
    private List<String> descriptions;

    public void setData(List<String> movieIds, List<String> titles, List<String> images, List<String> ratings, List<String> descriptions) {
        this.movieIds = movieIds;
        this.titles = titles;
        this.images = images;
        this.ratings = ratings;
        this.descriptions = descriptions;

    }

    public void addData(List<String> movieIds, List<String> titles, List<String> images, List<String> ratings, List<String> descriptions) {
        this.movieIds.addAll(movieIds);
        this.titles.addAll(titles);
        this.images.addAll(images);
        this.ratings.addAll(ratings);
        this.descriptions.addAll(descriptions);

    }

    public void onBindAtPosition(InterfaceMoviesView interfaceMoviesView, int position) {
        interfaceMoviesView.movieLayoutClicked(movieIds.get(position));
        interfaceMoviesView.setTitle(titles.get(position));
        interfaceMoviesView.setPoster(images.get(position));
        interfaceMoviesView.setRating(ratings.get(position));
        interfaceMoviesView.setDescription(descriptions.get(position));

    }

    public int getMoviesSize() {
        return titles.size();
    }
}
