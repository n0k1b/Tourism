package com.example.zubrein.gpstracker;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.zubrein.gpstracker.Model.place_info;
import com.example.zubrein.gpstracker.Model.place_info;
import com.example.zubrein.gpstracker.Model.police_station_info;
import com.example.zubrein.gpstracker.Retrofit.ApiClient;
import com.example.zubrein.gpstracker.Retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class choose_category extends AppCompatActivity {
    CardView res,hotel,hospital,police,transport,gallery;


    int SPLASH_TIME_OUT=400;
  //  final Dialog myDialog;

    List<place_info>search;
    private ApiInterface apiInterface;
    private Spinner choose_category,view_spinner;
     ImageView img2;
     TextView place_name;
    private Context c;
    private Context context;
    String[] food_item;

    RequestOptions myOptions = new RequestOptions()

            .centerCrop()
            .override(300, 300);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        context = this;

        food_item=getResources().getStringArray(R.array.food_item);

        img2=(ImageView)findViewById(R.id.img);
        place_name=(TextView)findViewById(R.id.place_name);
        res=findViewById(R.id.res2);
        hotel=findViewById(R.id.hotel);
        hospital=findViewById(R.id.hospital);
        police=findViewById(R.id.police);
        transport=findViewById(R.id.transport);
        gallery=findViewById(R.id.gallery);
        Intent intent = getIntent();

        String place=intent.getStringExtra("place_name");




        fetchInformation(place);





    }
    public  void fetchInformation(final String place){

        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(choose_category.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<place_info>> call = apiInterface.get_place_info(place);



        call.enqueue(new Callback<List<place_info>>() {
            @Override
            public void onResponse(Call<List<place_info>> call, Response<List<place_info>> response) {
                search = response.body();

                res.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        for(place_info p1:search)
                        {
                            if(p1.getRes_type().equals("lux")) {


                                TextView txtclose2;
                                //EditText hotel_budget;
                                CardView bcsCard, hscCard, sscCard, res_apply;


                                final Dialog myDialog = new Dialog(choose_category.this);
                                myDialog.setContentView(R.layout.pop_up_res);
                                myDialog.setCanceledOnTouchOutside(false);
                                txtclose2 = myDialog.findViewById(R.id.closePopupMenu2);

                                res_apply=myDialog.findViewById(R.id.res_apply);
                                view_spinner=(Spinner)myDialog.findViewById(R.id.view);

                                ArrayAdapter<String>adapter2=new ArrayAdapter<String>(context,R.layout.sample_view3,R.id.view_text,food_item);
                                view_spinner.setAdapter(adapter2);



                                res_apply.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        EditText res_budget=(EditText)myDialog.findViewById(R.id.budget);
                                        String bu=res_budget.getText().toString();
                                        String item=view_spinner.getSelectedItem().toString();



                                        //Toast.makeText(choose_category.this,bu,Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(choose_category.this, res.class);
                                        intent.putExtra("place", place);
                                        intent.putExtra("budget",bu);
                                        intent.putExtra("food_type",item);


                                        startActivity(intent);


                                        myDialog.dismiss();
                                    }
                                });

                                txtclose2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        myDialog.dismiss();
                                    }
                                });
                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.show();




                            }
                            else{


                                Intent intent = new Intent(choose_category.this, res_normal.class);
                                intent.putExtra("place", place);
                                startActivity(intent);
                            }
                            //String test=p1.getRes_type();
                            //Toast.makeText(choose_category.this,test,Toast.LENGTH_LONG).show();
                        }
                        //Intent intent = new Intent(choose_category.this,res.class);
                        //intent.putExtra("place",place);
                        //Intent intent = new Intent(choose_category.this,test.class);
                        //startActivity(intent);

                    }
                });


                police.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                                Intent intent = new Intent(choose_category.this, police_station.class);
                                intent.putExtra("place", place);
                                startActivity(intent);


                    }
                });

                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        Intent intent = new Intent(choose_category.this, gallery.class);
                        intent.putExtra("place", place);
                        startActivity(intent);


                    }
                });

                transport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        Intent intent = new Intent(choose_category.this, travel_path.class);
                        intent.putExtra("place", place);
                        startActivity(intent);


                    }
                });

                hospital.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        Intent intent = new Intent(choose_category.this,hospital.class);
                        intent.putExtra("place", place);
                        startActivity(intent);


                    }
                });






                hotel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                                TextView txtclose;
                               //EditText hotel_budget;
                                CardView bcsCard, hscCard, sscCard, hotel_apply;


                        final Dialog myDialog = new Dialog(choose_category.this);
                                myDialog.setContentView(R.layout.pop_up_hotel);
                                myDialog.setCanceledOnTouchOutside(false);
                                txtclose = myDialog.findViewById(R.id.closePopupMenu);

                                hotel_apply=myDialog.findViewById(R.id.hotel_apply);




                                hotel_apply.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                       EditText hotel_budget=(EditText)myDialog.findViewById(R.id.budget);
                                        String bu=hotel_budget.getText().toString();
                                        //Toast.makeText(choose_category.this,bu,Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(choose_category.this, hotel.class);
                                        intent.putExtra("place", place);
                                        intent.putExtra("budget",bu);
                                        startActivity(intent);


                                        myDialog.dismiss();
                                    }
                                });

                                txtclose.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        myDialog.dismiss();
                                    }
                                });
                                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                myDialog.show();




                    }
                });


                for(final place_info p:search) {
                        Glide.with(getApplicationContext()).load("http://www.hotelhillviewbandarban.com/jayed/" + p.getPlace_image().toString()).apply(myOptions).into(img2);
                    }
                  place_name.setText(place);


                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<List<place_info>> call, Throwable t) {
                progressDoalog.dismiss();

                Toast.makeText(choose_category.this,"Check Internet connection",Toast.LENGTH_LONG).show();
            }
        });
    }

}
