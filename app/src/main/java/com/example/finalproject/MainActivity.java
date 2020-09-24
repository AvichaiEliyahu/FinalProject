package com.example.finalproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("Users");
    DatabaseReference productsRef = database.getReference("Products");
    DatabaseReference supermarketsRef = database.getReference("Supermarkets");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productsRef.child("5").setValue(new Product().setPrice(4.4).setProdName("water"));
        productsRef.child("4").setValue(new Product());


        /*usersRef.child("user1").child("username").setValue("myUN");
        usersRef.child("user1").child("password").setValue("myPassword");
        DatabaseReference user1ref = usersRef.child("user1");
        user1ref.child("password").setValue("newpassword");*/
       /*usersRef.child("userName").setValue("username1");
        usersRef.child("password").setValue("password1");
        usersRef.child("products").setValue("myprodlist");*/
        /*Product p1 = new Product(1234,"milk",23,44.6);
        Product p2 = new Product(12345,"eggs",1,3.6);
        Product p3 = new Product(123456,"water",77,3.5);
        ProductsList list= new ProductsList();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        User u1 = new User("12345","pass123",list,USER_TYPE.USER);
        usersRef.child("user1").setValue(u1);*/



    }
}