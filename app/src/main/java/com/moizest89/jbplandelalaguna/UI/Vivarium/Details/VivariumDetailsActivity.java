package com.moizest89.jbplandelalaguna.UI.Vivarium.Details;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.Util.ImageViewWithRatio;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VivariumDetailsActivity extends AppCompatActivity {



    @Bind(R.id.ImageDetails)
    ImageView ImageDetails;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vivarium_details);

        ButterKnife.bind(this);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setToolbar();


        this.collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Nombre de planta");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
