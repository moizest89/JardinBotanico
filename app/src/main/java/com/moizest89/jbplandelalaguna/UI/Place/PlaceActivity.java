package com.moizest89.jbplandelalaguna.UI.Place;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import com.moizest89.jbplandelalaguna.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlaceActivity extends AppCompatActivity implements IPlaceActivity{

    @Bind(R.id.toolbar_spinner)
    Spinner toolbar_spinner;


    private PlacePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.mPresenter = new PlacePresenter(this);
        this.mPresenter.loadDataForSpinner();

    }

    @Override
    public void setDataInSpinner(List<String> mData) {

        ZoneSpinnerAdapter zoneSpinnerAdapter = new ZoneSpinnerAdapter(this, mData);
        this.toolbar_spinner.setAdapter(zoneSpinnerAdapter);
        zoneSpinnerAdapter.notifyDataSetChanged();

    }

}
