package com.inti.atv_assignment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AdminReviews extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<UserReview> reviews;
    private DatabaseManager dbManager;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_reviews, container, false);
        dbManager = new DatabaseManager(requireActivity());
        user = LoginActivity.user;

        try {
            dbManager.open();
        } catch (Exception e){
            e.printStackTrace();
        }

        recyclerView = view.findViewById(R.id.recyclerViewAdminReviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reviews = new ArrayList<UserReview>();

        reviews = dbManager.getAllReviews();
        dbManager.close();

        recyclerView.setAdapter(new ReviewAdapter(reviews));
        return view;
    }
}