// --- MainActivity.java ---
package com.example.airlineapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnSearchFlights, btnBookingHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearchFlights = findViewById(R.id.btnSearchFlights);
        btnBookingHistory = findViewById(R.id.btnBookingHistory);

        btnSearchFlights.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SearchFlightsActivity.class));
        });

        btnBookingHistory.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, BookingHistoryActivity.class));
        });
    }
}
