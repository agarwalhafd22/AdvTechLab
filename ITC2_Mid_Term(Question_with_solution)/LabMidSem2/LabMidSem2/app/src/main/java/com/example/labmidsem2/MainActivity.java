package com.example.labmidsem2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton rbDaily, rbWeekly;
    Button proceed;

    String user="";
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);
        rbDaily = findViewById(R.id.rbDaily);
        rbWeekly = findViewById(R.id.rbWeekly);
        proceed = findViewById(R.id.proceed);

        ArrayList<String> users=new ArrayList<>();
        users.add("Select");
        users.add("User 1");
        users.add("User 2");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0) {
                    user = users.get(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rbDaily){
                    Toast.makeText(MainActivity.this, "Mode Selected: Daily", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.rbWeekly) {
                    Toast.makeText(MainActivity.this, "Mode Selected: Weekly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                if(checkedId==-1) {
                    Toast.makeText(MainActivity.this, "Select a mode", Toast.LENGTH_SHORT).show();
                } else if (user.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Select a user", Toast.LENGTH_SHORT).show();
                } else {
                    if(checkedId == R.id.rbDaily) {
                        mode = "Daily";
                    } else {
                        mode = "Weekly";
                    }
                    Toast.makeText(MainActivity.this, "User : "+user+" Mode: "+mode, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DailyExpense.class);
                    intent.putExtra("user", user);
                    intent.putExtra("mode", mode);
                    startActivity(intent);
                }
            }
        });

    }
}