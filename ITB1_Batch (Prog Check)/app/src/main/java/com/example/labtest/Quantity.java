package com.example.labtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quantity extends AppCompatActivity {

    RadioGroup radioGroup;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quantity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String receivedString = intent.getStringExtra("clickedItem"); // Use the same key used in putExtra

        radioGroup = findViewById(R.id.radioGroup);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if(selectedId !=-1){
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String text = selectedRadioButton.getText().toString();
                    if(receivedString.equals("Apple")){
                        if(text.equals("1")){
                            Toast.makeText(Quantity.this, "Item Selected: "+receivedString+" Amount: "+"25", Toast.LENGTH_SHORT).show();
                        }
                        else if(text.equals("2")){
                            Toast.makeText(Quantity.this, "Item Selected: "+receivedString+" Amount: "+"50", Toast.LENGTH_SHORT).show();
                        }
                        else if(text.equals("3")){
                            Toast.makeText(Quantity.this, "Item Selected: "+receivedString+" Amount: "+"75", Toast.LENGTH_SHORT).show();
                        }
                    } else if(receivedString.equals("Banana")){
                        if(text.equals("1")){
                            Toast.makeText(Quantity.this, "Item Selected: "+receivedString+" Amount: "+"20", Toast.LENGTH_SHORT).show();
                        }
                        else if(text.equals("2")){
                            Toast.makeText(Quantity.this, "Item Selected: "+receivedString+" Amount: "+"40", Toast.LENGTH_SHORT).show();
                        }
                        else if(text.equals("3")){
                            Toast.makeText(Quantity.this, "Item Selected: "+receivedString+" Amount: "+"60", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(Quantity.this, "Select Option", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}