package com.example.toshiba.moviedb;

import com.example.toshiba.moviedb.MoviesRecyclerView.Model.Movie;
import com.example.toshiba.moviedb.MoviesRecyclerView.Model.POJOMovie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TOSHIBA on 23/11/2017.
 */

public class MoviesPresenter {
    private MovieDbAPIService movieDbApiService = new MovieDbAPIService();
    private MoviesView moviesView;

    public void getMovies(final int pageCount) {
        movieDbApiService.getAPI().getMovies(
                movieDbApiService.getAPIKey(), movieDbApiService.getLanguangeParam(),
                movieDbApiService.getSortBy(), String.valueOf(pageCount)).enqueue(new Callback<POJOMovie>() {
            @Override
            public void onResponse(Call<POJOMovie> call, Response<POJOMovie> response) {
                List<Movie> movies = new ArrayList();

                POJOMovie body = response.body();
                if (pageCount <= body.getTotalPages()) {
                    int size = body.getResults().size();

                    for (int i = 0; i < size; i++) {
                        String moveId = body.getResults().get(i).getId().toString();
                        String title = body.getResults().get(i).getTitle();
                        String poster = movieDbApiService.getStartingImagePath() + body.getResults().get(i).getPosterPath();
                        String rating = String.valueOf(body.getResults().get(i).getVoteAverage());
                        String description = body.getResults().get(i).getOverview();
                        movies.add(new Movie(moveId, title, poster, rating, description));


                    }

                }

                if (moviesView != null) {
                    moviesView.updateRecyclerViewAdapter(pageCount, movies);
                }
            }

            @Override
            public void onFailure(Call<POJOMovie> call, Throwable t) {
                moviesView.errorInUpdatingRecyclerViewAdapter();
            }
        });
    }

    public void getMoviesByTitle(final String word) {
        movieDbApiService.getAPI().getMoviesByTitle(movieDbApiService.getAPIKey(), word).enqueue(new Callback<POJOMovie>() {
            @Override
            public void onResponse(Call<POJOMovie> call, Response<POJOMovie> response) {
                POJOMovie body = response.body();

                List<String> moviesTitle = new ArrayList<>();
                List<String> movieIds = new ArrayList<>();
                if (response.body() != null) {
                    for (int i = 0; i < body.getResults().size(); i++) {
                        moviesTitle.add(body.getResults().get(i).getTitle());
                        movieIds.add(body.getResults().get(i).getId().toString());
                    }
                }
                if (moviesView != null) {
                    moviesView.showMovieTitlesInSearchBar(moviesTitle, movieIds);
                }
            }

            @Override
            public void onFailure(Call<POJOMovie> call, Throwable t) {
                if (moviesView != null) {
                    moviesView.moviesTitleRetrievalFail();
                }
            }
        });
    }


    public void attachView(MoviesView moviesView) {
        this.moviesView = moviesView;
    }


    public void detachView() {
        moviesView = null;
    }
}
