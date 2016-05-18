package com.jardinbotanico.jbplandelalaguna.UI.Zone.Details;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jardinbotanico.jbplandelalaguna.Data.Api.MySingleton;
import com.jardinbotanico.jbplandelalaguna.Data.Api.RestClient;
import com.jardinbotanico.jbplandelalaguna.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 5/5/16.
 */
public class ZoneDetailsPresenter {

    private ZoneDetailsActivity context;
    private final RestClient.ApiInterface service;
    private final String TAG = ZoneDetailsPresenter.class.getSimpleName();

    public ZoneDetailsPresenter(ZoneDetailsActivity context) {
        this.context = context;
        this.service = RestClient.getClient();
    }


    public void getDataByUrl(String mString){

        String mUrl = RestClient.baseUrl + mString;
        getGata(mUrl);

    }

    public void getDataByPosition(Integer position){



        String mUrl = RestClient.URL_ZONE_FAMILIES + position.toString();

        getGata(mUrl);

    }

    private void getGata(String mUrl){

        Log.e(TAG, "URL " + mUrl);

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,mUrl,null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        setData(response);

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        String mMessage =
                                context.getResources().getString(R.string.error_connection_message);
                        context.serErrorMessage(mMessage);

                    }
                });
        MySingleton.getInstance(this.context).addToRequestQueue(req);
    }

    private void setData(JSONObject object){

        List<String> mUrlsImages = new ArrayList<>();

        try {

            JSONArray data = object.getJSONArray("zones");
            JSONObject zone = data.getJSONObject(0);

            mUrlsImages.add(zone.getString("banner"));

            JSONArray families = zone.getJSONArray("families");

            for (int fam = 0; fam < families.length(); fam++) {

                JSONObject item = families.getJSONObject(fam);
                JSONArray family_images = item.getJSONArray("family_images");

                for (int imgs = 0; imgs < family_images.length(); imgs++) {
                    JSONObject img = family_images.getJSONObject(imgs);
                    mUrlsImages.add(img.getString("url"));
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e(TAG,"mUrlsImages: "+mUrlsImages);
        Log.e(TAG,"mUrlsImages size: "+mUrlsImages.size());


        this.context.setData(mUrlsImages);
        this.context.hideLoader();
        this.context.showData();
    }

}
