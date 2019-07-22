package com.example.api.topsell;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.conts.Contstan;
import com.example.model.JsonTopSell;
import com.example.model.TopSellAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopSellService {
    private static TopSellService apiService;

    private Retrofit retrofit;

    private TopSellService(String baseUrl) {
        initClient(baseUrl);
    }

    private void initClient(@NonNull String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            return;
        }
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        Log.e("URL ",baseUrl);
    }

    public static void init(@NonNull String baseUrl) {
        if (apiService == null) {
            apiService = new TopSellService(baseUrl);
        }
    }

    public static TopSellService getInstance() {
        return apiService;
    }

    public void getTopSell(Callback<JsonTopSell> callback, String dateFrom, String dateTo) {

        if (retrofit != null) {
            Call<JsonTopSell> getTopSell = retrofit.create(ApiTopSell.class).getTopSell("Token " + Contstan.ACCESS_KEY,dateFrom,dateTo);

            getTopSell.enqueue(callback);
            Log.e("URL ",retrofit.baseUrl().toString());
        }
    }
}
