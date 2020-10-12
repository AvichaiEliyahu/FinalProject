package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

public class Activity_Show_Route extends AppCompatActivity {
    public static final String productsIntent= "PRODUCTS";
    public static final String superIDIntent= "SUPERID";
    private HashMap<String, Integer> productsMap;
    private int superID;
    TextView route_LBL_super;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);
        getInfoFromIntent();
        
        findViews();
    }

    private void getInfoFromIntent() {
        Intent intent = getIntent();
        productsMap = (HashMap<String, Integer>)intent.getSerializableExtra(productsIntent);
        superID = intent.getIntExtra(superIDIntent,0);
    }

    private void findViews() {
        route_LBL_super = findViewById(R.id.route_LBL_super);
        route_LBL_super.setText(this.superID +"");
    }
}