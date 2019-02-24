package com.example.zubrein.gpstracker.Model;

import com.google.gson.annotations.SerializedName;

public class place_info {


    @SerializedName("place_image")
    private String place_image;


    @SerializedName("res_type")
    private String res_type;


    @SerializedName("travel_path")
    private String travel_path;



    public String getPlace_image() {
        return place_image;
    }
    public String getRes_type() {
        return res_type;
    }

    public String getTravel_path() {
        return travel_path;
    }
}
