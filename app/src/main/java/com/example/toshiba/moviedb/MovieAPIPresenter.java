package com.example.toshiba.moviedb;

import android.os.AsyncTask;
import android.util.Log;

import com.example.toshiba.moviedb.MoviesRecyclerView.MoviesListPresenter;
import com.example.toshiba.moviedb.POJOMovieAPI.POJOMovie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TOSHIBA on 23/11/2017.
 */

public class MovieAPIPresenter{
    APIServiceModel apiServiceModel;
    MovieAPIPresenterListener listner;

    public interface MovieAPIPresenterListener{
        void moviesReady(List movies);
    }

    public MovieAPIPresenter(APIServiceModel apiServiceModel, MovieAPIPresenterListener listener){
        this.apiServiceModel = apiServiceModel;
        this.listner = listener;
    }

    public void getMovies(String apiKey, String language, String sortBy, String page){
        final Call<POJOMovie> call = apiServiceModel.getAPI().getMovies(apiKey, language, sortBy, page);
        POJOMovie movies = null;
        try {
            movies = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List result = new ArrayList();
        for(int i = 0; i < 2;i++){
            result.add(movies.getResults().get(i).getTitle());

        }
        listner.moviesReady(result);

    }
}
