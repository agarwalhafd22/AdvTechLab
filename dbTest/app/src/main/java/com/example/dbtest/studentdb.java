package com.example.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//backend for database
public class studentdb extends SQLiteOpenHelper {

    protected static final String db_name="student";

    private static final int db_verison=1;
    protected static final String table_name="student_database";
    protected static final String student_name="student__name";

    protected static final String student_number="student__number";

//constructor

    public studentdb(Context context) {
        super(context, db_name, null, db_verison);
    }

    //create table,in built
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + " ("
                + student_number + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + student_name + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    //insert vals in table,userdefined
    public void insertvals(String studentName){
//ob of the function
        SQLiteDatabase db=this.getWritableDatabase();
//ob of CLASS content values
        ContentValues cv=new ContentValues();
//col var,parameter
        cv.put(student_name,studentName);

//inserting table
        db.insert(table_name,null,cv);

        db.close();
    }
//command

}
