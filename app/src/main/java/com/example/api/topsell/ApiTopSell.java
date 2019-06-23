package com.example.api.topsell;

import com.example.conts.Contstan;
import com.example.model.TopSellAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiTopSell {
    @Headers({
            "Authorization: Token "+ Contstan.ACCESS_KEY,
            "Content-Type: application/json"
    })
    @GET("/top_products")
    Call<ArrayList<TopSellAPI>> getTopSell(@Query("date_from") String dateFrom,
                                      @Query("date_to") String dateTo);
//    @GET
//    Call<Repos> getRepos(@Url String url);
}
