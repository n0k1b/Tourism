package com.example.zubrein.gpstracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import com.example.zubrein.gpstracker.Model.gallery_info;
import com.example.zubrein.gpstracker.Model.gallery_info;
import com.example.zubrein.gpstracker.Retrofit.ApiClient;
import com.example.zubrein.gpstracker.Retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gallery extends AppCompatActivity {
    private ApiInterface apiInterface;

    RecyclerView recyclerView;
    CustomAdapter_gallery recyclerAdapter;


    List<gallery_info> galleryList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Bundle extras = getIntent().getExtras();
        String place=extras.getString("place");
        galleryList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.gallery);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerAdapter = new CustomAdapter_gallery(getApplicationContext(),galleryList);
        recyclerView.setAdapter(recyclerAdapter);


        fetchInformation(place);

    }

    public  void fetchInformation(final String place){

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(gallery.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<gallery_info>> call = apiInterface.get_gallery_info(place);

        call.enqueue(new Callback<List<gallery_info>>() {
            @Override
            public void onResponse(Call<List<gallery_info>> call, Response<List<gallery_info>> response) {
                galleryList = response.body();


                progressDoalog.dismiss();
                recyclerAdapter.setgallery(galleryList);

                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                // do whatever
                                Intent intent = new Intent(gallery.this, image_detail.class);
                                intent.putExtra("image_name",galleryList.get(position).getImage());
                                startActivity(intent);

                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );

            }

            @Override
            public void onFailure(Call<List<gallery_info>> call, Throwable t) {
                progressDoalog.dismiss();
            }
        });
    }
}
