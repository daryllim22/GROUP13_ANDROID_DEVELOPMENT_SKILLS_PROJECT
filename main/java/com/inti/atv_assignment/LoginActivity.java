package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.inti.atv_assignment.databinding.ActivityLoginBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    DatabaseManager dbManager;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbManager = new DatabaseManager(this);
        user = new User();

        int input = dbManager.getFirstTableInput();
        if (input != 1){
            //Creating account for admin for the first time
            //Email: admin@gmail.com
            //Password: admin123
            dbManager.createAdminAccount();
            dbManager.insertFirstTable();
        }

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString().trim();
                String password = binding.loginPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!dbManager.login(email, password)) {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    return;
                }

                user = dbManager.getUserDetails(email);

                if (user.getRole().equals("Admin")){
                    Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                    intent.putExtra("adminDetails", user);
                    startActivity(intent);
                } else if (user.getRole().equals("User")){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("userDetails", user);
                    startActivity(intent);
                }
            }
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.textViewForgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });


    }
}
