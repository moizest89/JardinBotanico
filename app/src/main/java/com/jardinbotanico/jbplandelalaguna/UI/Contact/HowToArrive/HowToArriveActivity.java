package com.jardinbotanico.jbplandelalaguna.UI.Contact.HowToArrive;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.UI.ConfirmationDialog.ConfirmationDialog;
import com.jardinbotanico.jbplandelalaguna.Util.Fonts;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HowToArriveActivity extends AppCompatActivity implements ConfirmationDialog.ConfirmationDialogListener {


    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.TVHowTo)
    TextView TVHowTo;

    private static String TAG = HowToArriveActivity.class.getSimpleName();
    private final Integer CONFIRMATION_MAP = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_arrive);
        ButterKnife.bind(this);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();

        this.TVHowTo.setTypeface(new Fonts().RobotoCondensed_Regular(this));

    }


    private void setToolbar(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_short_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.how_to_arrive_title));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @OnClick(R.id.DIVMap)
    public void goToGoogleMap(View view){
        String mMessage = this.getResources().getString(R.string.how_to_arrive_label_open_in_googlemap);
        DialogFragment dialog = ConfirmationDialog.newInstance(mMessage,CONFIRMATION_MAP);
        dialog.show(getSupportFragmentManager(), TAG);
    }

    private void sendEmail(){


    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Integer origin) {
        if(origin == CONFIRMATION_MAP){
            Uri gmmIntentUri = Uri.parse("geo:13.6702314,-89.2492048?q=Jardin Botanico 'Plan de la laguna'");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog, Integer origin) {

    }
}
