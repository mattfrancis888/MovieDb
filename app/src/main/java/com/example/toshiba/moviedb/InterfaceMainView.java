package com.example.toshiba.moviedb;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by TOSHIBA on 21/11/2017.
 */

public interface InterfaceMainView {
    void setUpRecyclerView(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, List dataList);
    List setRecyclerViewSize();


}
