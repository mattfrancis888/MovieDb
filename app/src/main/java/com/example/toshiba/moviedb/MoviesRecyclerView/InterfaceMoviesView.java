package com.example.toshiba.moviedb.MoviesRecyclerView;

/**
 * Created by TOSHIBA on 21/11/2017.
 */

public interface InterfaceMoviesView {
     void movieLayoutClicked(String movieId);
     void setTitle(String title);
     void setPoster(String posterPath);
     void setRating(String rating);
     void setDescription(String description);
}
