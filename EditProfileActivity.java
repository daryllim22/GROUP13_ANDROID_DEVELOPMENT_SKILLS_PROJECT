package com.inti.atv_assignment;

import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    private User user;
    private EditText nameEditText, editTextTextEmailAddress, editTextPhone;
    private Button updateBtn;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nameEditText = findViewById(R.id.nameEditText);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        updateBtn = findViewById(R.id.updateBtn);

        user = new User();
        user = LoginActivity.user;
        dbManager = new DatabaseManager(this);

        if (user.getName() != null){
            nameEditText.setText(user.getName());
        }
        if (user.getEmail() != null) {
            editTextTextEmailAddress.setText(user.getEmail());
        }
        if (user.getPhoneNum() != null){
            editTextPhone.setText(user.getPhoneNum());
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (dbManager.updateUserDetails(user.getUserDBID(),
                        nameEditText.getText().toString().trim(),
                        editTextTextEmailAddress.getText().toString().trim(),
                        editTextPhone.getText().toString().trim())){
                    Toast.makeText(EditProfileActivity.this, "Details updated successfully!", Toast.LENGTH_SHORT).show();
                    LoginActivity.user.setName(nameEditText.getText().toString().trim());
                    LoginActivity.user.setEmail(editTextTextEmailAddress.getText().toString().trim());
                    LoginActivity.user.setEmail(editTextPhone.getText().toString().trim());
                    Intent i = new Intent(EditProfileActivity.this, MainActivity.class);
                    startActivity(i);
                    return;
                }
                Toast.makeText(EditProfileActivity.this, "Failed to update!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}