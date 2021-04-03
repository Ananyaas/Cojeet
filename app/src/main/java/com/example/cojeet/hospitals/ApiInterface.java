package com.example.cojeet.hospitals;

import com.example.cojeet.hospitals.Apidata.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface ApiInterface {

    @GET("?where")
    Call<Example> gethospitalData(
            @Query("lat") double latitude,
            @Query("lon") double longitude
    );


}
