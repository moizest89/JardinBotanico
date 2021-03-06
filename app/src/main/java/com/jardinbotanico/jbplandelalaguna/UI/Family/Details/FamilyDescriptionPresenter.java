package com.jardinbotanico.jbplandelalaguna.UI.Family.Details;

import android.util.Log;

import com.jardinbotanico.jbplandelalaguna.Data.Api.RestClient;
import com.jardinbotanico.jbplandelalaguna.Data.models.Families;
import com.jardinbotanico.jbplandelalaguna.Data.models.Family;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by @moizest89 in SV on 5/8/16.
 */
public class FamilyDescriptionPresenter {

    private FamilyDescriptionActivity context;
    private final RestClient.ApiInterface service;
    private final String TAG = FamilyDescriptionPresenter.class.getSimpleName();

    public FamilyDescriptionPresenter(FamilyDescriptionActivity context) {
        this.context = context;
        this.service = RestClient.getClient();
    }



    //Obtengo la informacion especificado en una URL
    public void getData(String mUrl){

        this.context.showLoader();

        String url = RestClient.baseUrl + mUrl;

        Log.e(TAG, "url: "+url);

        Call<Families> call = this.service.getFamilyDetails(url);
        call.enqueue(new Callback<Families>() {
            @Override
            public void onResponse(Response<Families> response) {

                //Si la peticion fue exitosa obtengo la informacion
                Families families  = response.body();
                Log.e(TAG,"families: "+families);
                setData(families);
            }

            @Override
            public void onFailure(Throwable t) {

                //Si la peticion me dio error muestro un error
                Log.e(TAG, t.getMessage());
                context.hideLoader();
                context.setMessage("Ocurrio un error con la peticion, intentalo nuevamente");
            }
        });

    }

    //Pongo la informacion obtenida del request
    private void setData(Families families){
        List<Family> familyList = families.getFamilies();

        if(familyList.size() > 0) {
            this.context.setData(familyList.get(0));
            this.context.hideLoader();
        }else{
            this.context.setMessage("Actualmente, este contenido no esta disponible");
        }

    }
}
