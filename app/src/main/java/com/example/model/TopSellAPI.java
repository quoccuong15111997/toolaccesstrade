package com.example.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopSellAPI {
    @SerializedName("aff_link")
    @Expose
    private String aff_link;

    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("discount")
    @Expose
    private String discount;

    @SerializedName("brand")
    @Expose
    private String brand;

    @SerializedName("category_id")
    @Expose
    private String category_id;

    @SerializedName("category_name")
    @Expose
    private String category_name;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("product_category")
    @Expose
    private String product_category;

    @SerializedName("product_id")
    @Expose
    private String product_id;

    public TopSellAPI(String aff_link, String price, String discount, String brand, String category_id, String category_name, String image, String link, String name, String product_category, String product_id) {
        this.aff_link = aff_link;
        this.price = price;
        this.discount = discount;
        this.brand = brand;
        this.category_id = category_id;
        this.category_name = category_name;
        this.image = image;
        this.link = link;
        this.name = name;
        this.product_category = product_category;
        this.product_id = product_id;
    }

    public TopSellAPI() {
    }

    public String getAff_link() {
        return aff_link;
    }

    public void setAff_link(String aff_link) {
        this.aff_link = aff_link;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
