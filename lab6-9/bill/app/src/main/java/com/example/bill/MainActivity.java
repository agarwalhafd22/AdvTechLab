package com.example.bill;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DBHelper db;
    TextView tvBudget, tvLimit;
    Button btnUpdate, btnAddExpense, btnViewExpenses, btnSetReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        tvBudget = findViewById(R.id.tvBudget);
        tvLimit = findViewById(R.id.tvLimit);
        btnUpdate = findViewById(R.id.btnUpdateBudget);
        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnViewExpenses = findViewById(R.id.btnViewExpenses);
        btnSetReminder = findViewById(R.id.btnReminder);

        loadBudget();

        btnUpdate.setOnClickListener(v -> startActivity(new Intent(this, UpdateBudgetActivity.class)));
        btnAddExpense.setOnClickListener(v -> startActivity(new Intent(this, AddExpenseActivity.class)));
        btnViewExpenses.setOnClickListener(v -> startActivity(new Intent(this, ExpenseListActivity.class)));
        btnSetReminder.setOnClickListener(v -> startActivity(new Intent(this, ReminderActivity.class)));
    }

    private void loadBudget() {
        Cursor cursor = db.getBudget();
        if (cursor.moveToFirst()) {
            tvBudget.setText("Budget: Rs. " + cursor.getDouble(1));
            tvLimit.setText("Limit: Rs. " + cursor.getDouble(2));
        }
    }
}