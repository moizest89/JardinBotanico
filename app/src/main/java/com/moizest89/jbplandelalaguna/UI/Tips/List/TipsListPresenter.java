package com.moizest89.jbplandelalaguna.UI.Tips.List;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.moizest89.jbplandelalaguna.Data.Api.MySingleton;

import org.json.JSONArray;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public class TipsListPresenter {

    private TipsListActivity context;

    public TipsListPresenter(TipsListActivity context) {
        this.context = context;

    }


    public void getData(Integer id){
        //categories/3/tips

        String mUrlConnection = MySingleton.API_URL_TIPS_CATEGOIRES+"/"+id.toString()+"/tips";

        JsonArrayRequest request = new JsonArrayRequest(mUrlConnection,
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



        //set data
        this.context.hideLoading();
        this.context.showData();
    }
}
