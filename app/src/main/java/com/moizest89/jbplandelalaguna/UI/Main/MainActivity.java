package com.moizest89.jbplandelalaguna.UI.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.UI.History.HistoryActivity;
import com.moizest89.jbplandelalaguna.UI.Place.PlaceActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.moizest89.jbplandelalaguna.Util.Util.changeActivity;

public class MainActivity extends AppCompatActivity {


    private final static Integer INTENT_HISTORY = 1000;


    @Bind(R.id.IVActionHistoy)
    ImageView IVActionHistoyr;

    @Bind(R.id.IVActionPlace)
    ImageView IVActionPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);


    }


    @OnClick(R.id.IVActionHistoy)
    public void actionHistory(View view){
        changeActivity(this, HistoryActivity.class, null, false);
    }

    @OnClick(R.id.IVActionPlace)
    public void actionToPlace(View view){
        changeActivity(this, PlaceActivity.class, null, false);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
