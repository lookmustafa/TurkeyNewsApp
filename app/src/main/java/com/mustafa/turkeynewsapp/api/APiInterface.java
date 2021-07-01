package com.mustafa.turkeynewsapp.api;

import com.mustafa.turkeynewsapp.newsApiJSON.Root;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APiInterface {

    // https://newsapi.org/v2/top-headlines?country=tr&apiKey=bb65ff85ca75461795102a37d1811405


    @GET("top-headlines?country=tr&apiKey=bb65ff85ca75461795102a37d1811405")
    Call<Root> getData();
}
