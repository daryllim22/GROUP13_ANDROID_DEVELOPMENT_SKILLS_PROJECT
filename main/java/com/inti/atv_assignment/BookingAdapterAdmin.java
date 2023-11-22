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

public class BookingAdapterAdmin extends RecyclerView.Adapter<BookingAdapterAdmin.BookingAdapterAdminHolder>{
    private final BookingAdminInterface bookingAdminInterface;
    private ArrayList<Booking> bookings;

    public BookingAdapterAdmin(ArrayList<Booking> bookings, BookingAdminInterface bookingAdminInterface) {
        this.bookings = bookings;
        this.bookingAdminInterface = bookingAdminInterface;
    }

    @NonNull
    @Override
    public BookingAdapterAdmin.BookingAdapterAdminHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design_admin_booking, parent, false);
        return new BookingAdapterAdminHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapterAdmin.BookingAdapterAdminHolder holder, int position) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        holder.amountText.setText("Amount Payable: " + bookings.get(position).getPayableAmount());
        holder.noOfPax.setText("No. of pax: " + bookings.get(position).getQuantity());
        holder.status.setText("Status: " + bookings.get(position).getStatus());
        holder.reason.setText("Reason \n" + bookings.get(position).getReason());

        Date time = bookings.get(position).getBookingTime();
        Date date = bookings.get(position).getBookingDate();

        if (date != null) {
            holder.dateText.setText(dateFormat.format(date));
        } else {
            holder.dateText.setText("N/A");
        }

        if (time != null) {
            holder.timeText.setText(timeFormat.format(time));
        } else {
            holder.timeText.setText("Time: N/A");
        }
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    class BookingAdapterAdminHolder extends RecyclerView.ViewHolder {

        private TextView dateText, timeText, amountText, noOfPax, status, reason;
        public BookingAdapterAdminHolder (@NonNull View itemView){
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            timeText = itemView.findViewById(R.id.timeText);
            amountText = itemView.findViewById(R.id.amountText);
            noOfPax = itemView.findViewById(R.id.noOfPax);
            status = itemView.findViewById(R.id.status);
            reason = itemView.findViewById(R.id.reason);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    if (bookingAdminInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            bookingAdminInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
