package com.inti.atv_assignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>{
    private ArrayList<UserReview> reviews;

    public ReviewAdapter(ArrayList<UserReview> reviews) {
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design_admin_review, parent, false);
        return new ReviewAdapter.ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
        holder.reviewRate.setText("" + reviews.get(position).getRating());
        holder.username.setText(reviews.get(position).getUser().getUsername() + "\n");
        holder.reviewText.setText("Review \n" + reviews.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        private TextView reviewRate, username, reviewText;
        public ReviewViewHolder (@NonNull View itemView){
            super(itemView);
            reviewRate = itemView.findViewById(R.id.reviewRate);
            username = itemView.findViewById(R.id.username);
            reviewText = itemView.findViewById(R.id.reviewText);
        }
    }
}
