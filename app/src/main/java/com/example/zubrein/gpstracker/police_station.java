package com.example.zubrein.gpstracker;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zubrein.gpstracker.Model.police_station_info;
import com.example.zubrein.gpstracker.Retrofit.ApiClient;
import com.example.zubrein.gpstracker.Retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class police_station extends AppCompatActivity {

    private ApiInterface apiInterface;
    ListView lv;
    CustomAdapter_police customAdapter;
    List<police_station_info> resInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_station);
        Bundle extras = getIntent().getExtras();
        String place=extras.getString("place");

        // Toast.makeText(hotel.this,budget,Toast.LENGTH_LONG).show();
        lv=findViewById(R.id.police);
        fetchInformation(place);
    }
    public  void fetchInformation(final String place){

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(police_station.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<police_station_info>> call = apiInterface.get_police_station_info(place);

        call.enqueue(new Callback<List<police_station_info>>() {
            @Override
            public void onResponse(Call<List<police_station_info>> call, Response<List<police_station_info>> response) {
                resInfo = response.body();

                customAdapter = new CustomAdapter_police(police_station.this,resInfo);
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
            public void onFailure(Call<List<police_station_info>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(police_station.this,"Check Internet connection",Toast.LENGTH_LONG).show();
            }
        });
    }

}
