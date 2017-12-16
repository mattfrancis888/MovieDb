package com.example.toshiba.moviedb;

import android.content.Context;
import android.content.res.Resources;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TOSHIBA on 22/11/2017.
 */

public class MovieAPIService {
    //service class to faciliaate api usages
    private final String BASE_URL = "https://api.themoviedb.org/";
    private final String languangeParam = "en-us";
    private final String sortBy = "popularity.desc";
    private final String pageCount = "1";
    private final String startingImagePath = "http://image.tmdb.org/t/p/w500/";
    public MovieAPI getAPI(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MovieAPI.class);
    }

    public String getAPIKey(Context context) {
        return context.getResources().getString(R.string.api_key);
    }
    //need this path in the beggining to show a picture
    public String getStartingImagePath(){
        return startingImagePath;
    }
    public String getLanguangeParam(){
        return languangeParam;
    }

    public String getSortBy(){
        return sortBy;
    }

    public String getPageCount(){
        return pageCount;
    }

}
