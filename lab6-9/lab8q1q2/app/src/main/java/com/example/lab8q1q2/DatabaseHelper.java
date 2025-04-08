package com.example.lab8q1q2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "BillingDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "products";
    private static final String COL_NAME = "name";
    private static final String COL_PRICE = "price";
    private static final String COL_QTY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COL_NAME + " TEXT, " +
                COL_PRICE + " REAL, " +
                COL_QTY + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, product.getName());
        values.put(COL_PRICE, product.getPrice());
        values.put(COL_QTY, product.getQuantity());
        return db.insert(TABLE_NAME, null, values) > 0;
    }

    public Cursor getAllProducts() {
        return getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getMaxPricedProduct() {
        return getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " ORDER BY price DESC LIMIT 1", null);
    }

    public Cursor getMinPricedProduct() {
        return getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " ORDER BY price ASC LIMIT 1", null);
    }
}
