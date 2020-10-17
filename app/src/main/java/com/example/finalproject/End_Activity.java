package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class End_Activity extends AppCompatActivity {

    private Button end_BTN_newList;
    private Button end_BTN_exit;
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
                Intent i = new Intent(End_Activity.this,Activity_Supermarket_Select.class);
                startActivity(i);
                finish();
            }
        });
        end_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void findViews() {
        end_BTN_newList = findViewById(R.id.end_BTN_newList);
        end_BTN_exit = findViewById(R.id.end_BTN_exit);
    }
}