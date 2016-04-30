package com.moizest89.jbplandelalaguna.Data.Api;


import com.moizest89.jbplandelalaguna.Data.models.Categories;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;


import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;


/**
 * Created by @moizest89 in SV on 4/29/16.
 */
public class RestClient {

    private static ApiInterface apiInterface;
    private static String baseUrl = "https://jardin-botanico.herokuapp.com" ;

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

    }

}
