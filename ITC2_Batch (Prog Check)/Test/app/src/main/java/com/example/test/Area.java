package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Area extends AppCompatActivity {



    TextView shapeTextView, result;
    EditText length, breadth;
    int area;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_area);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        shapeTextView = findViewById(R.id.shapeTextView);
        submit = findViewById(R.id.submit);
        length = findViewById(R.id.length);
        breadth = findViewById(R.id.breadth);
        result = findViewById(R.id.result);

        Intent intent = getIntent();
        String receivedString = intent.getStringExtra("key");

        if(receivedString.equals("Square")){
            shapeTextView.setText("Square");
        } else if (receivedString.equals("Rectangle")) {
            shapeTextView.setText("Rectangle");
        } else {
            shapeTextView.setText("Triangle");
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lengthInt = Integer.parseInt(length.getText().toString());
                int breadthInt = Integer.parseInt(breadth.getText().toString());
                if(receivedString.equals("Square")) {
                    if(lengthInt==breadthInt) {
                        area = lengthInt * breadthInt;
                        //result.setText("The area is: "+Integer.toString(area));
                        Toast.makeText(Area.this, "The area is: " + area, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Area.this, "For Square, length is equal to breadth", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    area = lengthInt * breadthInt;
                    //result.setText("The area is: "+Integer.toString(area));
                    Toast.makeText(Area.this, "The area is: " + area, Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}