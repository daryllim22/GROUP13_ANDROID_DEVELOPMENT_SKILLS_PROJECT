package com.inti.atv_assignment;

import java.io.Serializable;
import java.util.Date;

import javax.crypto.SecretKey;

public class Booking implements Serializable {
    private int bookingDBID;
    private int userDBID;
    private Date bookingDate;
    private Date bookingTime;
    private int quantity;
    private String status;
    private double payableAmount;
    private String reason;

    //Constructor
    public Booking(int bookingDBID, int userDBID, Date bookingDate, Date bookingTime, int quantity, String status, double payableAmount, String reason) {
        this.bookingDBID = bookingDBID;
        this.userDBID = userDBID;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.quantity = quantity;
        this.status = status;
        this.payableAmount = payableAmount;
        this.reason = reason;
    }

    //Default Constructor
    public Booking(){
        this.bookingDBID = 0;
        this.userDBID = 0;
        this.bookingDate = new Date();
        this.bookingTime = new Date();
        this.quantity = 0;
        this.status = "";
        this.payableAmount = 0.0;
        this.reason = "";
    }

    //Getters
    public int getBookingDBID() {
        return bookingDBID;
    }
    public int getUserDBID() {return this.userDBID;}

    public Date getBookingDate() {
        return bookingDate;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
    public double getPayableAmount() {return payableAmount;}
    public String getReason() {return reason;}

    //Setters
    public void setUserDBID(int userDBID){this.userDBID = userDBID;}
    public void setBookingDBID(int bookingDBID) {
        this.bookingDBID = bookingDBID;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setPayableAmount(double payableAmount) {this.payableAmount = payableAmount;}
    public void setReason(String reason){this.reason = reason;}

    //Other functions
    public double calculatePayableAmount(){
        return this.quantity * 60;
    }
}
