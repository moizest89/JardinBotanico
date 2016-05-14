package com.moizest89.jbplandelalaguna.UI.BarcodeView;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        ButterKnife.bind(this);



//        scanNow();

        Handler();

    }


    private void Handler(){
        this.zBarScannerView.setResultHandler(new ZBarScannerView.ResultHandler() {
            @Override
            public void handleResult(Result rawResult) {

                Log.e(TAG, "rawResult: " + rawResult);
                Log.e(TAG,"rawResult.getContents(): "+ rawResult.getContents().toString());
                Log.e(TAG, "rawResult.getContents()"+ rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

                redirectSpecificDetails(rawResult.getContents().toString());
                // If you would like to resume scanning, call this method below:
//                zBarScannerView.resumeCameraPreview(this);
            }
        });
    }

//    public void scanNow(){
//
//        new ZxingOrient(BarcodeActivity.this)
//                .showInfoBox(false) // Doesn't display the info box
//                .setBeep(false)  // Doesn't play beep sound
//                .setVibration(true)  // Enables the vibration
//                .setIcon(R.drawable.ic_launcher)
//                .setToolbarColor("#AA00796B")
//                .setInfoBoxColor("#AA00796B")
//                .setInfo("Escanea el codigo QR")
//                .initiateScan(Barcode.QR_CODE);
//    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//
//        Log.e(TAG, "requestCode: "+requestCode);
//        Log.e(TAG, "resultCode: "+resultCode);
//
//        Log.e(TAG, "RESULT_CANCELED: "+RESULT_CANCELED);
//        Log.e(TAG, "RESULT_OK: "+RESULT_OK);
//
//        Log.e(TAG, "scanningResult: "+scanningResult);

        if(resultCode == RESULT_OK) {}

//            if (scanningResult != null) {
//                String scanContent = scanningResult.getContents();
//                String scanFormat = scanningResult.getFormatName();
//
//                Log.e(TAG, "FORMAT: " + scanFormat);
//                Log.e(TAG, "CONTENT: " + scanContent);
//
//
//            } else {
//                Toast toast = Toast.makeText(getApplicationContext(), "No scan data received!", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//            ZxingOrientResult scanResult = ZxingOrient.parseActivityResult(requestCode, resultCode, intent);
//
//            if (scanResult != null) {
//                String scanContent = scanResult.getContents();
//                String scanFormat = scanResult.getFormatName();
//
//                Log.e(TAG, "FORMAT: " + scanFormat);
//                Log.e(TAG, "CONTENT: " + scanContent);
//
//                redirectSpecificDetails(scanContent);
//            }
//        }else{
//            finish();
//        }

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
}
