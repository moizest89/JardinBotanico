package com.jardinbotanico.jbplandelalaguna.UI.Contact.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.UI.ConfirmationDialog.ConfirmationDialog;
import com.jardinbotanico.jbplandelalaguna.UI.Contact.HowToArrive.HowToArriveActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jardinbotanico.jbplandelalaguna.Util.Util.changeActivity;

public class ContactActivity extends AppCompatActivity implements ConfirmationDialog.ConfirmationDialogListener{


    @Bind(R.id.toolbar)
    Toolbar toolbar;


    private final static String TAG = ContactActivity.class.getSimpleName();
    private final static Integer CONFIRMATION_EMAIL = 100;


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

    @OnClick(R.id.BTSendEmail)
    public void action_send_email(View view){

        String mMessage = this.getResources().getString(R.string.contact_send_email_request);
        DialogFragment dialog = ConfirmationDialog.newInstance(mMessage,CONFIRMATION_EMAIL);
        dialog.show(getSupportFragmentManager(), TAG);


    }


    @Override
    public void onDialogPositiveClick(DialogFragment dialog, Integer origin) {
        if(origin == CONFIRMATION_EMAIL){
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, "jardinbotanico@jardinbotanico.org.sv");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Hola jardin Botanico");
            startActivity(Intent.createChooser(intent, "Send Email"));
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog, Integer origin) {

    }
}
