package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.finalproject.objects.Product;

import java.util.HashMap;

public class Activity_Show_Route extends AppCompatActivity {
    public static final String productsIntent= "PRODUCTS";
    public static final String superIDIntent= "SUPERID";
    private HashMap<String, Product> productsMap;
    private int superID;
    private TextView route_LBL_super;
    private ImageButton route_IMGBTN_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);
        getInfoFromIntent();

        findViews();
    }

    private void getInfoFromIntent() {
        Intent intent = getIntent();
        productsMap = (HashMap<String, Product>)intent.getSerializableExtra(productsIntent);
        superID = intent.getIntExtra(superIDIntent,0);
        Log.d("pttt",productsMap.toString());

    }

    private void findViews() {
        route_LBL_super = findViewById(R.id.route_LBL_super);
        route_IMGBTN_camera = findViewById(R.id.route_IMGBTN_camera);
        Glide
                .with(this)
                .load(R.drawable.ic_qr_code)
                .centerCrop()
                .into(route_IMGBTN_camera);
        route_IMGBTN_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCaemraForQR();
            }
        });
        route_LBL_super.setText(this.superID +"");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                Log.d("content",contents);
            }
            if(resultCode == RESULT_CANCELED){
                //handle cancel
            }
        }
    }

    private  void openCaemraForQR(){
        checkForCamPermission();

        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);

        } catch (Exception e) {

            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
            startActivity(marketIntent);

        }
    }

    private void checkForCamPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.CAMERA},
                20
        );
    }
}