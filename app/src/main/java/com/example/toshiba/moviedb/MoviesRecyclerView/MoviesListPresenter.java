package com.example.toshiba.moviedb.MoviesRecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TOSHIBA on 20/11/2017.
 */

public class MoviesListPresenter {

    private List<String> titles = new ArrayList<>();
    private List<String> images = new ArrayList<>();;
    private List<String> ratings;
    private List<String> descriptions;

    public void setData(List<String> titles, List<String> images, List<String> ratings, List<String> descriptions) {
        this.titles = titles;
        this.images = images;
        this.ratings = ratings;
        this.descriptions = descriptions;

    }

    public void onBindAtPosition(InterfaceMoviesView interfaceMoviesView, int position) {
        interfaceMoviesView.setTitle(titles.get(position));
        interfaceMoviesView.setPoster(images.get(position));
        interfaceMoviesView.setRating(ratings.get(position));
        interfaceMoviesView.setDescription(descriptions.get(position));
    }

    public int getMoviesSize() {
        return titles.size();
    }
}
