package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonTopSell {
    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("data")
    @Expose
    private List<TopSellAPI> data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<TopSellAPI> getData() {
        return data;
    }

    public void setData(List<TopSellAPI> data) {
        this.data = data;
    }
}
