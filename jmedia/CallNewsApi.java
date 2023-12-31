package com.example.jmedia;

import com.example.jmedia.Models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  CallNewsApi{

    @GET("top-headlines")
    Call<NewsApiResponse> callHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("q") String query,
            //Passing API Key here:
            @Query("apiKey") String api_key

    );
}