package com.example.toshiba.moviedb.MovieInfo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TOSHIBA on 17/12/2017.
 */

public class POJOMovieInfoCastResult {
    private Integer maxCastSize = 3;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cast")
    @Expose
    private List<POJOMovieInfoCast> cast = null;

    public Integer getMaxCastSize(){
        return maxCastSize;
    }

    public void setMaxCastSize(Integer maxCastSize){
        this.maxCastSize = maxCastSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<POJOMovieInfoCast> getCast() {
        return cast;
    }

    public void setCast(List<POJOMovieInfoCast> cast) {
        this.cast = cast;
    }


}
