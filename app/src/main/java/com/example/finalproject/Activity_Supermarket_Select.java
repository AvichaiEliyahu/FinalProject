package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class Activity_Supermarket_Select extends AppCompatActivity {
    private MaterialButton select_BTN_select;
    private Fragment_Super_List fragment_super_list;
    private Fragment_Map fragment_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket_select);
        initFragments();
        findViews();
        setRoles();
        //TODO delete on submission!
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                20
        );
    }

    private void initFragments() {
        fragment_super_list = new Fragment_Super_List();
        FragmentTransaction transaction_super_list = getSupportFragmentManager().beginTransaction();
        transaction_super_list.replace(R.id.select_LAY_list,fragment_super_list);
        transaction_super_list.commit();

        fragment_map = new Fragment_Map();
        FragmentTransaction transaction_map = getSupportFragmentManager().beginTransaction();
        transaction_map.replace(R.id.select_LAY_map,fragment_map);
        transaction_map.commit();
    }


    private void findViews() {
        select_BTN_select = findViewById(R.id.select_BTN_select);
    }

    private void setRoles() {
        select_BTN_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectSupermarket();
            }
        });
    }

    private void selectSupermarket() {
    }
}
