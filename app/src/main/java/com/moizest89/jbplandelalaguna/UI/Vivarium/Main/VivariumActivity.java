package com.moizest89.jbplandelalaguna.UI.Vivarium.Main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.moizest89.jbplandelalaguna.Data.models.DummyData;
import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.UI.BarcodeView.BarcodeActivity;
import com.moizest89.jbplandelalaguna.UI.Vivarium.Details.VivariumDetailsActivity;
import com.moizest89.jbplandelalaguna.Util.DummyAdapter;
import com.moizest89.jbplandelalaguna.Util.MarginDecoration;
import com.moizest89.jbplandelalaguna.Util.OnItemClickListener;
import com.moizest89.jbplandelalaguna.Util.Util;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.moizest89.jbplandelalaguna.Util.Util.changeActivity;

public class VivariumActivity extends AppCompatActivity implements IVivariumActivity{


    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.AVLoader)
    AVLoadingIndicatorView AVLoader;
    @Bind(R.id.SWRefresh) SwipeRefreshLayout SWRefresh;
    @Bind(R.id.RVList) RecyclerView RVList;


    private VivariumPresenter mPresenter;
    private final static String TAG =  VivariumActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivarium);
        ButterKnife.bind(this);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();
        RVList.addItemDecoration(new MarginDecoration(0, this));
        RVList.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        RVList.setLayoutManager(manager);

        this.mPresenter = new VivariumPresenter(this);
        mPresenter.getData();


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


    @Override
    public void showData() {

        this.SWRefresh.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);

    }

    @Override
    public void hideData() {

        this.SWRefresh.animate().alpha(0).setDuration(Util.ANIMATION_DURATION);

    }

    @Override
    public void showLoader() {

        this.AVLoader.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);

    }

    @Override
    public void hideLoader() {
        Log.e(TAG, "hideLoader");
        this.AVLoader.animate().alpha(0).setDuration(Util.ANIMATION_DURATION);

    }

    @Override
    public void setData(List<DummyData> data) {
        Log.e(TAG, "setData");
        Log.e(TAG, "data: " + data.size());
        DummyAdapter adapter = new DummyAdapter(data, this);
        this.RVList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, Integer position) {
//                DummyData dummyDataItem = data.get(position);
                Log.e(TAG,"position: "+position);
                changeActivity(VivariumActivity.this, VivariumDetailsActivity.class, null, false);
            }
        };

        adapter.setOnItemClickListener(onItemClickListener);

    }
}
