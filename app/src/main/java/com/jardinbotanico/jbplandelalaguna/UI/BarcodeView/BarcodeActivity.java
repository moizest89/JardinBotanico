package com.jardinbotanico.jbplandelalaguna.UI.BarcodeView;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.jardinbotanico.jbplandelalaguna.R;
import com.jardinbotanico.jbplandelalaguna.UI.Family.Details.FamilyDescriptionActivity;
import com.jardinbotanico.jbplandelalaguna.UI.Zone.Details.ZoneDetailsActivity;
import com.jardinbotanico.jbplandelalaguna.Util.Util;


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


        //Llamo al metodo que espera los resutados del lector de QR
        Handler();

    }


    //Metodo para caputara la respuesta del codigo QR
    private void Handler(){
        this.zBarScannerView.setResultHandler(new ZBarScannerView.ResultHandler() {
            @Override
            public void handleResult(Result rawResult) {
                redirectSpecificDetails(rawResult.getContents().toString());
            }
        });
    }


    //Instancio la barra principal
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


    //methodo para evaluar los datos obtenidos por el codigo QR
    private void redirectSpecificDetails(String mContent){
        //family_info

        //Verifico el formato de la info del QR
        boolean family = mContent.matches("/family_info(?i).*");
        boolean zone = mContent.matches("/zone_families(?i).*");


        //Evaluacion para el redireccionamiento de la informacion
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
                //Si el formato no es correcto muestro un mensage de error
                Toast.makeText(BarcodeActivity.this, "El codigo QR no pertenece al Jardin Botanico", Toast.LENGTH_SHORT).show();
            }
        }
        //Finalizo la vista
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
