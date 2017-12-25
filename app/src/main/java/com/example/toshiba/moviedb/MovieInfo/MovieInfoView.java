package com.example.toshiba.moviedb.MovieInfo;

import java.util.List;

/**
 * Created by TOSHIBA on 22/12/2017.
 */

public interface MovieInfoView {
     void setMovieInfoPage(String poster, String title, String description,
                                 String releaseDate, String status, String runTime,
                                 String revenue, List<String> genres, List<String> castPictures, List<String> castNames);

     void showMovieInfoPageError();
}
