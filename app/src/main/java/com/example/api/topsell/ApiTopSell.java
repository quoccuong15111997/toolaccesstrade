package com.example.api.topsell;

import com.example.conts.Contstan;
import com.example.model.JsonTopSell;
import com.example.model.TopSellAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiTopSell {

    @GET("v1/top_products")
    Call<JsonTopSell> getTopSell(@Header(value = "Authorization") String token, @Query(value = "date_from") String dateFrom,
                                 @Query(value = "date_to") String dateTo);

//    @GET
//    Call<Repos> getRepos(@Url String url);
}
