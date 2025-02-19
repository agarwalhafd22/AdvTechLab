package com.example.labmidsem2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DailyExpense extends AppCompatActivity {

    EditText budgetEditText, foodExpense, transExpense, purchQuant, pricePerItem;
    CheckBox homeCheckBox, hostelCheckBox;
    Button viewTotal, totalExpense, viewExpense;
    RadioGroup radioGroup2;
    RadioButton carRadioButton, busRadioButton, trainRadioButton;

    int tot, totalExpenseInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daily_expense);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        budgetEditText = findViewById(R.id.budgetEditText);
        foodExpense = findViewById(R.id.foodExpense);
        transExpense = findViewById(R.id.transExpense);
        purchQuant = findViewById(R.id.purchQuant);
        pricePerItem = findViewById(R.id.pricePerItem);
        homeCheckBox = findViewById(R.id.homeCheckBox);
        hostelCheckBox = findViewById(R.id.hostelCheckBox);
        carRadioButton = findViewById(R.id.carRadioButton);
        trainRadioButton = findViewById(R.id.trainRadioButton);
        busRadioButton = findViewById(R.id.busRadioButton);
        viewTotal = findViewById(R.id.viewTotal);
        viewExpense = findViewById(R.id.viewExpense);
        totalExpense = findViewById(R.id.totalExpense);
        radioGroup2 = findViewById(R.id.radioGroup2);

        viewTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quant = purchQuant.getText().toString();
                String pricePer = pricePerItem.getText().toString();
                if(quant.isEmpty()||pricePer.isEmpty()){
                    Toast.makeText(DailyExpense.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    int quantInt = Integer.parseInt(quant);
                    int priceInt = Integer.parseInt(pricePer);
                    tot=quantInt*priceInt;
                    Toast.makeText(DailyExpense.this, Integer.toString(tot), Toast.LENGTH_SHORT).show();
                }
            }
        });

        totalExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String budget = budgetEditText.getText().toString();
                String foodExpenseStr = foodExpense.getText().toString();
                String transExpenseStr = transExpense.getText().toString();
                String quant = purchQuant.getText().toString();
                String pricePer = pricePerItem.getText().toString();
                if(budget.isEmpty()||foodExpenseStr.isEmpty()||transExpenseStr.isEmpty()||quant.isEmpty()||pricePer.isEmpty()){
                    Toast.makeText(DailyExpense.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = getIntent();
                    String user = intent.getStringExtra("user");
                    String mode = intent.getStringExtra("mode");
                    int budgetInt = Integer.parseInt(budget);
                    int foodExpenseInt = Integer.parseInt(foodExpenseStr);
                    int transExpenseInt = Integer.parseInt(transExpenseStr);
                    int quantInt = Integer.parseInt(quant);
                    int priceInt = Integer.parseInt(pricePer);
                    tot=quantInt*priceInt;
                    totalExpenseInt = foodExpenseInt+transExpenseInt+tot;
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());
                    alertDialogBuilder.setTitle("Total Expense");
                    alertDialogBuilder.setMessage("Username: "+user+" Expense: "+Integer.toString(totalExpenseInt));
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(totalExpenseInt>budgetInt){
                                showDialog(totalExpenseInt, budgetInt);
                            }
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                }
            }
        });
        
        viewExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=transExpense.getText().toString();
                int checkedId = radioGroup2.getCheckedRadioButtonId();
                if(checkedId!=-1&&!str.isEmpty()) {
                    if(checkedId==R.id.carRadioButton) {
                        Toast.makeText(DailyExpense.this, "Transportation Mode: Car Expense: "+str, Toast.LENGTH_SHORT).show();
                    } else if(checkedId == R.id.busRadioButton) {
                        Toast.makeText(DailyExpense.this, "Transportation Mode: Bus Expense: "+str, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DailyExpense.this, "Transportation Mode: Train Expense: "+str, Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(DailyExpense.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void showDialog(int totalExpenseInt, int budgetInt){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Budget Exceeded");
        alertDialogBuilder.setMessage("Your Daily Budget has exceeded: "+Integer.toString(totalExpenseInt-budgetInt));
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}