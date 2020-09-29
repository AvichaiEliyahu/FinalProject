package com.example.finalproject;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Activity_login extends AppCompatActivity {
    EditText login_TXT_userName;
    EditText login_TXT_password;
    Button login_BTN_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        requestPermissions();
        initLoginBTN();
    }

    private void initLoginBTN() {
        login_BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = login_TXT_userName.getText().toString();
                String password = login_TXT_password.getText().toString();
                
            }
        });
    }

    private void findViews() {
        login_TXT_userName=findViewById(R.id.login_TXT_userName);
        login_TXT_password=findViewById(R.id.login_TXT_password);
        login_BTN_login=findViewById(R.id.login_BTN_login);
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                20
        );
    }
}
