package com.moizest89.jbplandelalaguna.UI.BarcodeView;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.UI.Family.Details.FamilyDescriptionActivity;
import com.moizest89.jbplandelalaguna.UI.Zone.Details.ZoneDetailsActivity;
import com.moizest89.jbplandelalaguna.Util.Util;


import butterknife.Bind;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class BarcodeActivity extends AppCompatActivity{


    private final static String TAG =BarcodeActivity.class.getSimpleName();

    @Bind(R.id.BarCode)
    ZBarScannerView zBarScannerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        ButterKnife.bind(this);

        setToolbar();

        Handler();

    }


    private void Handler(){
        this.zBarScannerView.setResultHandler(new ZBarScannerView.ResultHandler() {
            @Override
            public void handleResult(Result rawResult) {

//                Log.e(TAG, "rawResult: " + rawResult);
//                Log.e(TAG, "rawResult.getContents(): " + rawResult.getContents().toString());
//                Log.e(TAG, "rawResult.getContents()" + rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

                redirectSpecificDetails(rawResult.getContents().toString());
            }
        });
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_short_name));
        getSupportActionBar().setSubtitle(getResources().getString(R.string.barcode_title));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {


        if(resultCode == RESULT_OK) {}


    }



    private void redirectSpecificDetails(String mContent){
        //family_info

        Log.e(TAG, "redirectSpecificDetails: "+mContent);

        boolean family = mContent.matches("/family_info(?i).*");
        boolean zone = mContent.matches("/zone_families(?i).*");

        if(family){
            Log.e(TAG, "family");
            Intent intent = new Intent(this, FamilyDescriptionActivity.class);
            intent.putExtra(Util.INTENT_DATA_SEND, mContent);
            startActivity(intent);
        }else{
            if(zone){
                Log.e(TAG, "zone");
                Intent intent = new Intent(this, ZoneDetailsActivity.class);
                intent.putExtra(Util.INTENT_DATA_SEND, mContent);
                intent.putExtra(Util.INTENT_DATA_ORIGIN, Util.INTENT_DATA_ORIGIN_QR_CODE);
                startActivity(intent);
            }else{
                Toast.makeText(BarcodeActivity.this, "El codigo QR no pertenece al Jardin Botanico", Toast.LENGTH_SHORT).show();
            }
        }
        finish();
    }



    @Override
    protected void onResume() {
        super.onResume();

        if(this.zBarScannerView !=null){
            Handler();
            this.zBarScannerView.startCamera();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(this.zBarScannerView !=null){
            this.zBarScannerView.stopCamera();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.zBarScannerView !=null){
            this.zBarScannerView.stopCamera();
        }
    }
}
