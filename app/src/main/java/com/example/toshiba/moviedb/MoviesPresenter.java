package com.example.toshiba.moviedb;

import com.example.toshiba.moviedb.MoviesRecyclerView.Model.Movie;
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

    public MoviesPresenter(MoviesView moviesView){
        this.movieDbApiService = new MovieDbAPIService();
        this.moviesView = moviesView;
    }



    public void getMovies(final int pageCount){
        movieDbApiService.getAPI().getMovies(
                movieDbApiService.getAPIKey(),  movieDbApiService.getLanguangeParam(),
                movieDbApiService.getSortBy(), String.valueOf(pageCount)).enqueue(new Callback<POJOMovie>() {
            @Override
            public void onResponse(Call<POJOMovie> call, Response<POJOMovie> response) {
                List<Movie> movies = new ArrayList();

                POJOMovie body = response.body();
                if(pageCount <= body.getTotalPages()) {
                    int size = body.getResults().size();

                    for (int i = 0; i < size; i++) {
                        String moveId = body.getResults().get(i).getId().toString();
                        String title = body.getResults().get(i).getTitle();
                        String poster = movieDbApiService.getStartingImagePath() + body.getResults().get(i).getPosterPath();
                        String rating = String.valueOf(body.getResults().get(i).getVoteAverage());
                        String description = body.getResults().get(i).getOverview();
                        movies.add(new Movie(moveId, title, poster, rating, description));


                    }

                    moviesView.updateRecyclerViewAdapter(pageCount, movies);

                } else {
                    //will be empty
                    moviesView.updateRecyclerViewAdapter(pageCount, movies);
                }
            }

            @Override
            public void onFailure(Call<POJOMovie> call, Throwable t) {
                moviesView.errorInUpdatingRecyclerViewAdapter();
            }
        });
    }

    public void getMoviesByTitle(final String word){
        movieDbApiService.getAPI().getMoviesByTitle(movieDbApiService.getAPIKey(), word).enqueue(new Callback<POJOMovie>() {
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
