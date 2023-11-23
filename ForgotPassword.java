package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {
    private EditText forgotEmailTextField;
    private Button proceedToCheckUsernameBtn;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgotEmailTextField = findViewById(R.id.forgotEmailTextField);
        proceedToCheckUsernameBtn = findViewById(R.id.resetPasswordBtn);
        dbManager = new DatabaseManager (this);

        proceedToCheckUsernameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbManager.isEmailExist(forgotEmailTextField.getText().toString().trim())) {
                    Intent i = new Intent(ForgotPassword.this, ForgotPassword2.class);
                    i.putExtra("username", dbManager.getUsername(forgotEmailTextField.getText().toString().trim()));
                    i.putExtra("email", forgotEmailTextField.getText().toString().trim());
                    startActivity(i);
                } else {
                    Toast.makeText(ForgotPassword.this, "No such email exist in the system!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}