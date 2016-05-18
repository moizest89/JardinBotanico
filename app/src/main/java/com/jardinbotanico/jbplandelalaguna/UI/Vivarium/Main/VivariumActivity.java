package com.jardinbotanico.jbplandelalaguna.UI.Vivarium.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.jardinbotanico.jbplandelalaguna.R;

import com.jardinbotanico.jbplandelalaguna.Util.ImageViewWithRatio;

import com.wang.avi.AVLoadingIndicatorView;



import butterknife.Bind;
import butterknife.ButterKnife;


public class VivariumActivity extends AppCompatActivity{


    @Nullable @Bind(R.id.toolbar) Toolbar toolbar;
    @Nullable @Bind(R.id.AVLoader) AVLoadingIndicatorView AVLoader;
    @Nullable @Bind(R.id.SWRefresh) SwipeRefreshLayout SWRefresh;
    @Nullable @Bind(R.id.RVList) RecyclerView RVList;

    @Nullable @Bind(R.id.IVImage_1)
    ImageViewWithRatio IVImage_1;
    @Nullable @Bind(R.id.IVImage_2)
    ImageViewWithRatio IVImage_2;


    private final static String TAG =  VivariumActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivarium);
        ButterKnife.bind(this);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();


        this.IVImage_1.setX(1.0f);
        this.IVImage_1.setY(1.0f);

        this.IVImage_2.setX(1.0f);
        this.IVImage_2.setY(1.0f);


    }



    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_short_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.vivarium_title));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
