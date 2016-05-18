package com.jardinbotanico.jbplandelalaguna.UI.Zone.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.UI.BarcodeView.BarcodeActivity;
import com.jardinbotanico.jbplandelalaguna.UI.Zone.Details.ZoneDetailsActivity;
import com.jardinbotanico.jbplandelalaguna.Util.Util;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ZoneActivity extends AppCompatActivity implements IZoneActivity,
        Spinner.OnItemSelectedListener{

    @Bind(R.id.toolbar_spinner)
    Spinner toolbar_spinner;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    private ZonePresenter mPresenter;
    private final static String TAG = ZoneActivity.class.getSimpleName();

    private final static Integer INTENT_ZONE_DETAILS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        ButterKnife.bind(this);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();

        this.mPresenter = new ZonePresenter(this);
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


    //Coloca un select con las 32 zonas
    @Override
    public void setDataInSpinner(List<String> mData) {

        ZoneSpinnerAdapter zoneSpinnerAdapter = new ZoneSpinnerAdapter(this, mData);
        this.toolbar_spinner.setAdapter(zoneSpinnerAdapter);
        zoneSpinnerAdapter.notifyDataSetChanged();
        this.toolbar_spinner.setOnItemSelectedListener(this);
    }

    //Evento que detecta cuando selecciono una opcion del select de la barra
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0) {
            Log.e(TAG, "onItemSelected");
            Log.e(TAG, "position: " + position);
            Log.e(TAG, "long: " + id);

            Intent intent = new Intent(ZoneActivity.this, ZoneDetailsActivity.class);
            intent.putExtra(Util.INTENT_ZONE_ID, position);
            intent.putExtra(Util.INTENT_DATA_ORIGIN, Util.INTENT_DATA_ORIGIN_PLACE);
            startActivityForResult(intent,INTENT_ZONE_DETAILS);

        }
        parent.setSelection(0);

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.e(TAG, "onNothingSelected");
        Log.e(TAG, "parent.getFirstVisiblePosition(): " + parent.getFirstVisiblePosition());
    }

    //Coloco el logo del QG en la barra
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_qr, menu);
        return true;
    }

    //Detecto la accion del menu al tocarla
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_action_qr_code) {

            Intent intent = new Intent(this, BarcodeActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
