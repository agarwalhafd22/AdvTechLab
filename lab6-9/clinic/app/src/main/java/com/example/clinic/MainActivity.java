package com.example.clinic;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn, registerBtn, viewAppointmentsBtn, viewDoctorsBtn;
    DatabaseHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        viewAppointmentsBtn = findViewById(R.id.viewAppointmentsBtn);
        viewDoctorsBtn = findViewById(R.id.viewDoctorsBtn);

        loginBtn.setOnClickListener(v -> {
            if (db.checkLogin(username.getText().toString(), password.getText().toString())) {
                Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                i.putExtra("user", username.getText().toString());
                startActivity(i);
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        registerBtn.setOnClickListener(v -> {
            if (db.registerUser(username.getText().toString(), password.getText().toString())) {
                Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
            }
        });

        viewAppointmentsBtn.setOnClickListener(v -> showAppointments());

        viewDoctorsBtn.setOnClickListener(v -> showAvailableDoctors());
    }

    private void showAppointments() {
        Cursor cursor = db.getAppointments();
        if (cursor.getCount() == 0) {
            showMessage("Appointments", "No appointments found.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (cursor.moveToNext()) {
            sb.append("ID: ").append(cursor.getInt(0)).append("\n")
                    .append("Patient: ").append(cursor.getString(1)).append("\n")
                    .append("Doctor ID: ").append(cursor.getInt(2)).append("\n")
                    .append("Date: ").append(cursor.getString(3)).append("\n\n");
        }
        showMessage("Appointments", sb.toString());
    }

    private void showAvailableDoctors() {
        Cursor cursor = db.getAvailableDoctors();
        if (cursor.getCount() == 0) {
            showMessage("Available Doctors", "No available doctors.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        while (cursor.moveToNext()) {
            sb.append("ID: ").append(cursor.getInt(0)).append("\n")
                    .append("Name: ").append(cursor.getString(1)).append("\n")
                    .append("Specialization: ").append(cursor.getString(2)).append("\n\n");
        }
        showMessage("Available Doctors", sb.toString());
    }

    private void showMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}