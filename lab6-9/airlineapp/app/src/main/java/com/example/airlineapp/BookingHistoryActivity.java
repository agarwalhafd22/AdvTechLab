package com.example.airlineapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookingHistoryActivity extends AppCompatActivity {
    TextView history;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);
          history=findViewById(R.id.txtHistory);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<String> bookings = dbHelper.getAllBookings();

        StringBuilder sb = new StringBuilder("Previous Bookings:\n");
        for (int i = 0; i < bookings.size(); i++) {
            sb.append(i + 1).append(". ").append(bookings.get(i)).append("\n");
        }

        history.setText(sb.toString());
    }
}
