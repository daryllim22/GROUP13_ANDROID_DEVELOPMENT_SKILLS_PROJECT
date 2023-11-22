package com.inti.atv_assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder>{

    private ArrayList<Booking> bookings;

    public BookingAdapter(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public BookingAdapter.BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.BookingViewHolder holder, int position) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date date = bookings.get(position).getBookingDate();
        Date time = bookings.get(position).getBookingTime();

        holder.dateText.setText(dateFormat.format(date));
        holder.timeText.setText("Time: " + timeFormat.format(time));
        holder.amountText.setText("Payable amount: RM " + bookings.get(position).getPayableAmount());
        holder.noOfPax.setText("No. of pax: " + bookings.get(position).getQuantity());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    class BookingViewHolder extends RecyclerView.ViewHolder {

        private TextView dateText, timeText, amountText, noOfPax;
        public BookingViewHolder (@NonNull View itemView){
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            timeText = itemView.findViewById(R.id.timeText);
            amountText = itemView.findViewById(R.id.amountText);
            noOfPax = itemView.findViewById(R.id.noOfPax);
        }
    }

}
