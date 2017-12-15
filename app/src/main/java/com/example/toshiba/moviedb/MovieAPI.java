package com.example.toshiba.moviedb;

import com.example.toshiba.moviedb.POJOMovieAPI.POJOMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TOSHIBA on 22/11/2017.
 */

public interface MovieAPI {
    @GET("/3/discover/movie")
    Call<POJOMovie> getMovies(@Query("api_key") String apiKey,
                              @Query("language") String language,
                              @Query("sort_by") String sortBy,
                              @Query("page") String page);
}
