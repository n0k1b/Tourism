package com.example.zubrein.gpstracker;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zubrein.gpstracker.Model.hospital_info;
import com.example.zubrein.gpstracker.Retrofit.ApiClient;
import com.example.zubrein.gpstracker.Retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class hospital extends AppCompatActivity {

    private ApiInterface apiInterface;
    ListView lv;
    CustomAdapter_hospital customAdapter;
    List<hospital_info> resInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        Bundle extras = getIntent().getExtras();
        String place=extras.getString("place");

        // Toast.makeText(hotel.this,budget,Toast.LENGTH_LONG).show();
        lv=findViewById(R.id.hospital);
        fetchInformation(place);
    }
    public  void fetchInformation(final String place){

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(hospital.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<hospital_info>> call = apiInterface.get_hospital_info(place);

        call.enqueue(new Callback<List<hospital_info>>() {
            @Override
            public void onResponse(Call<List<hospital_info>> call, Response<List<hospital_info>> response) {
                resInfo = response.body();

                customAdapter = new CustomAdapter_hospital(hospital.this,resInfo);
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
            public void onFailure(Call<List<hospital_info>> call, Throwable t) {
                progressDoalog.dismiss();
            }
        });
    }

}
