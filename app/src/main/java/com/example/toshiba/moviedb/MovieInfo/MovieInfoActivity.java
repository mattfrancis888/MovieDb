package com.example.toshiba.moviedb.MovieInfo;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.toshiba.moviedb.MovieAPI;
import com.example.toshiba.moviedb.MovieAPIService;
import com.example.toshiba.moviedb.ProgressDialogUtil;
import com.example.toshiba.moviedb.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TOSHIBA on 11/12/2017.
 */

public class MovieInfoActivity extends AppCompatActivity implements MovieInfoPresenter.MovieInfoPresenterListener {
    ImageView ivPoster;
    TextView tvTitle;
    TextView tvOverview;
    TextView tvGenres;
    TextView tvStatus;
    TextView tvRunTime;
    TextView tvRevenue;
    TextView tvReleaseDate;
    ProgressDialog progressDialog;

    TextView tvCast1;
    TextView tvCast2;
    TextView tvCast3;

    ImageView ivCast1;
    ImageView ivCast2;
    ImageView ivCast3;

    public void initViews(){
        ivPoster = (ImageView) findViewById(R.id.ivPoster);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        tvGenres = (TextView) findViewById(R.id.tvGenres);


        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvRunTime = (TextView) findViewById(R.id.tvRuntime);
        tvRevenue = (TextView) findViewById(R.id.tvRevenue);

        ivCast1 = (ImageView) findViewById(R.id.ivCast1);
        ivCast2 = (ImageView) findViewById(R.id.ivCast2);
        ivCast3 = (ImageView) findViewById(R.id.ivCast3);

        tvCast1 = (TextView) findViewById(R.id.tvCast1);
        tvCast2 = (TextView) findViewById(R.id.tvCast2);
        tvCast3 = (TextView) findViewById(R.id.tvCast3);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        progressDialog = ProgressDialogUtil.showProgressDialog(this, "Loading...");

        initViews();

        MovieInfoPresenter movieInfoPresenter = new MovieInfoPresenter(this, this);
//        movieInfoPresenter.getMovieInfo("346364");
        movieInfoPresenter.getMovieInfo(getIntent().getStringExtra("MOVIE_ID"));


    }



    @Override
    public void moviesInfoRetrieved(String poster, String title, String description,
                                    String releaseDate, String status, String runTime,
                                    String revenue, List<String> genres, List<String> castPictures, List<String> castNames) {
        Log.d("blue", "moviesInfoRetrieved " + title);
//        initViews();
        progressDialog.dismiss();

        tvTitle.setText(title);
        tvOverview.setText(description);
        tvReleaseDate.setText(releaseDate);

        tvGenres.setText(android.text.TextUtils.join(", ", genres));

        Glide.with(this).load(poster).into(ivPoster);


        tvStatus.setText(status);
        tvRunTime.setText(runTime);
        tvRevenue.setText(revenue);

        Log.d("blue", castPictures.get(0));
        Glide.with(this).load(castPictures.get(0)).into(ivCast1);
        Glide.with(this).load(castPictures.get(1)).into(ivCast2);
        Glide.with(this).load(castPictures.get(2)).into(ivCast3);

        tvCast1.setText(castNames.get(0));
        tvCast2.setText(castNames.get(1));
        tvCast3.setText(castNames.get(2));
    }

    @Override
    public void moviesInfoRetrievalFail() {

    }
}
