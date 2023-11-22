package com.inti.atv_assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.inti.atv_assignment.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        if (LoginActivity.user.getRole().equals("Admin")){
            binding.reviewBtn.setVisibility(View.INVISIBLE);
            binding.reviewTextView.setVisibility(View.INVISIBLE);
        } else {
            binding.reviewBtn.setVisibility(View.VISIBLE);
            binding.reviewTextView.setVisibility(View.VISIBLE);
        }

        binding.reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), Review.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
