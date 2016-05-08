package com.moizest89.jbplandelalaguna.UI.Zone.Details;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.Util;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZoneDetailsActivity extends AppCompatActivity implements IZoneView{


    @Bind(R.id.AVLoader)
    AVLoadingIndicatorView AVLoader;
    @Bind(R.id.VPimages)
    ViewPager VPImages;

    private int mPosition = 0;

    private ZoneDetailsPresenter mPresenter;
    private final static String TAG = ZoneDetailsActivity.class.getSimpleName();
    private ZoneDetailsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_details);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        if(intent != null){
            this.mPosition = intent.getIntExtra(Util.INTENT_ZONE_ID, 0);
        }


        this.mPresenter = new ZoneDetailsPresenter(this);
        this.mPresenter.getDataByPosition(this.mPosition);

    }

    @Override
    public void showLoader() {
        this.AVLoader.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
    }

    @Override
    public void hideLoader() {
        this.AVLoader.animate().alpha(0).setDuration(Util.ANIMATION_DURATION);
    }

    @Override
    public void showData() {
        this.VPImages.animate().alpha(1).setDuration(Util.ANIMATION_DURATION);
        Log.e(TAG, "showData()");
    }

    @Override
    public void hideData() {
        this.VPImages.animate().alpha(0).setDuration(Util.ANIMATION_DURATION);
    }

    @Override
    public void setData(List<String> data) {
        ZoneDetailsAdapter adapter = new ZoneDetailsAdapter(this, data);
        this.VPImages.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
