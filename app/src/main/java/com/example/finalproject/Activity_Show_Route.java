package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.finalproject.adapters.ProductAdapter;
import com.example.finalproject.objects.Product;
import com.example.finalproject.objects.Supermarket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Activity_Show_Route extends AppCompatActivity {
    public static final String productsIntent = "PRODUCTS";
    public static final String superIntent = "SUPER";
    private int topProduct = 0;
    private HashMap<String, Product> productsMap;
    private TextView route_LBL_nextProductName;
    private TextView route_LBL_nextProductRowNum;
    private TextView route_LBL_super;
    private TextView route_LBL_amount;
    private ImageButton route_IMGBTN_camera;
    private ImageButton route_IMGBTN_check;
    private Button route_IMGBTN_finish;
    private Supermarket demoSuper;
    private ListView route_LSTVIEW_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_route);
        getInfoFromIntent();
        createSupermarketAndSort();
        findViews();
        setUI();
        buttonPress();
    }

    private void setUI() {
        Log.d("list", this.demoSuper.getProducts().toString());
        ProductAdapter arrayAdapter = new ProductAdapter(this, R.layout.product_list_item, this.demoSuper.getProducts());
        route_LSTVIEW_list.setAdapter(arrayAdapter);
        if (topProduct >= this.demoSuper.getProducts().size()) {
            setProductsLabelsWIthProduct("You have finished", " your shopping", "-");
            openFinalActivity();
        } else {
            Product p = demoSuper.getProducts().get(0);
            setProductsLabelsWIthProduct(p.getProdName(), "Row: "+p.getRowNum(), p.getAmount()+"");
        }
    }

    private void setProductsLabelsWIthProduct(String productName, String productRow, String amount) {
        route_LBL_nextProductName.setText(productName);
        route_LBL_nextProductRowNum.setText(productRow);
        route_LBL_amount.setText(amount);
    }

    private void buttonPress() {
        route_IMGBTN_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextProduct();
            }
        });
    }

    private void openFinalActivity() {
        route_IMGBTN_check.setClickable(false);
        route_IMGBTN_check.setVisibility(View.INVISIBLE);
        route_IMGBTN_camera.setClickable(false);
        route_IMGBTN_camera.setVisibility(View.INVISIBLE);

        route_IMGBTN_finish.setVisibility(View.VISIBLE);
        route_IMGBTN_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishShopping();

            }
        });
    }

    private void finishShopping() {
        Intent i = new Intent(Activity_Show_Route.this, End_Activity.class);
        startActivity(i);
        finish();
    }

    private void createSupermarketAndSort() {
        this.demoSuper.setProducts(new ArrayList<Product>(productsMap.values()));
        this.demoSuper.sortProductsByRow();
        Log.d("pttt2", demoSuper.toString());
    }

    private void getInfoFromIntent() {
        Intent intent = getIntent();
        productsMap = (HashMap<String, Product>) intent.getSerializableExtra(productsIntent);
        demoSuper = (Supermarket) getIntent().getSerializableExtra(superIntent);
        Log.d("pttt", productsMap.toString());
        Log.d("pttt1", demoSuper.toString());

    }

    private void findViews() {
        route_LBL_nextProductName = findViewById(R.id.route_LBL_nextProductName);
        route_LBL_nextProductRowNum = findViewById(R.id.route_LBL_nextProductRowNum);
        route_LBL_amount = findViewById(R.id.route_LBL_amount);

        route_LBL_super = findViewById(R.id.route_LBL_super);
        route_IMGBTN_check = findViewById(R.id.route_IMGBTN_check);
        route_IMGBTN_finish = findViewById(R.id.route_IMGBTN_finish);
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
        String address = getSuperAddress();
        route_LBL_super.setText(address);
        route_LSTVIEW_list = findViewById(R.id.route_LSTVIEW_list);

    }

    private String getSuperAddress() {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(demoSuper.getLat(), demoSuper.getLon(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            return addresses.get(0).getAddressLine(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No Address";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
                Log.d("content", contents+" == " + demoSuper.getProducts().get(0).getProdID());
                if (contents.equals(demoSuper.getProducts().get(topProduct).getProdID())) {
                    nextProduct();
                }
                else{
                    Toast.makeText(this, "This product is no " +demoSuper.getProducts().get(0).getProdName() ,
                            Toast.LENGTH_LONG).show();
                }
            }
            if (resultCode == RESULT_CANCELED) {
                //handle cancel
            }
        }
    }

    private void nextProduct() {
        this.demoSuper.getProducts().remove(0);
        setUI();
    }

    private void openCaemraForQR() {
        checkForCamPermission();
        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
            startActivityForResult(intent, 0);
        } catch (Exception e) {
            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
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