package com.example.student_management;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText rollInput, nameInput, marksInput;
    Button addBtn, viewBtn, updateBtn, deleteBtn;
    StudentDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new StudentDBHelper(this);

        rollInput = findViewById(R.id.rollInput);
        nameInput = findViewById(R.id.nameInput);
        marksInput = findViewById(R.id.marksInput);

        addBtn = findViewById(R.id.addBtn);
        viewBtn = findViewById(R.id.viewBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        addBtn.setOnClickListener(v -> {
            boolean inserted = dbHelper.insertStudent(
                    rollInput.getText().toString(),
                    nameInput.getText().toString(),
                    marksInput.getText().toString()
            );
            showToast(inserted ? "Student Added" : "Failed to Add");
        });

        updateBtn.setOnClickListener(v -> {
            boolean updated = dbHelper.updateStudent(
                    rollInput.getText().toString(),
                    nameInput.getText().toString(),
                    marksInput.getText().toString()
            );
            showToast(updated ? "Updated Successfully" : "Update Failed");
        });

        deleteBtn.setOnClickListener(v -> {
            boolean deleted = dbHelper.deleteStudent(rollInput.getText().toString());
            showToast(deleted ? "Deleted Successfully" : "Delete Failed");
        });

        viewBtn.setOnClickListener(v -> {
            Cursor cursor = dbHelper.getAllStudents();
            if (cursor.getCount() == 0) {
                showToast("No Records Found");
                return;
            }

            StringBuilder sb = new StringBuilder();
            while (cursor.moveToNext()) {
                sb.append("Roll: ").append(cursor.getString(0)).append("\n");
                sb.append("Name: ").append(cursor.getString(1)).append("\n");
                sb.append("Marks: ").append(cursor.getString(2)).append("\n\n");
            }

            showAlert("Student Records", sb.toString());
        });
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    void showAlert(String title, String msg) {
        new android.app.AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }
}
