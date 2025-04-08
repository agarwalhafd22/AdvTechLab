package com.example.bill;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddExpenseActivity extends AppCompatActivity {
    EditText etAmount, etDescription;
    Spinner spCategory;
    Button btnAdd;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        etAmount = findViewById(R.id.etAmount);
        etDescription = findViewById(R.id.etDescription);
        spCategory = findViewById(R.id.spCategory);
        btnAdd = findViewById(R.id.btnAdd);
        db = new DBHelper(this);

        btnAdd.setOnClickListener(v -> {
            double amount = Double.parseDouble(etAmount.getText().toString());
            String description = etDescription.getText().toString();
            String category = spCategory.getSelectedItem().toString();
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            db.addExpense(category, description, amount, date);
            finish();
        });
    }
}

