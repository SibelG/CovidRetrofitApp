package com.example.covidretrofitapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {
    String Base_Url="https://disease.sh/v3/covid-19/";
    @GET("countries")
    Call<List<CovidClass>> getCountryData();

}