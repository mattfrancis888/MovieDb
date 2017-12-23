package com.example.toshiba.moviedb.MoviesRecyclerView.Model;

import java.util.List;

/**
 * Created by TOSHIBA on 23/12/2017.
 */

public class Movie {
    private String movieId;
    private String title;
    private String poster;
    private String rating;
    private String descriptions;

    public Movie(String movieId, String title, String poster,
                 String rating, String description){
        this.movieId = movieId;
        this.title = title;
        this.poster = poster;
        this.rating = rating;
        this.descriptions = description;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getRating() {
        return rating;
    }

    public String getDescriptions() {
        return descriptions;
    }


}
