package com.example.cojeet.Covidstats.covidstatsapi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.example.cojeet.Covidstats.covidstatsapi.RegionDatum;

public interface ApiInterface {

    @GET("LATEST")
    Call<Covidstats> getData(
            @Query("disableRedirect") Boolean query
    );


}
