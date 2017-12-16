package com.example.toshiba.moviedb;

import com.example.toshiba.moviedb.MovieInfo.POJOMovieInfoCast;
import com.example.toshiba.moviedb.MovieInfo.POJOMovieInfoCastResult;
import com.example.toshiba.moviedb.MovieInfo.POJOMovieInfoResult;
import com.example.toshiba.moviedb.POJOMovieAPI.POJOMovie;
import com.example.toshiba.moviedb.POJOMovieAPI.POJOMovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("/3/movie/{movie_id}")
    Call<POJOMovieInfoResult> getMovieDetails(@Path("movie_id") String movie_id,
                                           @Query("api_key") String apiKey);

    @GET("/3/movie/{movie_id}/credits")
    Call<POJOMovieInfoCastResult> getMovieCredits(@Path("movie_id") String movie_id,
                                                  @Query("api_key") String apiKey);
}
