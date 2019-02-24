package com.example.zubrein.gpstracker.Model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_email")
    private String user_email;
    @SerializedName("user_password")
    private String user_password;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("user_mobile")
    private String user_mobile;
    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }
}
