package com.example.covidretrofitapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit=null;

    public static ApiInterface getApiInterface(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(ApiInterface.Base_Url).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit.create(ApiInterface.class);
    }
}
