package com.example.toshiba.moviedb;

import android.content.Context;

import com.example.toshiba.moviedb.MoviesRecyclerView.Model.POJOMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TOSHIBA on 23/11/2017.
 */

public class MoviesPresenter {
    MovieDbAPIService movieDbApiService;
    MoviesView moviesView;
    Context context;

    public MoviesPresenter(Context context, MoviesView moviesView){
        this.context = context;
        this.movieDbApiService = new MovieDbAPIService();
        this.moviesView = moviesView;
    }

    public void getMovies(final int pageCount){
        movieDbApiService.getAPI().getMovies(
                movieDbApiService.getAPIKey(context),  movieDbApiService.getLanguangeParam(),
                movieDbApiService.getSortBy(), String.valueOf(pageCount)).enqueue(new Callback<POJOMovie>() {
            @Override
            public void onResponse(Call<POJOMovie> call, Response<POJOMovie> response) {
                List<String> movieIds = new ArrayList<>();
                List<String> titles = new ArrayList();
                List<String> posters = new ArrayList();
                List<String> ratings  = new ArrayList();
                List<String> descriptions  = new ArrayList();
                POJOMovie body = response.body();
                if(pageCount <= body.getTotalPages()) {
                    int size = body.getResults().size();

                    for (int i = 0; i < size; i++) {
                        movieIds.add(body.getResults().get(i).getId().toString());
                        titles.add(body.getResults().get(i).getTitle());
                        posters.add(movieDbApiService.getStartingImagePath() + body.getResults().get(i).getPosterPath());
                        ratings.add(String.valueOf(body.getResults().get(i).getVoteAverage()));
                        descriptions.add(body.getResults().get(i).getOverview());
                    }

                    moviesView.updateRecyclerViewAdapter(pageCount, movieIds, titles, posters, ratings, descriptions);

                } else {
                    //will be empty
                    moviesView.updateRecyclerViewAdapter(pageCount, movieIds, titles, posters, ratings, descriptions);
                }
            }

            @Override
            public void onFailure(Call<POJOMovie> call, Throwable t) {
                moviesView.errorInUpdatingRecyclerViewAdapter();
            }
        });
    }

    public void getMoviesByTitle(final String word){
        movieDbApiService.getAPI().getMoviesByTitle(movieDbApiService.getAPIKey(context), word).enqueue(new Callback<POJOMovie>() {
            @Override
            public void onResponse(Call<POJOMovie> call, Response<POJOMovie> response) {
                POJOMovie body = response.body();

                List<String> moviesTitle = new ArrayList<>();
                List<String> movieIds = new ArrayList<>();
                if(response.body() != null) {
                    for (int i = 0; i < body.getResults().size(); i++) {
                        moviesTitle.add(body.getResults().get(i).getTitle());
                        movieIds.add(body.getResults().get(i).getId().toString());
                    }
                }

                moviesView.showMovieTitlesInSearchBar(moviesTitle, movieIds);
            }

            @Override
            public void onFailure(Call<POJOMovie> call, Throwable t) {
                moviesView.moviesTitleRetrievalFail();
            }
        });
    }

}
