package com.example.student_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "StudentDB";
    private static final String TABLE_NAME = "student";
    private static final int DB_VERSION = 1;

    public StudentDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (roll VARCHAR PRIMARY KEY, name VARCHAR, marks VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertStudent(String roll, String name, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("roll", roll);
        cv.put("name", name);
        cv.put("marks", marks);
        long result = db.insert(TABLE_NAME, null, cv);
        return result != -1;
    }

    public boolean updateStudent(String roll, String name, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("marks", marks);
        int result = db.update(TABLE_NAME, cv, "roll=?", new String[]{roll});
        return result > 0;
    }

    public boolean deleteStudent(String roll) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, "roll=?", new String[]{roll});
        return result > 0;
    }

    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
