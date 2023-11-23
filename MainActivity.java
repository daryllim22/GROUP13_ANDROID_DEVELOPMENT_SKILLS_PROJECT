package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.os.Bundle;

import com.inti.atv_assignment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = new User();

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        Intent intent = getIntent();
        if (intent.hasExtra("userDetails")) {
            user = (User) intent.getSerializableExtra("userDetails");
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.atv) {     //Booking
                replaceFragment(new ATVFragment());
            } else if (item.getItemId() == R.id.profile) { //Profile
                replaceFragment(new ProfileFragment());
            } else if (item.getItemId() == R.id.history) { //History
                replaceFragment(new HistoryFragment());
            } else if (item.getItemId() == R.id.logout){
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
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