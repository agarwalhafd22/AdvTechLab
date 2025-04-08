package com.example.bill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class 8u9988DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "budgetApp";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE budget (id INTEGER PRIMARY KEY, budget REAL, budgetLimit REAL)");
        db.execSQL("CREATE TABLE expense (id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT, description TEXT, amount REAL, date TEXT)");
        db.execSQL("INSERT INTO budget (id, budget, budgetLimit) VALUES (1, 0, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS budget");
        db.execSQL("DROP TABLE IF EXISTS expense");
        onCreate(db);
    }

    public void updateBudget(double budget, double limit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("budget", budget);
        values.put("budgetLimit", limit);
        db.update("budget", values, "id = ?", new String[]{"1"});
    }

    public Cursor getBudget() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM budget WHERE id = 1", null);
    }

    public void addExpense(String category, String description, double amount, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category", category);
        values.put("description", description);
        values.put("amount", amount);
        values.put("date", date);
        db.insert("expense", null, values);
    }

    public Cursor getExpenses() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM expense ORDER BY date DESC", null);
    }
}