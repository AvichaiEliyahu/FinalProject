package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class End_Activity extends AppCompatActivity {

    private MaterialButton end_BTN_newList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        findViews();
        setButtons();
    }

    private void setButtons() {
        end_BTN_newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void findViews() {
        end_BTN_newList = findViewById(R.id.end_BTN_newList);
    }
}