package com.harry.movieproapp.service;

import com.harry.movieproapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface MovieDataService {

    // Base Url
    // https://api.themoviedb.org/3/
    //
    // End Point Url
    // movie/popular?api_key=4ab08249b09a5d4f6630b7adb47ad01b
    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);


}
