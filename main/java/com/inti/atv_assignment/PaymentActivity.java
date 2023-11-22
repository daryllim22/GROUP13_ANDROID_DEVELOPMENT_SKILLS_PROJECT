package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardNumberTextField, ccvTextField, monthTextField,
            yearTextField, nameTextField;
    private TextView amountPayableText;
    private Button payBtn;
    private Booking booking;
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cardNumberTextField = findViewById(R.id.cardNumberTextField);
        ccvTextField = findViewById(R.id.ccvTextField);
        monthTextField = findViewById(R.id.monthTextField);
        yearTextField = findViewById(R.id.yearTextField);
        nameTextField = findViewById(R.id.nameTextField);
        payBtn = findViewById(R.id.payBtn);
        amountPayableText = findViewById(R.id.amountPayableText);
        booking = new Booking();
        dbManager = new DatabaseManager(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss");

        Intent intent = getIntent();
        if (intent != null) {
            booking = (Booking) intent.getSerializableExtra("bookingDetails");
        }
        amountPayableText.setText("Amount Payable: RM: " + booking.getPayableAmount());

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (cardNumberTextField.getText().toString().trim().equals("") ||
                ccvTextField.getText().toString().trim().equals("") ||
                monthTextField.getText().toString().trim().equals("") ||
                yearTextField.getText().toString().trim().equals("") ||
                nameTextField.getText().toString().trim().equals("")) {
                    Toast.makeText(PaymentActivity.this, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String formattedTime = "", formattedDate = "";
                try {
                    formattedDate = dateFormat.format(booking.getBookingDate());
                    formattedTime = timeFormat.format(booking.getBookingTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }


                if (dbManager.bookATV(booking.getUserDBID(), formattedDate, formattedTime, booking.getQuantity(), booking.getPayableAmount())){
                    Toast.makeText(PaymentActivity.this, "Booked successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PaymentActivity.this, ReceiptActivity.class);
                    intent.putExtra("bookingDetails", booking);
                    startActivity(intent);
                } else {
                    Toast.makeText(PaymentActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}