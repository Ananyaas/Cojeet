package com.example.cojeet.Covidnews.covidnewsapi;

import com.example.cojeet.Covidnews.models.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<Headlines> getSpecificData(
            @Query("q") String query,
            @Query("sortBy") String sort,
            @Query("apiKey") String apiKey
    );



}