package com.moizest89.jbplandelalaguna.UI.Contact.Main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.UI.Contact.HowToArrive.HowToArriveActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.moizest89.jbplandelalaguna.Util.Util.changeActivity;

public class ContactActivity extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();


    }

    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_short_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.contact_title));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @OnClick(R.id.BThowToArrive)
    public void action_how_to_arrive(View view){
        changeActivity(this, HowToArriveActivity.class, null, false);
    }

}
