package com.example.toshiba.moviedb;

import android.content.Context;
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

public class MovieAPIPresenter{
    MovieAPIService movieApiService;
    MovieAPIPresenterListener listner;
    Context context;

    public interface MovieAPIPresenterListener{
        void moviesDataRetrieved(List<String> titles, List<String> posters, List<String> ratings, List<String> descriptions);
    }

    public MovieAPIPresenter(Context context, MovieAPIPresenterListener listener){
        this.context = context;
        this.movieApiService = new MovieAPIService();
        this.listner = listener;
    }

    public void getMovies(){
        movieApiService.getAPI().getMovies(
                movieApiService.getAPIKey(context),  movieApiService.getLanguangeParam(),
                movieApiService.getSortBy(),  movieApiService.getPageCount()).enqueue(new Callback<POJOMovie>() {
            @Override
            public void onResponse(Call<POJOMovie> call, Response<POJOMovie> response) {
                List<String> titles = new ArrayList();
                List<String> posters = new ArrayList();
                List<String> ratings  = new ArrayList();
                List<String> descriptions  = new ArrayList();
                String imagePath = "http://image.tmdb.org/t/p/w500/";
                POJOMovie body = response.body();
                int size = 4 ;

                for(int i = 0; i < size;i++){
                    titles.add(body.getResults().get(i).getTitle());
                    posters.add(imagePath + body.getResults().get(i).getPosterPath());
                    ratings.add(String.valueOf(body.getResults().get(i).getVoteAverage()));
                    descriptions.add(body.getResults().get(i).getOverview());
                }


                listner.moviesDataRetrieved(titles, posters, ratings, descriptions );

            }

            @Override
            public void onFailure(Call<POJOMovie> call, Throwable t) {
                Log.d("blue", "fail ya");
            }
        });
    }
}
