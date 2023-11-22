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

public class AdminBooking extends Fragment implements BookingAdminInterface{

    private RecyclerView recyclerView;
    private ArrayList<Booking> bookings;
    private DatabaseManager dbManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_booking, container, false);
        dbManager = new DatabaseManager(requireActivity());
        bookings = new ArrayList<Booking>();


        recyclerView = view.findViewById(R.id.recyclerViewAdminBooking);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        bookings = dbManager.getAllBookings();
        dbManager.close();

        recyclerView.setAdapter(new BookingAdapterAdmin(bookings, this::onItemClick));
        return view;
    }

    @Override
    public void onItemClick(int position) {
        Booking booking = new Booking();
        booking.setBookingDate(bookings.get(position).getBookingDate());
        booking.setBookingTime(bookings.get(position).getBookingTime());
        booking.setStatus(bookings.get(position).getStatus());
        booking.setPayableAmount(bookings.get(position).getPayableAmount());
        booking.setQuantity(bookings.get(position).getQuantity());
        booking.setReason(bookings.get(position).getReason());
        booking.setBookingDBID(bookings.get(position).getBookingDBID());

        Intent i = new Intent(requireActivity(), BookingCancellation.class);
        i.putExtra("bookingDetails", booking);
        startActivity(i);
    }
}