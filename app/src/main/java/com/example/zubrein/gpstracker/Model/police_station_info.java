package com.example.zubrein.gpstracker.Model;

import com.google.gson.annotations.SerializedName;

public class police_station_info {
    @SerializedName("police_station_name")
    private String police_station_name;
    @SerializedName("address")
    private String address;
    @SerializedName("contact_no")
    private String contact_no;

    public String getPolice_station_name() {
        return police_station_name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact_no() {
        return contact_no;
    }
}
