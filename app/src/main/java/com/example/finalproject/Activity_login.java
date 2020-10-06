package com.example.finalproject;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_login extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference usersRef = database.getReference("Users");
    EditText login_TXT_userName;
    EditText login_TXT_password;
    Button login_BTN_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        initLoginBTN();
        requestPermissions();

    }

    private void initLoginBTN() {
        login_BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = login_TXT_userName.getText().toString();
                String password = login_TXT_password.getText().toString();
                checkUsernameAndPassword(userName,password);
            }
        });
    }

    private void checkUsernameAndPassword(final String username, final String password){
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> users = snapshot.getChildren();
                for(DataSnapshot data : users){
                    User u = data.getValue(User.class);
                    if(u.getUserName().equals(username))
                        if(u.getPassword().equals(password))
                            openSupermarketSelectActivity();
                }
                Log.d("pttt", "error");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void openSupermarketSelectActivity(){
        Intent i = new Intent(Activity_login.this,Activity_Supermarket_Select.class);
        startActivity(i);
        finish();
    }

    private void findViews() {
        login_TXT_userName=findViewById(R.id.login_TXT_userName);
        login_TXT_password=findViewById(R.id.login_TXT_password);
        login_BTN_login=findViewById(R.id.login_BTN_login);
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                20
        );
    }
}
