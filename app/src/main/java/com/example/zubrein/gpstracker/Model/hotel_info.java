package com.example.zubrein.gpstracker.Model;

import com.google.gson.annotations.SerializedName;

public class hotel_info {
    @SerializedName("id")
    private String id;
    @SerializedName("hotel_name")

    private String hotel_name;
    @SerializedName("hotel_image")
    private String hotel_image;
    @SerializedName("hotel_lat")
    private String hotel_lat;
    @SerializedName("hotel_lon")
    private String hotel_lon;

    @SerializedName("price")
    private String price;

    public String getId() {
        return id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public String getHotel_image() {
        return hotel_image;
    }

    public String getHotel_lat() {
        return hotel_lat;
    }

    public String getHotel_lon() {
        return hotel_lon;
    }

    public String getPrice(){return  price;}
}
