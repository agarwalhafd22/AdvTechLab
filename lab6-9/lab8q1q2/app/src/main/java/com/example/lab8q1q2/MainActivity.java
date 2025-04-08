package com.example.lab8q1q2;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText nameInput, priceInput, qtyInput;
    Button addButton, showButton, maxMinButton,summaryButton;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        priceInput = findViewById(R.id.priceInput);
        qtyInput = findViewById(R.id.qtyInput);
        addButton = findViewById(R.id.addButton);
        showButton = findViewById(R.id.showButton);
        maxMinButton = findViewById(R.id.maxMinButton);
         summaryButton = findViewById(R.id.summaryButton);

        db = new DatabaseHelper(this);

        addButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            double price = Double.parseDouble(priceInput.getText().toString());
            int qty = Integer.parseInt(qtyInput.getText().toString());

            boolean inserted = db.insertProduct(new Product(name, price, qty));
            Toast.makeText(this, inserted ? "Product Added!" : "Failed", Toast.LENGTH_SHORT).show();
        });

        showButton.setOnClickListener(v -> {
            Cursor cursor = db.getAllProducts();
            if (cursor.getCount() == 0) {
                showMessage("No Data", "No products found.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                double price = cursor.getDouble(1);
                int qty = cursor.getInt(2);
                sb.append("Name: ").append(name)
                        .append("\nPrice: ").append(price)
                        .append("\nQuantity: ").append(qty)
                        .append("\nTotal: ").append(price * qty)
                        .append("\n\n");
            }
            showMessage("Products", sb.toString());
        });
        summaryButton.setOnClickListener(v -> {
            Cursor cursor = db.getAllProducts();
            if (cursor.getCount() == 0) {
                showMessage("No Data", "No products to bill.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            double grandTotal = 0;

            sb.append("BILL SUMMARY\n\n");

            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                double price = cursor.getDouble(1);
                int qty = cursor.getInt(2);
                double total = price * qty;
                grandTotal += total;

                sb.append("Product: ").append(name)
                        .append("\nPrice: ₹").append(price)
                        .append("\nQuantity: ").append(qty)
                        .append("\nTotal: ₹").append(total)
                        .append("\n----------------------\n");
            }

            sb.append("\nGrand Total: ₹").append(grandTotal);
            showMessage("Invoice", sb.toString());
        });
        maxMinButton.setOnClickListener(v -> {
            Cursor max = db.getMaxPricedProduct();
            Cursor min = db.getMinPricedProduct();

            max.moveToFirst();
            min.moveToFirst();

            StringBuilder sb = new StringBuilder();
            sb.append("Max Priced:\n")
                    .append("Name: ").append(max.getString(0))
                    .append(" | Price: ").append(max.getDouble(1)).append("\n\n")
                    .append("Min Priced:\n")
                    .append("Name: ").append(min.getString(0))
                    .append(" | Price: ").append(min.getDouble(1));

            showMessage("Price Extremes", sb.toString());
        });
    }

    private void showMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
