// DatabaseHelper.java

package com.inti.atv_assignment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "ATV_User.db";

    public DatabaseHelper(Context context) {
        super(context, "ATV_User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (" +
                "User_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name VARCHAR(255), " +
                "Username VARCHAR(255) UNIQUE, " +
                "Email VARCHAR(500) UNIQUE, " +
                "Phone_Num VARCHAR(255), " +
                "Password VARCHAR(500) NOT NULL, " +
                "Status VARCHAR(50) NOT NULL, " +
                "Role VARCHAR(50) NOT NULL" +
                ");");

        db.execSQL("CREATE TABLE Booking (" +
                "BookingID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "UserDBID INTEGER REFERENCES User(User_ID), " +
                "BookDate TEXT NOT NULL, " +
                "BookTime TEXT NOT NULL, " +
                "BookQuantity INTEGER NOT NULL," +
                "Status VARCHAR(255) NOT NULL, " +
                "PayableAmount DOUBLE NOT NULL, " +
                "Reason TEXT" +
                ");");

        db.execSQL("CREATE TABLE Reviews (" +
                "ReviewID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "User_ID INTEGER," +
                "Rating REAL," +
                "ReviewText TEXT," +
                "FOREIGN KEY (User_ID) REFERENCES User(User_ID)" +
                ");");

        db.execSQL( "CREATE TABLE First (" +
                "firstID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "input INTEGER NOT NULL " +
                ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table if it exists
        db.execSQL("DROP TABLE IF EXISTS ATV_User");
        db.execSQL("DROP TABLE IF EXISTS Booking");
        db.execSQL("DROP TABLE IF EXISTS Reviews");
        db.execSQL("DROP TABLE IF EXISTS First");
    }
}
