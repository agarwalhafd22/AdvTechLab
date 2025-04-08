package com.example.airlineapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AirlineDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKINGS = "bookings";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SEAT = "seat";
    private static final String COLUMN_FLIGHT = "flight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOKINGS_TABLE = "CREATE TABLE " + TABLE_BOOKINGS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SEAT + " TEXT, " +
                COLUMN_FLIGHT + " TEXT)";
        db.execSQL(CREATE_BOOKINGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        onCreate(db);
    }

    public void addBooking(String name, String seat, String flightInfo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_SEAT, seat);
        values.put(COLUMN_FLIGHT, flightInfo);
        db.insert(TABLE_BOOKINGS, null, values);
        db.close();
    }

    public ArrayList<String> getAllBookings() {
        ArrayList<String> bookings = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKINGS, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String seat = cursor.getString(2);
                String flight = cursor.getString(3);
                bookings.add("Passenger: " + name + " | Seat: " + seat + " | Flight: " + flight);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookings;
    }
}
