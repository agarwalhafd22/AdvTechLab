package com.example.clinic;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    DatabaseHelper db;
    ListView doctorList;
    EditText dateInput;
    Button bookBtn;
    String selectedDoctor = "";
    String user;
    ArrayList<Doctor> doctorListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        db = new DatabaseHelper(this);
        doctorList = findViewById(R.id.doctorList);
        dateInput = findViewById(R.id.dateInput);
        bookBtn = findViewById(R.id.bookBtn);
        user = getIntent().getStringExtra("user");

        // Load doctors from DB
        Cursor c = db.getAvailableDoctors();
        doctorListData = new ArrayList<>();
        while (c.moveToNext()) {
            String id = c.getString(0);       // Doctor ID
            String name = c.getString(1);     // Doctor Name
            doctorListData.add(new Doctor(id, name));
        }

        ArrayAdapter<Doctor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, doctorListData);
        doctorList.setAdapter(adapter);
        doctorList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        doctorList.setOnItemClickListener((adapterView, view, i, l) -> {
            selectedDoctor = doctorListData.get(i).id;
        });

        bookBtn.setOnClickListener(v -> {
            String date = dateInput.getText().toString();
            if (!selectedDoctor.isEmpty() && !date.isEmpty()) {
                if (!isValidDate(date)) {
                    Toast.makeText(this, "Please enter date in YYYY-MM-DD format", Toast.LENGTH_SHORT).show();
                } else if (db.bookAppointment(user, Integer.parseInt(selectedDoctor), date)) {
                    Toast.makeText(this, "Appointment booked!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Booking failed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please select a doctor and enter a date", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    // Doctor model class
    static class Doctor {
        String id;
        String name;

        Doctor(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name; // shown in ListView
        }
    }
}
