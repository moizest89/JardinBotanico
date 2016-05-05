package com.moizest89.jbplandelalaguna.UI.Tips.Main;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.moizest89.jbplandelalaguna.Data.Api.MySingleton;
import com.moizest89.jbplandelalaguna.Data.Api.RestClient;
import com.moizest89.jbplandelalaguna.Data.models.Categories;
import com.moizest89.jbplandelalaguna.Data.models.Formules;
import com.moizest89.jbplandelalaguna.Data.models.TipsCategory;
import com.moizest89.jbplandelalaguna.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
            }
        });

    }

    private void setData(Categories categories){

        Log.i(TAG, "categories: "+categories.getCategories().size());

        if(categories.getCategories().size() > 0){
            this.context.setData(categories);
        }else{

        }
//        Gson gson = new Gson();
//        List<TipsCategory> tipsCategoryList = new ArrayList<>();
//        try {
//            for (int cat = 0; cat < response.length(); cat++) {
//                    TipsCategory tipsCategory = gson.fromJson(String.valueOf(response.get(cat)), TipsCategory.class);
//                    tipsCategoryList.add(tipsCategory);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.e(TAG, e.getMessage());
//        }

//        this.context.setData(tipsCategoryList);
        this.context.hideLoading();
        this.context.showData();

    }




}
