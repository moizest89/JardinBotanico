package com.jardinbotanico.jbplandelalaguna.UI.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.UI.BarcodeView.BarcodeActivity;
import com.jardinbotanico.jbplandelalaguna.UI.Contact.Main.ContactActivity;
import com.jardinbotanico.jbplandelalaguna.UI.History.HistoryActivity;
import com.jardinbotanico.jbplandelalaguna.UI.Zone.Main.ZoneActivity;
import com.jardinbotanico.jbplandelalaguna.UI.Tips.Main.TipsActivity;
import com.jardinbotanico.jbplandelalaguna.UI.Vivarium.Main.VivariumActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jardinbotanico.jbplandelalaguna.Util.Util.changeActivity;

public class MainActivity extends AppCompatActivity {


    private final static Integer INTENT_HISTORY = 1000;


    @Nullable @Bind(R.id.IVActionHistoy)
    ImageView IVActionHistoyr;

    @Nullable @Bind(R.id.IVActionPlace)
    ImageView IVActionPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();

        ButterKnife.bind(this);

    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_short_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.app_short_sub_name));

    }


    @Nullable @OnClick(R.id.IVActionHistoy)
    public void actionHistory(View view){
        changeActivity(this, HistoryActivity.class, null, false);
    }

    @Nullable @OnClick(R.id.IVActionPlace)
    public void actionToPlace(View view){
        changeActivity(this, ZoneActivity.class, null, false);
    }


    @Nullable @OnClick(R.id.IVActionContact)
    public void actionToContact(View view){
        changeActivity(this, ContactActivity.class, null, false);
    }

    @Nullable @OnClick(R.id.IVVivarium)
    public void actionToVivarium(View view){
        changeActivity(this, VivariumActivity.class, null, false);
    }

    @Nullable @OnClick(R.id.IVQrCode)
    public void actionQrCode(View view){
        changeActivity(this, BarcodeActivity.class, null, false);
    }

    @Nullable @OnClick(R.id.IVTips)
    public void actionTips(View view){
        changeActivity(this, TipsActivity.class,null, false);
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
