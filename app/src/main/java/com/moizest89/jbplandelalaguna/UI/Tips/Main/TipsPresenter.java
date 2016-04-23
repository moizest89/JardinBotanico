package com.moizest89.jbplandelalaguna.UI.Tips.Main;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.moizest89.jbplandelalaguna.Data.Api.MySingleton;
import com.moizest89.jbplandelalaguna.Data.models.Formules;
import com.moizest89.jbplandelalaguna.Data.models.TipsCategory;
import com.moizest89.jbplandelalaguna.Util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public class TipsPresenter {

    private TipsActivity context;
    private final static String TAG = TipsPresenter.class.getSimpleName();

    public TipsPresenter(TipsActivity context) {
        this.context = context;

    }


    public void getData(){
        JsonArrayRequest request = new JsonArrayRequest(MySingleton.API_URL_TIPS_CATEGOIRES,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        setData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        MySingleton.getInstance(this.context).addToRequestQueue(request);
    }

    private void setData(JSONArray response){

        Log.i(TAG, "response: "+response);

        Gson gson = new Gson();
        List<TipsCategory> tipsCategoryList = new ArrayList<>();
        try {
            for (int cat = 0; cat < response.length(); cat++) {
                    TipsCategory tipsCategory = gson.fromJson(String.valueOf(response.get(cat)), TipsCategory.class);
                    tipsCategoryList.add(tipsCategory);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.context.setData(tipsCategoryList);
        this.context.hideLoading();
        this.context.showData();

    }




}
