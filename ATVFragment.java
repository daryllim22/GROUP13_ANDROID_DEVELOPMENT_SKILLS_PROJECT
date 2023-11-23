package com.inti.atv_assignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ATVFragment extends Fragment {

    private User user;
    private Booking booking;
    private CalendarView datePicker;
    private Button bookBtn;
    private EditText quantityTextField;
    private String selectedDate;
    private DatabaseManager dbManager;
    private Spinner timePicker;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_atv, container, false);
        user = LoginActivity.user;
        Log.i("ATV Fragment", "User DBID: " + user.getUserDBID());
        booking = new Booking();
        selectedDate = "";
        dbManager = new DatabaseManager(requireContext());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss");

        datePicker = view.findViewById(R.id.datePicker);
        bookBtn = view.findViewById(R.id.bookBtn);
        quantityTextField = view.findViewById(R.id.quantityTextField);
        timePicker = view.findViewById(R.id.timePicker);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (int hour = 5; hour <= 18; hour++) {
            String formattedTime = String.format("%02d:00", hour);
            adapter.add(formattedTime);
        }
        timePicker.setAdapter(adapter);

        Calendar todayCalendar = Calendar.getInstance();
        long todayInMillis = todayCalendar.getTimeInMillis();
        datePicker.setDate(todayInMillis, true, true);

        datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = String.format("%02d-%s-%04d", dayOfMonth, getMonthShortName(month + 1), year);
            }
        });

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String input = quantityTextField.getText().toString().trim();

                //Validating quantity
                if (input.isEmpty()){
                    Toast.makeText(requireContext(), "Quantity is empty!", Toast.LENGTH_SHORT).show();
                }
                if (!input.isEmpty()) {
                    try {
                        int quantity = Integer.parseInt(input);
                        if (quantity < 0) {
                            Toast.makeText(requireContext(), "Please enter a positive quantity!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(requireContext(), "Invalid input. Please enter a valid number!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                //Validating Date
                if (!selectedDate.isEmpty()) {
                    Calendar todayCalendar = Calendar.getInstance();
                    Calendar selectedCalendar = Calendar.getInstance();


                    String[] dateParts = selectedDate.split("-");
                    int dayOfMonth = Integer.parseInt(dateParts[0]);
                    String monthAbbreviation = dateParts[1];
                    int year = Integer.parseInt(dateParts[2]);

                    int month = getMonthIndex(monthAbbreviation);

                    selectedCalendar.set(year, month, dayOfMonth);

                    if (selectedCalendar.before(todayCalendar)) {
                        Toast.makeText(requireContext(), "Please select a future date.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(requireContext(), "Please select a date.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] dateParts = selectedDate.split("-");
                int dayOfMonth = Integer.parseInt(dateParts[0]);
                String monthAbbreviation = dateParts[1];
                int year = Integer.parseInt(dateParts[2]);

                int month = getMonthIndex(monthAbbreviation);
                String dateSelected = dayOfMonth + "-" + month + "-" + year;

                //Setting booking up
                booking.setQuantity(Integer.parseInt(quantityTextField.getText().toString().trim()));
                booking.setStatus("OPEN");
                booking.setUserDBID(user.getUserDBID());
                booking.setPayableAmount(booking.calculatePayableAmount());

                try {
                    Log.i("ATV Fragment", "Selected Date: " + dateSelected);
                    booking.setBookingDate(dateFormat.parse(dateSelected));
                    String time = timePicker.getSelectedItem().toString() + ":00";
                    booking.setBookingTime(timeFormat.parse(time));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                //Direct to payment page
                Intent i = new Intent(getActivity(), PaymentActivity.class);
                i.putExtra("bookingDetails", booking);
                startActivity(i);
            }
        });

        return view;
    }

    private String getMonthShortName(int month) {
        String[] shortMonthNames = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        return shortMonthNames[month - 1];
    }


    private int getMonthIndex(String monthAbbreviation) {
        String[] shortMonthNames = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        for (int i = 0; i < shortMonthNames.length; i++) {
            if (shortMonthNames[i].equals(monthAbbreviation)) {
                return i;
            }
        }
        return 0;
    }



}