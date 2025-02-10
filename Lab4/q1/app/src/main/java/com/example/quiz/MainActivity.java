package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup1, radioGroup2, radioGroup3, radioGroup4;
    RadioButton radioButton1_1, radioButton2_3, radioButton3_1, radioButton4_3;

    Button button;

    int result=0;

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

        button = findViewById(R.id.button);

        radioGroup1=findViewById(R.id.radioGroup1);
        radioGroup2=findViewById(R.id.radioGroup2);
        radioGroup3=findViewById(R.id.radioGroup3);
        radioGroup4=findViewById(R.id.radioGroup4);

        radioButton1_1=findViewById(R.id.radioButton1_1);
        radioButton2_3=findViewById(R.id.radioButton2_3);
        radioButton3_1=findViewById(R.id.radioButton3_1);
        radioButton4_3=findViewById(R.id.radioButton4_3);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(!radioButton1_1.isChecked()) {
                    if (checkedId == R.id.radioButton1_1) {
                        result++;
                    }
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(!radioButton2_3.isChecked()) {
                    if (checkedId == R.id.radioButton2_3) {
                        result++;
                    }
                }
            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(!radioButton3_1.isChecked()) {
                    if (checkedId == R.id.radioButton3_1) {
                        result++;
                    }
                }
            }
        });

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(!radioButton4_3.isChecked()) {
                    if (checkedId == R.id.radioButton4_3) {
                        result++;
                    }
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertBox();


            }
        });
    }

    private void alertBox(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


        alertDialogBuilder.setTitle("Submit Quiz");
        alertDialogBuilder.setMessage("Are you sure you want to submit the quiz?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                radioGroup1.clearCheck();
                radioGroup2.clearCheck();
                radioGroup3.clearCheck();
                radioGroup4.clearCheck();
                Intent intent = new Intent(MainActivity.this, Result.class);
                intent.putExtra("result", result);
                startActivity(intent);
            }
        });


        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss the dialog
            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    protected void onResume() {
        super.onResume();
        result = 0;
    }
}