package com.example.zubrein.gpstracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    Context context;

    CardView booking,bookingList,record,payment;
    private String user_email;
    private Spinner choose_category,view_spinner;
    private TextView spinner_text,view_text;
    private EditText living_budget,food_budget,transport_budget;
    CardView btnRegister;
    TextView logout;
    SharedPreferences sharePreferenceRead;
    SharedPreferences.Editor sharedPreferenceEditor;
    String[] category;
    String[] view_list2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context=this;
        Intent intent = getIntent();
        user_email = intent.getStringExtra("user_email");



        //Register Status Checking
        sharePreferenceRead = getSharedPreferences("user", MODE_PRIVATE);
        sharedPreferenceEditor = sharePreferenceRead.edit();

        view_list2=getResources().getStringArray(R.array.view_list);
       category=getResources().getStringArray(R.array.category);
       choose_category=(Spinner)findViewById(R.id.category);
       spinner_text=(TextView)findViewById(R.id.spinner_text);
       view_text=(TextView)findViewById(R.id.view_text);
        view_spinner=(Spinner)findViewById(R.id.view);
        logout = findViewById(R.id.logout);

        btnRegister = findViewById(R.id.cardViewRegister);





        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.sample_view,R.id.spinner_text,category);
        choose_category.setAdapter(adapter);

        ArrayAdapter<String>adapter2=new ArrayAdapter<String>(this,R.layout.sample_view2,R.id.view_text,view_list2);
        view_spinner.setAdapter(adapter2);





        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnectingToInternet()==true) {
                    String place = choose_category.getSelectedItem().toString();
                    String choose_view = view_spinner.getSelectedItem().toString();


                    Intent intent = new Intent(HomeActivity.this, PlaceActivity.class);
                    intent.putExtra("place", place);
                    intent.putExtra("choose_view", choose_view);

                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(HomeActivity.this,"Check Internet connection",Toast.LENGTH_LONG).show();
                }






            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                sharedPreferenceEditor.putInt("status", 0);
                sharedPreferenceEditor.commit();
                startActivity(intent);
                finish();
            }
        });




    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HomeActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
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
