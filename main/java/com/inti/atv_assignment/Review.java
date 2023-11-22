package com.inti.atv_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Review extends AppCompatActivity {
    TextView tvReviews;
    RatingBar rbStars;
    EditText reviewText;
    Button sendReviewBtn, backReviewBtn;
    private UserReview userReview;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        userReview = new UserReview();
        userReview.setUser(LoginActivity.user);
        dbManager = new DatabaseManager(this);

        tvReviews = findViewById(R.id.tvReviews);
        rbStars = findViewById(R.id.rbStars);
        reviewText = findViewById(R.id.reviewText);
        sendReviewBtn = findViewById(R.id.sendReviewBtn);
        backReviewBtn = findViewById(R.id.backReviewBtn);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    tvReviews.setText("Very Dissatisfied");
                } else if (rating == 1) {
                    tvReviews.setText("Dissatisfied");
                } else if (rating >= 2 && rating <= 3) {
                    tvReviews.setText("OK");
                } else if (rating == 4) {
                    tvReviews.setText("Satisfied");
                } else if (rating == 5) {
                    tvReviews.setText("Very Satisfied");
                }
            }
        });

        sendReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userReview.setText(reviewText.getText().toString().trim());
                userReview.setRating(rbStars.getRating());

                if (dbManager.saveReviewData(userReview.getRating(), userReview.getText(), userReview.getUser().getUserDBID())){
                    Toast.makeText(Review.this, "Review has sent to us successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Review.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Review.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Review.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
