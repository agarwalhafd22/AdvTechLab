package com.example.survey_nodao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class SurveyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SurveyDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "survey_questions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_QUESTION = "question";

    public SurveyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_QUESTION + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertQuestion(String question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_QUESTION, question);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<String> getAllQuestions() {
        ArrayList<String> questions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String question = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION));
                questions.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return questions;
    }
}
