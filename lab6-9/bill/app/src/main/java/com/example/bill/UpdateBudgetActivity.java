package com.example.bill;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateBudgetActivity extends AppCompatActivity {
    EditText etBudget, etLimit;
    Button btnSave;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_budget);

        etBudget = findViewById(R.id.etBudget);
        etLimit = findViewById(R.id.etLimit);
        btnSave = findViewById(R.id.btnSaveBudget);
        db = new DBHelper(this);

        btnSave.setOnClickListener(v -> {
            double budget = Double.parseDouble(etBudget.getText().toString());
            double limit = Double.parseDouble(etLimit.getText().toString());
            db.updateBudget(budget, limit);
            finish();
        });
    }
}