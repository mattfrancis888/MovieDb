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
        void moviesDataRetrieved(List movies);
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
                List result = new ArrayList();
                int size = 2 ;
                for(int i = 0; i < size;i++){
                    result.add(response.body().getResults().get(i).getTitle());
                }

                listner.moviesDataRetrieved(result);

            }

            @Override
            public void onFailure(Call<POJOMovie> call, Throwable t) {
                Log.d("blue", "fail ya");
            }
        });
    }
}
