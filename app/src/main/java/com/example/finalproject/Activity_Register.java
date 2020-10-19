package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_Register extends AppCompatActivity {
    private static final String TAG = "register TAG";
    private Button register_BTN_register;
    private EditText register_EDT_email;
    private EditText register_EDT_password;
    private EditText register_EDT_repassword;
    private TextView register_TXT_message;
    private FirebaseAuth mAuth;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        findViews();
        setButtons();
    }

    private void setButtons() {
        register_BTN_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        String email = register_EDT_email.getText().toString();
        String password = register_EDT_password.getText().toString();
        String rePassword = register_EDT_repassword.getText().toString();
        if (password.equals(rePassword)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(Activity_Register.this, "Welcome " + register_EDT_email.getText().toString(),
                                        Toast.LENGTH_LONG).show();
                                updateUI("Welcome " + register_EDT_email.getText().toString());
                                Handler myHandler = new Handler();
                                myHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Activity_Register.this, "Welcome " + register_EDT_email.getText().toString(),
                                                Toast.LENGTH_LONG).show();
                                        updateUI("Welcome " + register_EDT_email.getText().toString());
                                        finish();
                                    }
                                }, 1050);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.d(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Activity_Register.this, "Authentication failed.\n " + task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                                updateUI(task.getException().getMessage());
                            }
                        }
                    });
        } else {
            register_EDT_password.setBackgroundColor(Color.RED);
            register_EDT_repassword.setBackgroundColor(Color.RED);
            updateUI("Not equal passwords!");
        }

    }

    private void updateUI(String text) {
        register_TXT_message.setVisibility(View.VISIBLE);
        register_TXT_message.setText(text);
    }

    private void findViews() {
        register_BTN_register = findViewById(R.id.register_BTN_register);
        register_EDT_email = findViewById(R.id.register_EDT_email);
        register_EDT_password = findViewById(R.id.register_EDT_password);
        register_EDT_repassword = findViewById(R.id.register_EDT_repassword);
        register_TXT_message = findViewById(R.id.register_TXT_message);
    }


}