package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.finalproject.CallBack.CallBack_Map;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_Supermarket_Select extends AppCompatActivity implements CallBack_Map {
    private MaterialButton select_BTN_select;
    private Fragment_Super_List fragment_super_list;
    private Fragment_Map fragment_map;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference supermarketsRef = database.getReference("Supermarkets");
    private List<Supermarket> supermarkets = new ArrayList<>();

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
        transaction_super_list.replace(R.id.select_LAY_list, fragment_super_list);
        transaction_super_list.commit();

        fragment_map = new Fragment_Map();
        fragment_map.setCallBack_map(this);
        FragmentTransaction transaction_map = getSupportFragmentManager().beginTransaction();
        transaction_map.replace(R.id.select_LAY_map, fragment_map);
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

    @Override
    public List<Supermarket> getAllSupermarkets() {

        supermarketsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    Log.d("supers", s.toString());
                    ProductsList pList = new ProductsList();
                    pList.add(new Product("0",null,1,null));
                    pList.add(new Product("1",null,3,null));
                    pList.add(new Product("2",null,1,null));
                    pList.add(new Product("3",null,5,null));
                    pList.add(new Product("4",null,1,null));
                    pList.add(new Product("5",null,2,null));
                    pList.add(new Product("6",null,7,null));
                    pList.add(new Product("7",null,10,null));
                    Supermarket sp1 = new Supermarket(1,10,32.331725, 34.858923,pList);
                    pList.remove(7);
                    Supermarket sp2 = new Supermarket(2,7,32.331725, 34.858923,pList);
                    supermarkets.add(sp1);
                    supermarkets.add(sp2);
//                    Supermarket supermarket = s.getValue(Supermarket.class);
//                    Log.d("supers", supermarket.toString());
//                    rv.add(supermarket);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

                Log.d("supers", supermarkets.toString());
        return supermarkets;
    }

    @Override
    public Supermarket selectSupermarket() {
        return null;
    }


}
