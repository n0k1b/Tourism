package com.example.zubrein.gpstracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zubrein.gpstracker.Model.res_info;

import com.example.zubrein.gpstracker.Retrofit.ApiClient;
import com.example.zubrein.gpstracker.Retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class res extends AppCompatActivity {

    private ApiInterface apiInterface;
    ListView lv;
    CustomAdapter_res customAdapter;
    List<res_info>resInfo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        Intent intent = getIntent();
        String place=intent.getStringExtra("place");
        String food_type=intent.getStringExtra("food_type");
        String food_budget=intent.getStringExtra("budget");
        lv=findViewById(R.id.res);
        fetchInformation(place,food_type,food_budget);
    }
    public  void fetchInformation(final String place,final String food_type,final String food_budget){

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(res.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<res_info>> call = apiInterface.get_res_info(place,food_type,food_budget);

        call.enqueue(new Callback<List<res_info>>() {
            @Override
            public void onResponse(Call<List<res_info>> call, Response<List<res_info>> response) {
                resInfo = response.body();

                customAdapter = new CustomAdapter_res(res.this,resInfo);
                lv.setAdapter(customAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                       // Intent intent = new Intent(res.this,choose_category.class);
                       // intent.putExtra("place_name",resInfo.get(position).getPlace_name());

                       // startActivity(intent);
                        // String test=resInfo.get(position).getPlace_name();
                        //Toast.makeText(res.this,test,Toast.LENGTH_LONG).show();


                    }
                });
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<List<res_info>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(res.this,"Check Internet connection",Toast.LENGTH_LONG).show();
            }
        });
    }
   
    

   
}
