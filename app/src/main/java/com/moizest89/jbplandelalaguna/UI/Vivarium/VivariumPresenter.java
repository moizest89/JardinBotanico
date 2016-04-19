package com.moizest89.jbplandelalaguna.UI.Vivarium;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.moizest89.jbplandelalaguna.Data.Api.MySingleton;
import com.moizest89.jbplandelalaguna.Data.models.DummyData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @moizest89 in SV on 4/17/16.
 */
public class VivariumPresenter {

    private VivariumActivity context;
    private final static String TAG = VivariumPresenter.class.getSimpleName();

    public VivariumPresenter(VivariumActivity contect) {
        this.context = contect;
    }



    public void getData(){



        JsonArrayRequest request = new JsonArrayRequest(MySingleton.API_DUMMY_DATA,
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

    private void setData(JSONArray object){

        List<DummyData> mList = new ArrayList<>();
        try {
            for (int dummy = 0; dummy < object.length(); dummy++) {
                DummyData data = new DummyData();
                JSONObject obj = object.getJSONObject(dummy);
                data.setId(obj.getInt("id"));
                data.setName(obj.getString("name"));
                data.setImage("http://www.compo-expert.com/uploads/pics/triebe_gross_12.jpg");

                mList.add(data);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.context.hideLoader();
        this.context.setData(mList);
        this.context.showData();


    }

    private void errorData(VolleyError error){
        Log.e(TAG, "Error data");
        context.hideLoader();
    }
}