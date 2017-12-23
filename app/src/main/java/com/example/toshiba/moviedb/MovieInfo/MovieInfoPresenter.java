package com.example.toshiba.moviedb.MovieInfo;

import android.content.Context;
import android.util.Log;

import com.example.toshiba.moviedb.MovieDbAPI;
import com.example.toshiba.moviedb.MovieDbAPIService;
import com.example.toshiba.moviedb.MovieInfo.Model.POJOMovieInfoCastResult;
import com.example.toshiba.moviedb.MovieInfo.Model.POJOMovieInfoResult;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TOSHIBA on 15/12/2017.
 */

public class MovieInfoPresenter {
    private MovieDbAPIService movieDbApiService;
    private MovieInfoView movieInfoView;


    public MovieInfoPresenter(MovieInfoView movieInfoView) {
        this.movieDbApiService = new MovieDbAPIService();
        this.movieInfoView = movieInfoView;
    }

    public void getMovieInfo(final String movieId) {
        final MovieDbAPI movieDbAPI = movieDbApiService.getAPI();
        final String apiKey = movieDbApiService.getAPIKey();

        movieDbAPI.getMovieDetails(movieId,
                apiKey ).enqueue(new Callback<POJOMovieInfoResult>() {
            @Override
            public void onResponse(Call<POJOMovieInfoResult> call, Response<POJOMovieInfoResult> response) {
                POJOMovieInfoResult movieInfoBody = response.body();
                final String posterPath = movieDbApiService.getStartingImagePath() + movieInfoBody.getPosterPath();
                final String title = movieInfoBody.getTitle();
                final String description = movieInfoBody.getOverview();
                final String releaseDate = movieInfoBody.getReleaseDate();
                final List<String> genres = new ArrayList<>();
                final String status = movieInfoBody.getStatus();

                final String runTime = Integer.toString(movieInfoBody.getRuntime());
                final String runTimeFormatted = runTime.substring(0, 1) + "h " + runTime.substring(1, runTime.length()) + "m";
                final String revenue =   NumberFormat.getInstance().format(movieInfoBody.getRevenue());


                for (int i = 0; i < movieInfoBody.getGenres().size(); i++) {
                    genres.add(movieInfoBody.getGenres().get(i).getName());
                }

                //get casts in movie credts
                movieDbAPI.getMovieCredits(movieId, apiKey).enqueue(new Callback<POJOMovieInfoCastResult>() {
                    @Override
                    public void onResponse(Call<POJOMovieInfoCastResult> call, Response<POJOMovieInfoCastResult> response) {
                        Log.d("blue", "succ credit");
                        List<String> castPictures = new ArrayList<>();
                        List<String> names = new ArrayList<>();
                        POJOMovieInfoCastResult movieCastBody = response.body();

                        for(int i = 0; i < movieCastBody.getMaxCastSize(); i++){
                            castPictures.add(movieDbApiService.getStartingImagePath() + movieCastBody.getCast().get(i)
                                    .getProfilePath());
                            names.add(movieCastBody.getCast().get(i).getName());
                        }

                        movieInfoView.setMovieInfoPage(posterPath, title, description,releaseDate,
                                status, runTimeFormatted, revenue, genres, castPictures, names);

                    }

                    @Override
                    public void onFailure(Call<POJOMovieInfoCastResult> call, Throwable t) {
                        movieInfoView.showMovieInfoPageError();
                    }
                });



            }

            @Override
            public void onFailure(Call<POJOMovieInfoResult> call, Throwable t) {
                movieInfoView.showMovieInfoPageError();
            }
        });
    }
}
