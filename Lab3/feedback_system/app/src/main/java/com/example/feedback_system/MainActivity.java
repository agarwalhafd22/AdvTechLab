package com.example.feedback_system;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button submitButton;
    private RatingBar ratingBar;
    private EditText  feedbackEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ratingBar = findViewById(R.id.ratingBar3);
        submitButton = findViewById(R.id.button);
        feedbackEditText = findViewById(R.id.editTextText);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = feedbackEditText.getText().toString().trim();
                float rating = ratingBar.getRating();
                if ( feedback.isEmpty() || rating == 0) {
                    Toast.makeText(MainActivity.this, "Please fill out all fields and rate your experience", Toast.LENGTH_SHORT).show();
                } else {
                    // Display toast message
                    String message = "Feedback Submitted!\nRating: " + rating + "\nFeedback: " + feedback;
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                    // Optionally, clear the fields after submission

                    feedbackEditText.setText("");
                    ratingBar.setRating(0); // Reset the rating bar
                }
            }



        });
    }
}