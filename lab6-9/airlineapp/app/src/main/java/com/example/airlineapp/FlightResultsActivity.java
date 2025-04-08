package com.example.airlineapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FlightResultsActivity extends AppCompatActivity {
    TextView txtResults;
    Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_results);

        txtResults = findViewById(R.id.txtResults);
        btnBook = findViewById(R.id.btnBook);

        String from = getIntent().getStringExtra("from");
        String to = getIntent().getStringExtra("to");
        String date = getIntent().getStringExtra("date");

        txtResults.setText("Flight Found:\nFrom: " + from + "\nTo: " + to + "\nDate: " + date + "\nFlight No: AI-202\nPrice: $200");

        btnBook.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookingActivity.class);
            intent.putExtra("from", from);
            intent.putExtra("to", to);
            intent.putExtra("date", date);
            startActivity(intent);
        });
    }
}
