package com.example.zubrein.gpstracker;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zubrein.gpstracker.Model.place_info;
import com.example.zubrein.gpstracker.Retrofit.ApiClient;
import com.example.zubrein.gpstracker.Retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class travel_path extends AppCompatActivity {
    TextView travel_path;
    List<place_info> search;
    private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_path);
        travel_path = findViewById(R.id.travel_path);
        Intent intent = getIntent();
        String place = intent.getStringExtra("place");
        fetchInformation(place);


    }




    public  void fetchInformation(final String place){


        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(travel_path.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<place_info>> call = apiInterface.get_place_info(place);
        call.enqueue(new Callback<List<place_info>>() {
            @Override
            public void onResponse(Call<List<place_info>> call, Response<List<place_info>> response) {
                search = response.body();












                for(final place_info p:search) {
                    travel_path.setText(p.getTravel_path());
                }



                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<List<place_info>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(travel_path.this,"Check Internet connection",Toast.LENGTH_LONG).show();
            }
        });

    }
}
