package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.finalproject.adapters.ProductAdapter;
import com.example.finalproject.objects.Product;
import com.example.finalproject.objects.Supermarket;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity_MakeList extends AppCompatActivity {

    public static final String SUPER_ID = "SUPER_ID";
    public static final String SUPER = "SUPER";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference supermarketsRef = database.getReference("Supermarkets");
    private List<Product> productsList = new ArrayList<>();
    private HashMap<String, Product> selectedProducts = new HashMap<String, Product>();
    private ListView make_list_LSTVIEW_products;
    private Button make_list_BTN_finish;
//    private int superID;
    private Supermarket supermarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_list);

//        superID = getIntent().getExtras().getInt(SUPER_ID, 0);
        supermarket = (Supermarket) getIntent().getSerializableExtra(SUPER);
        Log.d("super",supermarket.toString());
        findViews();
        retriveData();
        viewsRoles();
    }

    private void viewsRoles() {
        make_list_BTN_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endShopList();
                finish();
            }
        });
    }

    private void endShopList() {
        Intent i = new Intent(Activity_MakeList.this,Activity_Show_Route.class);
        Log.d("size1","size from make_list:"+this.selectedProducts.size());
        i.putExtra(Activity_Show_Route.productsIntent,this.selectedProducts);
        i.putExtra(Activity_Show_Route.superIntent,this.supermarket);
        startActivity(i);
    }

    private void findViews() {
        make_list_LSTVIEW_products = findViewById(R.id.make_list_LSTVIEW_products);
        make_list_BTN_finish = findViewById(R.id.make_list_BTN_finish);
    }

    private void retriveData() {
        supermarketsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> dataList = snapshot.child(supermarket.getSuperID() + "").child("products").getChildren();
                for (DataSnapshot data : dataList) {
                    Log.d("size", "size:" + productsList.size());
                    Product p = data.getValue(Product.class);
                    productsList.add(p);
                }
                showList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showList() {
        ProductAdapter arrayAdapter = new ProductAdapter(this, R.layout.product_list_item, this.productsList);
        make_list_LSTVIEW_products.setAdapter(arrayAdapter);
        make_list_LSTVIEW_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView countView = (TextView)view.findViewById(R.id.productItem_LBL_count);
                Product p = productsList.get(i);
                Log.d("product",p.toString());
                Log.d("i", "i = "+i);
                int amnt = p.getAmount() != null ? p.getAmount() + 1 : 1;

                countView.setText(amnt + "");
                selectedProducts.put(p.getProdID(), p.setAmount(amnt));
                Log.d("selected", selectedProducts.toString());
            }
        });
    }
}