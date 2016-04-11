package com.moizest89.jbplandelalaguna.UI.History;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.Fonts;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {


    @Bind(R.id.TVAbout_1)
    TextView TVAbout_1;
    @Bind(R.id.TVAbout_2)
    TextView TVAbout_2;
    @Bind(R.id.TVAbout_3)
    TextView TVAbout_3;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();

        ButterKnife.bind(this);

        Fonts font = new Fonts();

        this.TVAbout_1.setTypeface(font.RobotoCondensed_Regular(this));
        this.TVAbout_2.setTypeface(font.RobotoCondensed_Regular(this));
        this.TVAbout_3.setTypeface(font.RobotoCondensed_Regular(this));

    }


    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_short_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.history_title));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
