package com.example.survey_nodao;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AnswerSurveyActivity extends AppCompatActivity {

    ListView listView;
    SurveyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_survey);

        listView = findViewById(R.id.listView);
        dbHelper = new SurveyDatabaseHelper(this);

        ArrayList<String> questions = dbHelper.getAllQuestions();

        if (questions.isEmpty()) {
            questions.add("No questions available. Please create a survey.");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions);
        listView.setAdapter(adapter);
    }
}
