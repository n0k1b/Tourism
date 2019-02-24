package com.example.zubrein.gpstracker.Model;

import com.google.gson.annotations.SerializedName;

public class search_place {




    @SerializedName("place_name")
    private String place_name;

    @SerializedName("place_image")
    private String place_image;



    public String getPlace_name() {
        return place_name;
    }
    public String getPlace_image() {
        return place_image;
    }





}
