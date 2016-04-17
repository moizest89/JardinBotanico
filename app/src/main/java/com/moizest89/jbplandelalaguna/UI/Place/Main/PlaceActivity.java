package com.moizest89.jbplandelalaguna.UI.Place.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.moizest89.jbplandelalaguna.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PlaceActivity extends AppCompatActivity implements IPlaceActivity,
        Spinner.OnItemSelectedListener{

    @Bind(R.id.toolbar_spinner)
    Spinner toolbar_spinner;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    private PlacePresenter mPresenter;
    private final static String TAG = PlaceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        ButterKnife.bind(this);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();

        this.mPresenter = new PlacePresenter(this);
        this.mPresenter.loadDataForSpinner();

    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_short_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.place_title));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setDataInSpinner(List<String> mData) {

        ZoneSpinnerAdapter zoneSpinnerAdapter = new ZoneSpinnerAdapter(this, mData);
        this.toolbar_spinner.setAdapter(zoneSpinnerAdapter);
        zoneSpinnerAdapter.notifyDataSetChanged();
        this.toolbar_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0) {
            Log.e(TAG, "onItemSelected");
            Log.e(TAG, "position: " + position);
            Log.e(TAG, "long: " + id);
        }

        parent.setSelection(0);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.e(TAG, "onNothingSelected");
        Log.e(TAG, "parent.getFirstVisiblePosition(): " + parent.getFirstVisiblePosition());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_qr, menu);
        return true;
    }
}
