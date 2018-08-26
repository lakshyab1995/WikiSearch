package com.lakshya.wikisearch.api;

import com.lakshya.wikisearch.model.WikiResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClient {

    @GET("api.php?\n" +
            "action=query&\n" +
            "format=json&\n" +
            "prop=pageimages|pageterms|info&\n" +
            "list=prefixsearch&\n" +
            "generator=prefixsearch&\n" +
            "formatversion=2&\n" +
            "inprop=url&\n" +
            "pilimit=15")
    Call<WikiResponseModel> fetchWikiPages(@Query("pssearch") String pSearch, @Query("gpssearch") String gSearch);
}
