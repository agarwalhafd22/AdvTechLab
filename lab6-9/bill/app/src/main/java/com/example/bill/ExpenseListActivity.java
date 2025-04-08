
package com.example.bill;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ExpenseListActivity extends AppCompatActivity {
    DBHelper db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        listView = findViewById(R.id.listView);
        db = new DBHelper(this);

        Cursor cursor = db.getExpenses();
        ArrayList<String> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            String item = cursor.getString(4) + " - " + cursor.getString(1) + ": Rs. " + cursor.getDouble(3);
            list.add(item);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}