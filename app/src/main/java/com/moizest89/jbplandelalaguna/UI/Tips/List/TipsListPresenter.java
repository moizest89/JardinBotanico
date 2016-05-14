package com.moizest89.jbplandelalaguna.UI.Tips.List;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.moizest89.jbplandelalaguna.Data.Api.MySingleton;
import com.moizest89.jbplandelalaguna.Data.Api.RestClient;
import com.moizest89.jbplandelalaguna.Data.models.Tip;
import com.moizest89.jbplandelalaguna.Data.models.Tips;

import org.json.JSONArray;

import retrofit.Call;
import retrofit.Callback;

/**
 * Created by @moizest89 in SV on 4/23/16.
 */
public class TipsListPresenter {

    private TipsListActivity context;
    private final RestClient.ApiInterface service;
    private final String TAG = TipsListPresenter.class.getSimpleName();

    public TipsListPresenter(TipsListActivity context) {
        this.context = context;
        this.service = RestClient.getClient();

    }


    public void getData(Integer id){
        //categories/3/tips

//        String mUrlConnection = MySingleton.API_URL_TIPS_CATEGOIRES+"/"+id.toString()+"/tips";

        Call<Tips> call = this.service.getTipsForCategory(id);
        call.enqueue(new Callback<Tips>() {
            @Override
            public void onResponse(retrofit.Response<Tips> response) {

                Log.e(TAG, "onResponse()");
                Log.e(TAG, "onResponse() response.code() "+response.code());

                switch (response.code()){
                    case 500:
                        context.hideLoading();
                        context.setMessageDataError();
                        break;
                    case 200:
                        Tips tips = response.body();
                        if(tips !=null){
                            setData(tips);
                        }
                        break;
                    default:

                        break;
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

    private void setData(Tips tips){

        if(tips.getTips().size() > 0){
            this.context.setData(tips.getTips());
        }else{
            this.context.setMessageDataEmpty();
        }


        //set data

        this.context.hideLoading();
        this.context.showData();
    }
}
