package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.inti.atv_assignment.databinding.ActivityAdminMainBinding;
import com.inti.atv_assignment.databinding.ActivityMainBinding;

public class AdminMainActivity extends AppCompatActivity {

    ActivityAdminMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdminMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = new User();

        replaceFragment(new HomeFragment());
        binding.bottomNavigationViewAdmin.setBackground(null);

        Intent intent = getIntent();
        if (intent.hasExtra("userDetails")) {
            user = (User) intent.getSerializableExtra("userDetails");
        }

        binding.bottomNavigationViewAdmin.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {                //Home
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.bookings) {     //Booking
                replaceFragment(new AdminBooking());
            } else if (item.getItemId() == R.id.reviews) {      //Reviews
                replaceFragment(new AdminReviews());
            } else if (item.getItemId() == R.id.logout){
                Intent i = new Intent(AdminMainActivity.this, LoginActivity.class);
                startActivity(i);
            }
            return true;
        });


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}