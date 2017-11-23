package com.example.toshiba.moviedb;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TOSHIBA on 22/11/2017.
 */

public class APIServiceModel {
    private static String URL;
    public APIServiceModel(String URL){
        this.URL = URL;
    }

    public MovieAPI getAPI(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MovieAPI.class);
    }

}
