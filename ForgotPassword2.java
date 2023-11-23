package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword2 extends AppCompatActivity {

    private TextView usernameTextField;
    private EditText editTextTextPassword, editTextTextPassword2;
    private Button resetPasswordBtn;
    private DatabaseManager dbManager;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
        usernameTextField = findViewById(R.id.usernameTextField);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn);
        dbManager = new DatabaseManager(this);
        email = "";

        Intent i = getIntent();
        if (i != null){
            usernameTextField.setText("Your Username: " + i.getStringExtra("username"));
            email = i.getStringExtra("email");
        }

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editTextTextPassword.getText().toString().equals(editTextTextPassword2.getText().toString())){
                    Toast.makeText(ForgotPassword2.this, "Password do not match!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (dbManager.updateAccountPassword(email, editTextTextPassword2.getText().toString())){
                    Toast.makeText(ForgotPassword2.this, "Account successfully reset!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ForgotPassword2.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}