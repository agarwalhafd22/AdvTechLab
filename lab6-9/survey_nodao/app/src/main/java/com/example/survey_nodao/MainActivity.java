package com.example.survey_nodao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCreateSurvey, btnAnswerSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateSurvey = findViewById(R.id.btnCreateSurvey);
        btnAnswerSurvey = findViewById(R.id.btnAnswerSurvey);

        btnCreateSurvey.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateSurveyActivity.class);
            startActivity(intent);
        });

        btnAnswerSurvey.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AnswerSurveyActivity.class);
            startActivity(intent);
        });
    }
}
