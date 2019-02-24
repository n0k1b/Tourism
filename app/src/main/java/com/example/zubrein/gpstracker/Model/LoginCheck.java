package com.example.zubrein.gpstracker.Model;

import com.google.gson.annotations.SerializedName;

public class LoginCheck {

    @SerializedName("user_email")
    private String user_email;

    @SerializedName("user_password")
    private String user_password;

    @SerializedName("response")
    private String response;

    public String getResponse() {
        return response;
    }
}
