package com.example.survey_nodao;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CreateSurveyActivity extends AppCompatActivity {

    EditText etQuestion;
    Button btnSave;
    SurveyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_survey);

        etQuestion = findViewById(R.id.etQuestion);
        btnSave = findViewById(R.id.btnDone);
        dbHelper = new SurveyDatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            String question = etQuestion.getText().toString().trim();
            if (!question.isEmpty()) {
                dbHelper.insertQuestion(question);
                Toast.makeText(this, "Question Saved", Toast.LENGTH_SHORT).show();
                etQuestion.setText("");
            } else {
                Toast.makeText(this, "Enter a question", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
