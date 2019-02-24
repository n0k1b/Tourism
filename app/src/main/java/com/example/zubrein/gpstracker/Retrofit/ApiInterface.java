package com.example.zubrein.gpstracker.Retrofit;


import com.example.zubrein.gpstracker.Model.LoginCheck;
import com.example.zubrein.gpstracker.Model.User;
import com.example.zubrein.gpstracker.Model.gallery_info;
import com.example.zubrein.gpstracker.Model.hospital_info;
import com.example.zubrein.gpstracker.Model.hotel_info;
import com.example.zubrein.gpstracker.Model.place_info;
import com.example.zubrein.gpstracker.Model.police_station_info;
import com.example.zubrein.gpstracker.Model.res_info;
import com.example.zubrein.gpstracker.Model.search_place;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Zubrein on 4/5/2018.
 */

public interface ApiInterface {

    @GET("gallery.php")
    Call<List<gallery_info>> get_gallery_info(@Query("place") String place);

    @GET("hospital_info.php")
    Call<List<hospital_info>> get_hospital_info(@Query("place") String place);

    @GET("police_station_info.php")
    Call<List<police_station_info>> get_police_station_info(@Query("place") String place);

    @GET("hotel_info.php")
    Call<List<hotel_info>> get_hotel_info(@Query("place") String place,@Query("budget") String budget);

    @GET("res_info_normal.php")
    Call<List<res_info>> get_res_info_normal(@Query("place") String place);

    @GET("res_info.php")
    Call<List<res_info>> get_res_info(@Query("place") String place,@Query("food_item") String food_item,@Query("food_budget") String food_budget);


    @GET("search_place.php")
    Call<List<search_place>> getData(@Query("place") String place,@Query("view") String view);

    @GET("place_info.php")
    Call<List<place_info>> get_place_info(@Query("place_info") String place_info);




    @FormUrlEncoded
    @POST("logincheck.php")
    Call<LoginCheck> logincheck(@Field("user_email") String user_email, @Field("user_password") String user_password);

    @FormUrlEncoded
    @POST("registration.php")
    Call<User> registration(@Field("user_name") String user_name,
                            @Field("user_email") String user_email,
                            @Field("user_password") String user_password,
                            @Field("user_mobile") String user_mobile);




}
