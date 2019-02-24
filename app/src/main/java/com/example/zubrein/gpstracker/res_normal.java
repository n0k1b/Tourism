package com.example.zubrein.gpstracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zubrein.gpstracker.Model.place_info;
import com.example.zubrein.gpstracker.Model.res_info;
import com.example.zubrein.gpstracker.Retrofit.ApiClient;
import com.example.zubrein.gpstracker.Retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class res_normal extends AppCompatActivity {
    private ApiInterface apiInterface;
    List<res_info>resInfo;
    TextView res_nor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_normal);
        Intent intent = getIntent();
        String place=intent.getStringExtra("place");


        res_nor=(TextView)findViewById(R.id.res_normal);


        fetchInformation(place);
    }

    public  void fetchInformation(final String place){

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(res_normal.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<res_info>> call = apiInterface.get_res_info_normal(place);

        call.enqueue(new Callback<List<res_info>>() {
            @Override
            public void onResponse(Call<List<res_info>> call, Response<List<res_info>> response) {
                resInfo = response.body();
                for(res_info p:resInfo)
                {
                    res_nor.setText(p.getRes_des());
                }

            }

            @Override
            public void onFailure(Call<List<res_info>> call, Throwable t) {
                progressDoalog.dismiss();
            }
        });
    }
}
