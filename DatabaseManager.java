// DatabaseManager.java

package com.inti.atv_assignment;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public boolean createAccount(String username, String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username", username);
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        contentValues.put("Status", "Active");
        contentValues.put("Role", "User");

        long result = db.insert("User", null, contentValues);

        // Check if the data insertion was successful
        return result != -1;
    }

    public boolean createAdminAccount() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String username = "Admin";
        String email = "admin@gmail.com";
        String password = "admin123";

        contentValues.put("Username", username);
        contentValues.put("Email", email);
        contentValues.put("Password", password);
        contentValues.put("Status", "Active");
        contentValues.put("Role", "Admin");

        long result = db.insert("User", null, contentValues);

        // Check if the data insertion was successful
        return result != -1;
    }

    public boolean updateAccountPassword(String email, String newPassword) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Password", newPassword);

        int rowsAffected = db.update("User", contentValues, "Email = ?", new String[]{email});

        return rowsAffected > 0;
    }

    @SuppressLint("Range")
    public User getUserDetails(String email){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        User user = new User();

        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE Email = ?", new String[]{email});

        // Check if there is a result
        if (cursor.moveToFirst()) {
            user = new User(cursor.getString(cursor.getColumnIndex("Username")),
                    cursor.getString(cursor.getColumnIndex("Password")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getString(cursor.getColumnIndex("Email")),
                    cursor.getString(cursor.getColumnIndex("Role")),
                    cursor.getString(cursor.getColumnIndex("Status")),
                    cursor.getInt(cursor.getColumnIndex("User_ID")),
                    cursor.getString(cursor.getColumnIndex("Phone_Num")));
        }
        // Close the cursor to avoid memory leaks
        cursor.close();
        return user;
    }

    public boolean updateUserDetails(int userDBID, String name, String email, String phoneNum) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "UPDATE User SET Name = ?, Email = ?, Phone_Num = ? WHERE User_ID = ?";
        Object[] bindArgs = {name, email, phoneNum, userDBID};

        try {
            db.execSQL(sql, bindArgs);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            db.close();
            return false;
        }
    }


    public boolean bookATV(int userDBID, String bookDate, String bookTime, int quantity, double amount) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Log.i("DatabaseManagement", "bookATV(), BookTime: " + bookTime);
        Log.i("DatabaseManagement", "bookATV(), BookDate: " + bookDate);
        Log.i("DatabaseManagement", "bookATV(), User ID: " + userDBID);
        contentValues.put("UserDBID", userDBID);
        contentValues.put("BookDate", bookDate);
        contentValues.put("BookTime", bookTime);
        contentValues.put("BookQuantity", quantity);
        contentValues.put("Status", "PAID");
        contentValues.put("PayableAmount", amount);

        long result = db.insert("Booking", null, contentValues);

        return result != -1;
    }

    @SuppressLint("Range")
    public ArrayList<Booking> getAllBookings(int userDBID) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Booking> bookings = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss");

        Cursor cursor = db.rawQuery("SELECT * FROM Booking WHERE UserDBID = ? and Status = \'PAID\'", new String[]{String.valueOf(userDBID)});

        if (cursor.moveToFirst()) {
            do {
                Booking booking = new Booking();
                booking.setBookingDBID(cursor.getInt(cursor.getColumnIndex("BookingID")));
                try {
                    String date = cursor.getString(cursor.getColumnIndex("BookDate"));
                    String time = cursor.getString(cursor.getColumnIndex("BookTime"));

                    booking.setBookingDate(dateFormat.parse(date));
                    booking.setBookingTime(timeFormat.parse(time));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                booking.setQuantity(cursor.getInt(cursor.getColumnIndex("BookQuantity")));
                booking.setStatus(cursor.getString(cursor.getColumnIndex("Status")));
                booking.setPayableAmount(cursor.getDouble(cursor.getColumnIndex("PayableAmount")));

                bookings.add(booking);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bookings;
    }

    @SuppressLint("Range")
    public ArrayList<Booking> getAllBookings() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Booking> bookings = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss");

        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);

        Cursor cursor = db.rawQuery(
                "SELECT * FROM Booking",null);

        if (cursor.moveToFirst()) {
            do {
                Booking booking = new Booking();
                booking.setBookingDBID(cursor.getInt(cursor.getColumnIndex("BookingID")));
                try {
                    String date = cursor.getString(cursor.getColumnIndex("BookDate"));
                    String time = cursor.getString(cursor.getColumnIndex("BookTime"));

                    booking.setBookingDate(dateFormat.parse(date));
                    booking.setBookingTime(timeFormat.parse(time));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                booking.setQuantity(cursor.getInt(cursor.getColumnIndex("BookQuantity")));
                booking.setStatus(cursor.getString(cursor.getColumnIndex("Status")));
                booking.setPayableAmount(cursor.getDouble(cursor.getColumnIndex("PayableAmount")));
                booking.setReason(cursor.getString(cursor.getColumnIndex("Reason")));
                bookings.add(booking);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bookings;
    }

    public boolean isEmailExist(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE Email = ?", new String[]{email});

        boolean emailExists = cursor.getCount() > 0;

        // Close the cursor to avoid memory leaks
        cursor.close();

        return emailExists;
    }

    @SuppressLint("Range")
    public String getUsername(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String username = null;

        Cursor cursor = db.rawQuery("SELECT Username FROM User WHERE Email = ?", new String[]{email});

        // Check if there is a result
        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex("Username"));
        }

        // Close the cursor to avoid memory leaks
        cursor.close();

        return username;
    }


    public boolean isUsernameExist(String username){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE Username = ?", new String[]{username});

        boolean usernameExist = cursor.getCount() > 0;

        // Close the cursor to avoid memory leaks
        cursor.close();
        return usernameExist;
    }

    public boolean login(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE Email = ? AND Password = ?", new String[]{email, password});

        boolean emailPasswordMatch = cursor.getCount() > 0;

        // Close the cursor to avoid memory leaks
        cursor.close();

        return emailPasswordMatch;
    }

    public boolean saveReviewData(float rating, String ratingText, int userDBID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("User_ID", userDBID);
        values.put("Rating", rating);
        values.put("ReviewText", ratingText);

        long newRowId = db.insert("Reviews", null, values);

        // Check if the insert was successful
        boolean reviewSaved = newRowId != -1;

        return reviewSaved;
    }

    @SuppressLint("Range")
    public ArrayList<UserReview> getAllReviews() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<UserReview> reviews = new ArrayList<>();

        String query = "SELECT Reviews.ReviewText, Reviews.Rating, User.Username " +
                "FROM Reviews " +
                "INNER JOIN User ON Reviews.User_ID = User.User_ID";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                UserReview review = new UserReview();
                User user = new User();
                review.setText(cursor.getString(cursor.getColumnIndex("ReviewText")));
                review.setRating(cursor.getFloat(cursor.getColumnIndex("Rating")));
                user.setUsername(cursor.getString(cursor.getColumnIndex("Username")));
                review.setUser(user);

                reviews.add(review);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reviews;
    }

    public boolean cancelBooking(int bookingDBID, String reason) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Status", "CANCELLED");
        values.put("Reason", reason);

        int rowsAffected = db.update("Booking", values, "BookingID = ?", new String[]{String.valueOf(bookingDBID)});

        return rowsAffected > 0;
    }

    public boolean insertFirstTable (){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int input = 1;

        String rawQuery = "INSERT INTO First" +
                " (input) " +
                "VALUES (?)";

        SQLiteStatement statement = db.compileStatement(rawQuery);
        statement.bindLong(1, input);

        try {
            long rowId = statement.executeInsert();
            statement.close();
            db.close();
            return rowId != -1;
        } catch (Exception e) {
            e.printStackTrace();
            statement.close();
            db.close();
            return false;
        }
    }

    public int getFirstTableInput() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String rawQuery = "SELECT * " +
                " FROM First " +
                " WHERE firstID = 1;";

        Cursor cursor = db.rawQuery(rawQuery, null);

        int input = -1;

        if (cursor != null && cursor.moveToFirst()) {
            input = cursor.getInt(cursor.getColumnIndexOrThrow("input"));
            cursor.close();
        }

        db.close();
        return input;
    }

}
