package com.example.zubrein.gpstracker.Model;

import com.google.gson.annotations.SerializedName;

public class res_info {



        @SerializedName("id")
        private String id;
        @SerializedName("res_name")

        private String res_name;
        @SerializedName("res_image")
        private String res_image;
        @SerializedName("res_lat")
        private String res_lat;
        @SerializedName("res_lon")
        private String res_lon;

    @SerializedName("res_des")
    private String res_des;

        public String getId() {
            return id;
        }

        public String getRes_name() {
            return res_name;
        }

        public String getRes_image() {
            return res_image;
        }

        public String getRes_lat() {
            return res_lat;
        }

        public String getRes_lon() {
            return res_lon;
        }

    public String getRes_des() {
        return res_des;
    }



}
