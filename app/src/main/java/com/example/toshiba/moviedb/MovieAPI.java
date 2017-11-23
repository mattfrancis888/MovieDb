package com.example.toshiba.moviedb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by TOSHIBA on 22/11/2017.
 */

public interface MovieAPI {
    @GET("/discover/movie?sort_by=popularity.desc")
    Call<POJOMovie> getMovies();
}
