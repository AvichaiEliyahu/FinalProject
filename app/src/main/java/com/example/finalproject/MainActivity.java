package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("Users");
    DatabaseReference productsRef = database.getReference("Products");
    DatabaseReference supermarketsRef = database.getReference("Supermarkets");
    DatabaseReference supermarketsProductsRef = database.getReference("Supermarkets-Products");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProductsList pList = new ProductsList();
        pList.add(new Product("0","Milk",null,3.5));
        pList.add(new Product("1","Eggs",null,4.6));
        pList.add(new Product("2","Chocolate",null,2.5));
        pList.add(new Product("3","Water",null,9.5));
        pList.add(new Product("4","Soap",null,3.5));
        pList.add(new Product("5","Rice",null,2.5));
        pList.add(new Product("6","Butter",null,1.5));
        pList.add(new Product("7","Bread",null,1.5));
        pList.add(new Product("8","Olive Oil",null,7.5));
        pList.add(new Product("9","Tuna",null,13.5));
        pList.add(new Product("10","Tomato",null,6.5));
        pList.add(new Product("11","Cucumber",null,6.5));
        pList.add(new Product("12","Coca-Cola",null,19.5));
        pList.add(new Product("13","Toothpaste",null,10.0));
        pList.add(new Product("14","Toothbrush",null,10.0));
        pList.add(new Product("15","Mask",null,3.5));
        pList.add(new Product("16","Alcogel",null,15.0));
        pList.add(new Product("17","Diapers",null,20.0));
        pList.add(new Product("18","Bear",null,13.90));
        pList.add(new Product("19","Television",null,2000.0));
        pList.add(new Product("20","PlayStation5",null,2100.0));


        ProductsList sp1Prods = new ProductsList();
        sp1Prods.add(pList.get(3));
        sp1Prods.add(pList.get(4));
        sp1Prods.add(pList.get(1));
        sp1Prods.add(pList.get(8));
        sp1Prods.add(pList.get(15));

        Supermarket sp1 = new Supermarket("0",10,32.331725, 34.858923,sp1Prods);

        ProductsList sp2Prods = new ProductsList();
        sp2Prods.add(pList.get(3));
        sp2Prods.add(pList.get(4));
        sp2Prods.add(pList.get(1));
        sp2Prods.add(pList.get(8));
        sp2Prods.add(pList.get(15));
        sp2Prods.add(pList.get(13));
        sp2Prods.add(pList.get(12));
        sp2Prods.add(pList.get(17));
        sp2Prods.add(pList.get(16));
        sp2Prods.add(pList.get(10));

        Supermarket sp2 = new Supermarket("1",10,32.331121, 34.862819,sp2Prods);

        ProductsList sp3Prods = new ProductsList();
        sp2Prods.add(pList.get(0));
        sp2Prods.add(pList.get(1));
        sp2Prods.add(pList.get(2));
        sp2Prods.add(pList.get(3));
        sp2Prods.add(pList.get(4));
        sp2Prods.add(pList.get(5));
        sp2Prods.add(pList.get(6));
        sp2Prods.add(pList.get(7));
        sp2Prods.add(pList.get(8));
        sp2Prods.add(pList.get(9));
        sp2Prods.add(pList.get(10));
        sp2Prods.add(pList.get(11));
        sp2Prods.add(pList.get(12));
        sp2Prods.add(pList.get(13));
        sp2Prods.add(pList.get(14));

        Supermarket sp3 = new Supermarket("2",10,32.335086, 34.859354,sp2Prods);
        ArrayList<Supermarket> supers = new ArrayList<Supermarket>();
        supers.add(sp1);
        supers.add(sp2);
        supers.add(sp3);
        supermarketsRef.setValue(supers);

    }
}