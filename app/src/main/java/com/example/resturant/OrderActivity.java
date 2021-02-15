package com.example.resturant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        OrdersDataBase dataBase = new OrdersDataBase(getBaseContext());
        SQLiteDatabase db = dataBase.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ordersTable;",null);
        if (cursor.moveToFirst()){
                OrderFragment orderFragment = new OrderFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.cart_fragment, orderFragment);
                transaction.commit();
            Button button = findViewById(R.id.continue_shopping);
            button.setVisibility(View.GONE);
        }

    }
}