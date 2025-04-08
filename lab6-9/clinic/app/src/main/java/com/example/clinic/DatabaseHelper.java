package com.example.clinic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "ClinicDB", null, 2); // Increased version to 2
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT)");
        db.execSQL("CREATE TABLE doctor (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, specialization TEXT, is_available INTEGER)");
        db.execSQL("CREATE TABLE appointment (id INTEGER PRIMARY KEY AUTOINCREMENT, patient_name TEXT, doctor_id INTEGER, date TEXT, FOREIGN KEY(doctor_id) REFERENCES doctor(id))");

        // Insert default doctors
        ContentValues cv1 = new ContentValues();
        cv1.put("name", "Dr. Smith");
        cv1.put("specialization", "General");
        cv1.put("is_available", 1);
        db.insert("doctor", null, cv1);

        ContentValues cv2 = new ContentValues();
        cv2.put("name", "Dr. Jones");
        cv2.put("specialization", "Cardiology");
        cv2.put("is_available", 0);
        db.insert("doctor", null, cv2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS doctor");
        db.execSQL("DROP TABLE IF EXISTS appointment");
        onCreate(db);
    }

    public boolean registerUser(String user, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", user);
        cv.put("password", pass);
        long res = db.insert("users", null, cv);
        return res != -1;
    }

    public boolean checkLogin(String user, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{user, pass});
        return cursor.getCount() > 0;
    }

    public Cursor getAvailableDoctors() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM doctor WHERE is_available=1", null);
    }

    public boolean bookAppointment(String patientName, int doctorId, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("patient_name", patientName);
        cv.put("doctor_id", doctorId);
        cv.put("date", date);
        long res = db.insert("appointment", null, cv);
        if (res != -1) {
            updateDoctorAvailability(doctorId, 0); // mark doctor unavailable after booking
            return true;
        }
        return false;
    }

    public boolean updateDoctorAvailability(int doctorId, int isAvailable) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("is_available", isAvailable);
        int result = db.update("doctor", cv, "id=?", new String[]{String.valueOf(doctorId)});
        return result > 0;
    }

    public Cursor getAppointments() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT a.id, a.patient_name, d.name AS doctor_name, a.date FROM appointment a JOIN doctor d ON a.doctor_id = d.id", null);
    }
}
