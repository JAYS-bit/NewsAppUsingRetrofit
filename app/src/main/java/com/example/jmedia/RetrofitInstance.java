package com.example.jmedia;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {


    public static String BASE_URL="https://newsapi.org/v2/";

    public static CallNewsApi callNewsApi;

    public static RetrofitInstance instance;

    public RetrofitInstance(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        callNewsApi= retrofit.create(CallNewsApi.class);
    }

    public static RetrofitInstance getInstance(){

        if(instance==null){
            instance= new RetrofitInstance();
        }
        return instance;
    }

}
