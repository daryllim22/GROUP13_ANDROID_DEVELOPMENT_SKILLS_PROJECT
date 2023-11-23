package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.inti.atv_assignment.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbManager = new DatabaseManager(this);

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString().trim();
                String username = binding.editTextUsername.getText().toString().trim();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    return;
                } 
                
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (dbManager.isEmailExist(email)) {
                    Toast.makeText(SignUpActivity.this, "Email already exists, Please try another email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dbManager.isUsernameExist(username)){
                    Toast.makeText(SignUpActivity.this, "Username already exists, Please try another one", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dbManager.createAccount(username, email, password)) {
                    Toast.makeText(SignUpActivity.this, "Signup Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(SignUpActivity.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
