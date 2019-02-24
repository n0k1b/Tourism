package com.example.zubrein.gpstracker;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zubrein.gpstracker.Model.LoginCheck;
import com.example.zubrein.gpstracker.Model.User;
import com.example.zubrein.gpstracker.Retrofit.ApiClient;
import com.example.zubrein.gpstracker.Retrofit.ApiInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LinearLayout layoutLogin,layoutRegistration;
    EditText useremail,userpass,conPass,usermobile,username,loginEmail,loginPass;
    CardView btnLogin,btnRegister;
    TextView regist,check;
    int stat = 0;
    ProgressDialog progressDoalog;
    private ApiInterface apiInterface;
    private  int STORAGE_PERMISSION = 1;

    SharedPreferences sharePreferenceRead;
    SharedPreferences.Editor sharedPreferenceEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Register Status Checking
        sharePreferenceRead = getSharedPreferences("user", MODE_PRIVATE);
        sharedPreferenceEditor = sharePreferenceRead.edit();

        int isExists = sharePreferenceRead.getInt("status", 0);
        if(isExists==1) {
            String user_email = sharePreferenceRead.getString("user_email","");
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            intent.putExtra("user_email",user_email);
            startActivity(intent);
            finish();
        }


        layoutLogin = findViewById(R.id.layoutLogin);
        layoutRegistration = findViewById(R.id.layoutRegistration);
        username = findViewById(R.id.input_name);
        useremail = findViewById(R.id.input_email);
        userpass = findViewById(R.id.input_password);
        conPass = findViewById(R.id.input_confirm_password);
        usermobile = findViewById(R.id.input_mobile);
        btnLogin = findViewById(R.id.cardViewLogin);
        btnRegister = findViewById(R.id.cardViewRegister);
        loginEmail = findViewById(R.id.input_emaill);
        loginPass = findViewById(R.id.input_passwordd);
        regist = findViewById(R.id.register);
        check = findViewById(R.id.regCheck);

        final EmailValidation emailValidation = new EmailValidation();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = loginEmail.getText().toString().trim();
                String pass = loginPass.getText().toString().trim();

                if(!email.equals("") && !pass.equals("")){
                    if (emailValidation.isValidate(email)) {
                        login_check(email, pass);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "please provide a valid email address", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Field must not empty",Toast.LENGTH_LONG).show();
                }

            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutLogin.setVisibility(View.GONE);
                layoutRegistration.setVisibility(View.VISIBLE);
                stat = 1;
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = username.getText().toString().trim();
                String email = useremail.getText().toString().trim();
                String pass = userpass.getText().toString().trim();
                String conpass = conPass.getText().toString().trim();
                String mobile = usermobile.getText().toString().trim();

                if(!name.equals("") && !email.equals("") && !pass.equals("") && !conpass.equals("") && !mobile.equals("")) {
                    if (emailValidation.isValidate(email)){
                        if (pass.equals(conpass)) {
                            if (mobile.length() == 11 || mobile.length() == 13 || mobile.length() == 14) {
                                registration(name, email, pass, mobile);
                            } else {
                                Toast.makeText(MainActivity.this, "please provide a valide mobile number", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "please provide same password", Toast.LENGTH_LONG).show();
                        }
                }else{
                        Toast.makeText(MainActivity.this, "please provide a valid email address", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Field must not empty",Toast.LENGTH_LONG).show();
                }



            }
        });



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
            return;
        }

    }


    private void login_check(final String email, final String pass){

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<LoginCheck> call = apiInterface.logincheck(email,pass);

        call.enqueue(new Callback<LoginCheck>() {
            @Override
            public void onResponse(Call<LoginCheck> call, Response<LoginCheck> response) {
                LoginCheck loginCheck = response.body();



                if(loginCheck.getResponse().equals("yes")){
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    sharedPreferenceEditor.putString("user_email", email);
                    sharedPreferenceEditor.putInt("status", 1);
                    sharedPreferenceEditor.commit();
                    intent.putExtra("user_email",email);
                    startActivity(intent);
                    finish();
                    progressDoalog.dismiss();
                }
                else if(loginCheck.getResponse().equals("not_confirm"))
                {
                    Toast.makeText(MainActivity.this,"Please Verify Your Email",Toast.LENGTH_LONG).show();
                    progressDoalog.dismiss();
                }

                else{
                    Toast.makeText(MainActivity.this,"Check your email and password.",Toast.LENGTH_LONG).show();
                    progressDoalog.dismiss();
                }


            }

            @Override
            public void onFailure(Call<LoginCheck> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this,"Check Internet connection",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(stat == 0){
            finish();
        }
        if (stat == 1) {
            layoutRegistration.setVisibility(View.GONE);
            layoutLogin.setVisibility(View.VISIBLE);
            stat = 0;
        }

    }


    public  void registration(String user_name, String user_email, String user_password, String user_mobile){
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("loading....");
        progressDoalog.show();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<User> call = apiInterface.registration(user_name,user_email,user_password,user_mobile);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
               User user = response.body();

               if(user.getResponse().equals("registered")){
                   check.setVisibility(View.VISIBLE);
                   progressDoalog.dismiss();
               }else{
                    if (user.getResponse().equals("Registered successfully")) {
                        Toast.makeText(MainActivity.this, "Registered successfully.Check Your Email to verify your account ", Toast.LENGTH_LONG).show();
                        layoutRegistration.setVisibility(View.GONE);
                        layoutLogin.setVisibility(View.VISIBLE);
                        stat = 0;
                        progressDoalog.dismiss();
                    } else {
                        progressDoalog.dismiss();
                        Toast.makeText(MainActivity.this, "Registered failed", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this,"Check Internet connection",Toast.LENGTH_LONG).show();
            }
        });
    }

    public class EmailValidation {
        private Pattern pattern;
        private Matcher matcher;

        private static final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        public EmailValidation() {
            pattern = Pattern.compile(EMAIL_PATTERN);
        }

        public boolean isValidate(final String hex) {
            matcher = pattern.matcher(hex);
            return matcher.matches();
        }
    }
}
