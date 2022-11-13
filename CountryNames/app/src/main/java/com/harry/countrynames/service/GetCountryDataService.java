package com.harry.countrynames.service;

import com.harry.countrynames.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {
    //Retrofit Interface

    @GET("countries")
    Call<Result> getResult();


}
