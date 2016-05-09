package com.moizest89.jbplandelalaguna.UI.BarcodeView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;


import com.moizest89.jbplandelalaguna.R;
import com.moizest89.jbplandelalaguna.UI.Family.Details.FamilyDescriptionActivity;
import com.moizest89.jbplandelalaguna.UI.Zone.Details.ZoneDetailsActivity;
import com.moizest89.jbplandelalaguna.Util.Util;

import me.sudar.zxingorient.Barcode;
import me.sudar.zxingorient.ZxingOrient;
import me.sudar.zxingorient.ZxingOrientResult;

public class BarcodeActivity extends AppCompatActivity{


    private final static String TAG =BarcodeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        scanNow();
    }


    public void scanNow(){
//        IntentIntegrator integrator = new IntentIntegrator(this);
//        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
//        integrator.setPrompt("Escanea el codigo QR");
//        integrator.setCameraId(0);  // Use a specific camera of the device
//        integrator.setBeepEnabled(true);
//        integrator.setBarcodeImageEnabled(false);
//        integrator.setOrientationLocked(false);
//
//        integrator.initiateScan();

        new ZxingOrient(BarcodeActivity.this)
                .showInfoBox(false) // Doesn't display the info box
                .setBeep(false)  // Doesn't play beep sound
                .setVibration(true)  // Enables the vibration
                .setIcon(R.drawable.ic_launcher)
                .setToolbarColor("#AA00796B")
                .setInfoBoxColor("#AA00796B")
                .setInfo("Escanea el codigo QR")
                .initiateScan(Barcode.QR_CODE);
    }

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

        if(resultCode == RESULT_OK) {

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
            ZxingOrientResult scanResult = ZxingOrient.parseActivityResult(requestCode, resultCode, intent);

            if (scanResult != null) {
                String scanContent = scanResult.getContents();
                String scanFormat = scanResult.getFormatName();

                Log.e(TAG, "FORMAT: " + scanFormat);
                Log.e(TAG, "CONTENT: " + scanContent);

                redirectSpecificDetails(scanContent);
            }
        }else{
            finish();
        }

    }



    private void redirectSpecificDetails(String mContent){
        //family_info

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

}
