package com.example.airlineapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {
    EditText name, seat;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        name = findViewById(R.id.editName);
        seat = findViewById(R.id.editSeat);
        btnConfirm = findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(v -> {
            String passenger = name.getText().toString();
            String seatNumber = seat.getText().toString();
            String flightInfo = getIntent().getStringExtra("from") + " to " +
                    getIntent().getStringExtra("to") + " on " +
                    getIntent().getStringExtra("date");

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            dbHelper.addBooking(passenger, seatNumber, flightInfo);

            Toast.makeText(this, "Booking Confirmed for " + passenger, Toast.LENGTH_LONG).show();
        });

    }
}
