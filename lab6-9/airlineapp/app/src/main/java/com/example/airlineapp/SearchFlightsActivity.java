package com.example.airlineapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SearchFlightsActivity extends AppCompatActivity {
    EditText from, to, date;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        from = findViewById(R.id.editFrom);
        to = findViewById(R.id.editTo);
        date = findViewById(R.id.editDate);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(this, FlightResultsActivity.class);
            intent.putExtra("from", from.getText().toString());
            intent.putExtra("to", to.getText().toString());
            intent.putExtra("date", date.getText().toString());
            startActivity(intent);
        });
    }
}
