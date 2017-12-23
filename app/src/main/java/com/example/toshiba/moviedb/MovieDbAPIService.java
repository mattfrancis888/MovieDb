package com.example.toshiba.moviedb;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TOSHIBA on 22/11/2017.
 */

public class MovieDbAPIService {
    //service class to faciliaate api usages
    private final String BASE_URL = "https://api.themoviedb.org/";
    private final String languangeParam = "en-us";
    private final String sortBy = "popularity.desc";
    private final String pageCount = "1";
    private final String startingImagePath = "http://image.tmdb.org/t/p/w500/";

    //API Key does not to be hidden
    private final String API_KEY = "267f6c03dccb4096c3b41afeb9ac7d26";
    public MovieDbAPI getAPI(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MovieDbAPI.class);
    }

    public String getAPIKey() {
        return API_KEY;
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
