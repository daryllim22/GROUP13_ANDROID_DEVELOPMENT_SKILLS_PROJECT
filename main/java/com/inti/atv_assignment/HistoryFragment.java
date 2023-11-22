package com.inti.atv_assignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Booking> bookings;
    private DatabaseManager dbManager;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        dbManager = new DatabaseManager(requireActivity());
        user = LoginActivity.user;

        try {
            dbManager.open();
        } catch (Exception e){
            e.printStackTrace();
        }

        recyclerView = view.findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookings = new ArrayList<Booking>();

        bookings = dbManager.getAllBookings(user.getUserDBID());
        dbManager.close();

        recyclerView.setAdapter(new BookingAdapter(bookings));

        return view;
    }


}