package com.example.resturant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OrdersDataBase extends SQLiteOpenHelper {
    private static final String DB_NAME = "Orders";
    private static final int DB_version = 2;

    public OrdersDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDBStructure(db, 0, DB_version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDBStructure(db, oldVersion, newVersion);
    }

    private void updateDBStructure(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {

            String createTable = "CREATE TABLE ordersTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , price INTEGER, totalPrice INTEGER , image INTEGER,quantity INTEGER);";
            db.execSQL(createTable);
        }
        if (oldVersion < 2) {
            String addToFavorite = "ALTER TABLE ordersTable ADD COLUMN favorite NUMERIC ;";
            db.execSQL(addToFavorite);
        }
    }
}
