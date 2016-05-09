package com.moizest89.jbplandelalaguna.UI.Family.Details;

import android.util.Log;

import com.google.gson.JsonArray;
import com.moizest89.jbplandelalaguna.Data.Api.RestClient;
import com.moizest89.jbplandelalaguna.Data.models.Families;
import com.moizest89.jbplandelalaguna.Data.models.Family;

import org.json.JSONObject;

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


    public void getData(String mUrl){

        String url = RestClient.baseUrl + mUrl;

        Log.e(TAG, "url: "+url);

        Call<Families> call = this.service.getFamilyDetails(url);
        call.enqueue(new Callback<Families>() {
            @Override
            public void onResponse(Response<Families> response) {
                Families families  = response.body();
                Log.e(TAG,"families: "+families);
                setData(families);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }


    private void setData(Families families){
        List<Family> familyList = families.getFamilies();
        this.context.setData(familyList.get(0));
    }
}
