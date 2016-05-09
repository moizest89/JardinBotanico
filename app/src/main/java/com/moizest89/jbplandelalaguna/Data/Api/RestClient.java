package com.moizest89.jbplandelalaguna.Data.Api;


import com.google.gson.JsonArray;
import com.moizest89.jbplandelalaguna.Data.models.Categories;
import com.moizest89.jbplandelalaguna.Data.models.Families;
import com.moizest89.jbplandelalaguna.Data.models.Tips;
import com.moizest89.jbplandelalaguna.Data.models.Zone;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.Url;


/**
 * Created by @moizest89 in SV on 4/29/16.
 */
public class RestClient {

    private static ApiInterface apiInterface;
    public static String baseUrl = "https://jardin-botanico.herokuapp.com" ;

    public final static String URL_ZONE_FAMILIES = baseUrl + "/zone_families?position=";

    public static ApiInterface getClient(){
        if(apiInterface == null){

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiInterface = client.create(ApiInterface.class);

        }

        return apiInterface;
    }


    public interface ApiInterface{

        @GET("/categories")
        Call<Categories> getCategoriesTips();

        @GET("/categories/{id}/tips")
        Call<Tips> getTipsForCategory(@Path("id") Integer id);

        @GET
        Call<JSONObject> getZoneByPosition(@Url String url);

        @GET
        Call<Families> getFamilyDetails(@Url String url);

    }

}
