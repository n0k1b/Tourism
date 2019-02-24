package com.example.zubrein.gpstracker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zubrein.gpstracker.Model.search_place;
import com.example.zubrein.gpstracker.Retrofit.ApiClient;
import com.example.zubrein.gpstracker.Retrofit.ApiInterface;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceActivity extends AppCompatActivity {

    ListView lv;
    CardView place;
    Context context;
    List<search_place>search;
    private ApiInterface apiInterface;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        Intent intent = getIntent();
        String place=intent.getStringExtra("place");
        String choose_view=intent.getStringExtra("choose_view");

        context=this;
        lv=findViewById(R.id.lv);
        fetchInformation(place,choose_view);


    }

    public  void fetchInformation(final String place,final String choose_view){

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(PlaceActivity.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<search_place>> call = apiInterface.getData(place,choose_view);

        call.enqueue(new Callback<List<search_place>>() {
            @Override
            public void onResponse(Call<List<search_place>> call, Response<List<search_place>> response) {
                search = response.body();



                customAdapter = new CustomAdapter(PlaceActivity.this,search);
                lv.setAdapter(customAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        if(isConnectingToInternet()==true) {
                            if(search.get(position).getPlace_name()!=null) {
                                Intent intent = new Intent(PlaceActivity.this, choose_category.class);
                                intent.putExtra("place_name", search.get(position).getPlace_name());

                                startActivity(intent);
                                // String test=search.get(position).getPlace_name();
                                //Toast.makeText(PlaceActivity.this,test,Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(PlaceActivity.this,"Place Not Found",Toast.LENGTH_LONG).show();
                            }
                            }
                        else{
                            Toast.makeText(PlaceActivity.this,"Check Internet connection",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<List<search_place>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(PlaceActivity.this,"Check Internet connection",Toast.LENGTH_LONG).show();
            }
        });
    }


    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }


}
