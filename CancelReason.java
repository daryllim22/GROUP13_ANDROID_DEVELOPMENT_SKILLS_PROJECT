package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CancelReason extends AppCompatActivity {

    private Booking booking;
    private DatabaseManager dbManager;
    private Button confirmCancelBtn;
    private EditText reasonToCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reason);
        booking = (Booking) getIntent().getSerializableExtra("bookingDetails");
        dbManager = new DatabaseManager(this);

        reasonToCancel = findViewById(R.id.reasonToCancel);
        confirmCancelBtn = findViewById(R.id.confirmCancelBtn);

        confirmCancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                booking.setReason(reasonToCancel.getText().toString().trim());
                if (dbManager.cancelBooking(booking.getBookingDBID(), booking.getReason())){
                    Toast.makeText(getApplicationContext(), "Cancelled successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to cancel, something went wrong!", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(CancelReason.this, AdminMainActivity.class);
                startActivity(i);
            }
        });

    }
}