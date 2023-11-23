package com.inti.atv_assignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    private TextView nameTextView, emailTextView, phoneNumberTextView;
    private User user;
    private Button updateProfileBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nameTextView = view.findViewById(R.id.nameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        phoneNumberTextView = view.findViewById(R.id.phoneNumberTextView);
        updateProfileBtn = view.findViewById(R.id.updateBtn);
        user = LoginActivity.user;

        if (user.getName() == null || user.getName().equals("")) {
            nameTextView.setText("Name: Update your profile");
        } else {
            nameTextView.setText("Name: " + user.getName());
        }

        if (user.getEmail() == null || user.getEmail().equals("")) {
            emailTextView.setText("Email: Update your profile");
        } else {
            emailTextView.setText("Email: " + user.getEmail());
        }

        if (user.getPhoneNum() == null || user.getPhoneNum().equals("")) {
            phoneNumberTextView.setText("Phone Number: Update your profile");
        } else {
            phoneNumberTextView.setText("Phone Number: " + user.getPhoneNum());
        }


        updateProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent i = new Intent(requireContext(), EditProfileActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}