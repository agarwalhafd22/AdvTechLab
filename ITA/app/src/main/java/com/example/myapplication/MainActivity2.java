package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    double cost = 0; // Stores total cost
    TextView text3; // Reference to total cost TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find CheckBoxes
        CheckBox cb1 = findViewById(R.id.checkbox1);
        CheckBox cb2 = findViewById(R.id.checkbox2);
        CheckBox cb3 = findViewById(R.id.checkbox3);

        // Find quantity TextViews
        TextView q1 = findViewById(R.id.q1);
        TextView q2 = findViewById(R.id.q2);
        TextView q3 = findViewById(R.id.q3);

        // Find total cost TextView
        text3 = findViewById(R.id.text3);

        // Set listeners for checkboxes
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTotalCost(cb1, q1, 60);
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTotalCost(cb2, q2, 50);
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateTotalCost(cb3, q3, 40);
            }
        });
    }

    // Function to update cost dynamically
    private void updateTotalCost(CheckBox cb, TextView quantityTextView, int pricePerUnit) {
        if (cb.isChecked()) {
            try {
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                cost += pricePerUnit * quantity;
            } catch (NumberFormatException e) {
                quantityTextView.setText("1"); // Set default quantity if empty
                cost += pricePerUnit; // Assume quantity 1
            }
        } else {
            try {
                int quantity = Integer.parseInt(quantityTextView.getText().toString());
                cost -= pricePerUnit * quantity;
            } catch (NumberFormatException e) {
                cost -= pricePerUnit;
            }
        }

        // Update the total cost text
//        text3.setText(String.valueOf(cost));
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Is order Final?");
        if(cost>5000){
            cost=cost * (.9);
        }
        alert.setMessage("Total Cost is   :" + String.valueOf(cost) );
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Closes the dialog

            }
        });
        alert.setNegativeButton("No ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Please choose Again",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();

    }
}
