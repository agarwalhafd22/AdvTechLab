package com.example.diksha_colorchange;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Spinner backgroundSpinner;
    private Button changeButton;
    private LinearLayout backgroundLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundSpinner = findViewById(R.id.spinner);
        changeButton = findViewById(R.id.button);
        backgroundLayout = findViewById(R.id.linear);

        // Handling system insets (status bar, navigation bar, etc.)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the Spinner with background color options
        String[] backgroundOptions = {"Red", "Green", "Blue", "Yellow"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, backgroundOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(adapter);

        // Set Button functionality to change the background color based on spinner selection
        changeButton.setOnClickListener(v -> {
            String selectedBackground = backgroundSpinner.getSelectedItem().toString();
            changeBackgroundColor(selectedBackground);
        });
    }

    // Change the background color based on selected option
    private void changeBackgroundColor(String color) {
        int backgroundColor;

        switch (color) {
            case "Red":
                backgroundColor = getResources().getColor(android.R.color.holo_red_dark);
                break;
            case "Green":
                backgroundColor = getResources().getColor(android.R.color.holo_green_dark);
                break;
            case "Blue":
                backgroundColor = getResources().getColor(android.R.color.holo_blue_dark);
                break;
            case "Yellow":
                backgroundColor = getResources().getColor(android.R.color.holo_orange_light);
                break;
            default:
                backgroundColor = getResources().getColor(android.R.color.white);  // Default color
        }

        backgroundLayout.setBackgroundColor(backgroundColor);
    }
}
