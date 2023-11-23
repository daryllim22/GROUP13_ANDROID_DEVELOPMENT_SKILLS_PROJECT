package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class ReceiptActivity extends AppCompatActivity {

    private Booking booking;
    private TextView dateTextView, timeTextView, amountTextView, paxTextView;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);
        amountTextView = findViewById(R.id.amountTextView);
        paxTextView = findViewById(R.id.paxTextView);
        backBtn = findViewById(R.id.backBtn); // Initialize backBtn

        booking = new Booking();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");

        Intent intent = getIntent();
        if (intent != null) {
            booking = (Booking) intent.getSerializableExtra("bookingDetails");
        }

        dateTextView.setText("Date: " + dateFormat.format(booking.getBookingDate()));
        timeTextView.setText("Time: " + timeFormat.format(booking.getBookingTime()));
        amountTextView.setText("Amount: RM " + booking.getPayableAmount());
        paxTextView.setText("No. of pax: " + booking.getQuantity());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReceiptActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}