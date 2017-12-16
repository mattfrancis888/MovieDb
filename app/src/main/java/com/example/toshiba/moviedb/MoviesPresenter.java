package com.example.toshiba.moviedb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.toshiba.moviedb.POJOMovieAPI.POJOMovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TOSHIBA on 23/11/2017.
 */

public class MoviesPresenter {
    MovieAPIService movieApiService;
    MovieAPIPresenterListener listner;
    Context context;


    public interface MovieAPIPresenterListener{
        void moviesDataRetrieved(int page, List<String> movieId, List<String> titles, List<String> posters, List<String> ratings, List<String> descriptions);
        void moviesDataRetrievalFail();
    }

    public MoviesPresenter(Context context, MovieAPIPresenterListener listener){
        this.context = context;
        this.movieApiService = new MovieAPIService();
        this.listner = listener;
    }

    public void getMovies(final int pageCount){
        Log.d("loveumeg", "getMovies()");
        movieApiService.getAPI().getMovies(
                movieApiService.getAPIKey(context),  movieApiService.getLanguangeParam(),
                movieApiService.getSortBy(), String.valueOf(pageCount)).enqueue(new Callback<POJOMovie>() {
            @Override
            public void onResponse(Call<POJOMovie> call, Response<POJOMovie> response) {
                Log.d("loveu", "onResponse" + pageCount);
                List<String> movieIds = new ArrayList<String>();
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
                        posters.add(movieApiService.getStartingImagePath() + body.getResults().get(i).getPosterPath());
                        ratings.add(String.valueOf(body.getResults().get(i).getVoteAverage()));
                        descriptions.add(body.getResults().get(i).getOverview());
                    }

                    listner.moviesDataRetrieved(pageCount, movieIds, titles, posters, ratings, descriptions);

                } else {
                    //will be empty
                    listner.moviesDataRetrieved(pageCount, movieIds, titles, posters, ratings, descriptions);
                }
            }

            @Override
            public void onFailure(Call<POJOMovie> call, Throwable t) {
                listner.moviesDataRetrievalFail();
                Log.d("blue", "fail ya");
            }
        });
    }

}
