package com.inti.atv_assignment;

public class UserReview {
    private float rating;
    private String text;
    private User user;

    //Constructor
    public UserReview(float rating, String text, User user) {
        this.rating = rating;
        this.text = text;
        this.user = user;
    }

    //Default Constructor
    public UserReview(){
        this.rating = 0;
        this.text = "";
        this.user = new User();
    }

    //Getters
    public float getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    //Setters
    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
