package com.example.dbtest;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    Button but1;

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
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        but1=findViewById(R.id.but1);
//creating ib if the class
        studentdb stud=new studentdb(MainActivity.this);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//converting to string
                String a=et1.getText().toString().trim(); //name
                String b=et2.getText().toString().trim(); //numeber


                int d=Integer.parseInt(b);

                stud.insertvals(a);
            }

        });


    }
}