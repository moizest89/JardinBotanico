package com.jardinbotanico.jbplandelalaguna.UI.Tips.Main;

import android.util.Log;

import com.jardinbotanico.jbplandelalaguna.Data.Api.RestClient;
import com.jardinbotanico.jbplandelalaguna.Data.models.Categories;

import retrofit.Call;
import retrofit.Callback;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public class TipsPresenter {

    private TipsActivity context;
    private final static String TAG = TipsPresenter.class.getSimpleName();
    private final RestClient.ApiInterface service;


    public TipsPresenter(TipsActivity context) {
        this.context = context;
        this.service = RestClient.getClient();
    }


    public void getData(){

        Call<Categories> call = service.getCategoriesTips();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(retrofit.Response<Categories> response) {
                Categories categories = response.body();

                if (categories != null){
                    setData(categories);
                }

            }
            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, t.getMessage());
                context.hideLoading();
                context.setMessageDataError();
            }
        });

    }

    private void setData(Categories categories){

        Log.i(TAG, "categories: "+categories.getCategories().size());

        if(categories.getCategories().size() > 0){
            this.context.setData(categories);
        }else{
            context.setMessageDataEmpty();
        }

        this.context.hideLoading();
        this.context.showData();

    }




}
