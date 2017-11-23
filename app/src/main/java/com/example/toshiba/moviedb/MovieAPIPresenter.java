package com.example.toshiba.moviedb;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TOSHIBA on 23/11/2017.
 */

public class MovieAPIPresenter{
    APIServiceModel apiServiceModel;
    public MovieAPIPresenter(APIServiceModel apiServiceModel){
        this.apiServiceModel = apiServiceModel;
    }
    public void getMovies(String apiKey, String language, String sortBy, String page){
        apiServiceModel.getAPI().getMovies(apiKey, language, sortBy, page).enqueue(new Callback<POJOMovie>() {
            @Override
            public void onResponse(Call<POJOMovie> call, Response<POJOMovie> response) {
                Log.d("blue", "love you");
            }

            @Override
            public void onFailure(Call<POJOMovie> call, Throwable t) {
                Log.d("blue", "i miss you");
            }
        });
    }
}
