package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class BookingCancellation extends AppCompatActivity {
    private Booking booking;
    private TextView dateCancelTextView, timeCancelTextView, amountCancelTextView,
            noOfPaxCancelTextView, statusCancelTextView, reasonCancelTextView;
    private Button cancelBookingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_cancellation);
        booking = (Booking) getIntent().getSerializableExtra("bookingDetails");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");

        dateCancelTextView = findViewById(R.id.dateCancelTextView);
        timeCancelTextView = findViewById(R.id.timeCancelTextView);
        amountCancelTextView = findViewById(R.id.amountCancelTextView);
        noOfPaxCancelTextView = findViewById(R.id.noOfPaxCancelTextView);
        statusCancelTextView = findViewById(R.id.statusCancelTextView);
        reasonCancelTextView = findViewById(R.id.reasonCancelTextView);

        cancelBookingBtn = findViewById(R.id.cancelBookingBtn);

        dateCancelTextView.setText(dateFormat.format(booking.getBookingDate()));
        timeCancelTextView.setText("Time: " + timeFormat.format(booking.getBookingTime()));
        amountCancelTextView.setText("Amount: " + booking.getPayableAmount());
        noOfPaxCancelTextView.setText("No. of pax: " + booking.getQuantity());
        statusCancelTextView.setText("Status: " + booking.getStatus());
        reasonCancelTextView.setText(booking.getReason());

        if (booking.getStatus().equals("CANCELLED")){
            cancelBookingBtn.setVisibility(View.INVISIBLE);
        } else {
            cancelBookingBtn.setVisibility(View.VISIBLE);
        }

        cancelBookingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(BookingCancellation.this, CancelReason.class);
                i.putExtra("bookingDetails", booking);
                startActivity(i);
            }
        });

    }
}