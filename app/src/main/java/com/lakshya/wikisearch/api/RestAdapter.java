package com.lakshya.wikisearch.api;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAdapter {

    private static final String BASE_URL = "https://en.wikipedia.org//w/";

    private static ApiClient mApiClient;

    public RestAdapter(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        OkHttpClient okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiClient = retrofit.create(ApiClient.class);
    }

    public ApiClient getApiClient(){
        return  mApiClient;
    }

}
