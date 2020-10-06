package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.CallBack.CallBack_SelectSupermarket;
import com.example.finalproject.fragments.Fragment_Map;
import com.example.finalproject.fragments.Fragment_Super_List;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Activity_Supermarket_Select extends AppCompatActivity implements CallBack_SelectSupermarket {
    private MaterialButton select_BTN_select;
    private Fragment_Super_List fragment_super_list;
    private Fragment_Map fragment_map;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private int selectedSupermarketID;

    DatabaseReference supermarketsRef = database.getReference("Supermarkets");
    DatabaseReference productsRef = database.getReference("Products");

    private List<Supermarket> supermarkets = new ArrayList<>();

    public List<Supermarket> getSupermarkets() {
        return supermarkets;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket_select);
//        supermarketsRef.addChildEventListener(childEventListener);
        retrieveData();
//        initFragments();
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
        fragment_super_list.setCallBack_selectSupermarket(this);
        FragmentTransaction transaction_super_list = getSupportFragmentManager().beginTransaction();
        transaction_super_list.replace(R.id.select_LAY_list, fragment_super_list);
        transaction_super_list.commit();

        fragment_map = new Fragment_Map();
        fragment_map.setCallBack_selectSupermarket(this);
        FragmentTransaction transaction_map = getSupportFragmentManager().beginTransaction();
        transaction_map.replace(R.id.select_LAY_map, fragment_map).commit();
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
        if (supermarkets.size() ==0 ){
            retrieveData();
        }
        Log.d("supers", supermarkets.toString());

        return supermarkets;
    }

    private void retrieveData(){
        supermarketsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> list = snapshot.getChildren();
                for (DataSnapshot data : list) {
                    Supermarket sp = data.getValue(Supermarket.class);
                    Log.d("print", sp.toString());
                    supermarkets.add(sp);
                }
                initFragments();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void selectSupermarket() {
        Intent i = new Intent(Activity_Supermarket_Select.this,Activity_MakeList.class);
        i.putExtra("",this.selectedSupermarketID);
        startActivity(i);
        finish();
    }


}
