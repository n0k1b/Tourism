package com.example.zubrein.gpstracker.Model;

import com.google.gson.annotations.SerializedName;

public class hospital_info {

    @SerializedName("hospital_name")
    private String hospital_name;
    @SerializedName("address")
    private String address;
    @SerializedName("contact_no")
    private String contact_no;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;

    public String getHospital_name() {
        return hospital_name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
