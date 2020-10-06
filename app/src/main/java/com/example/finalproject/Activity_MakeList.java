package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_MakeList extends AppCompatActivity {

    public static final String SUPER_ID = "SUPER_ID";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference supermarketsRef = database.getReference("Supermarkets");
    private List<Product> productsList = new ArrayList<>();
    private List<Product> selectedProducts = new ArrayList<>();
    private ListView make_list_LSTVIEW_products;
    private Button make_list_BTN_finish;
    private int superID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_list);

        superID = getIntent().getExtras().getInt(SUPER_ID,0);
        findViews();
        retriveData();

    }

    private void findViews() {
        make_list_LSTVIEW_products=findViewById(R.id.make_list_LSTVIEW_products);
        make_list_BTN_finish = findViewById(R.id.make_list_BTN_finish);
    }

    private void retriveData() {
        supermarketsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> dataList = snapshot.child(superID+"").child("products").getChildren();
                for(DataSnapshot data : dataList){
                    Log.d("size","size:"+productsList.size());
                    Product p = data.getValue(Product.class);
                    productsList.add(p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}