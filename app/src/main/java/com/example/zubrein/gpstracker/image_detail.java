package com.example.zubrein.gpstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class image_detail extends AppCompatActivity {
    RequestOptions myOptions = new RequestOptions()


            .override(300, 300);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Intent intent = getIntent();
        String image_name=intent.getStringExtra("image_name");
        ImageView img = (ImageView)findViewById(R.id.images);

        Glide.with(getApplicationContext()).load("http://www.hotelhillviewbandarban.com/jayed/" + image_name.toString()).apply(myOptions).into(img);
    }
}
