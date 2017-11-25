package com.example.toshiba.moviedb;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by TOSHIBA on 25/11/2017.
 */

public interface MovieAPIAndRecyclerViewContract {

    interface Presenter {
        void setUpRecyclerView(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, List dataList);

        List setRecyclerViewData();
    }

    interface View{
        String getAPIKey();
    }
}
