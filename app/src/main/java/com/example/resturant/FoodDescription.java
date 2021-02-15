package com.example.resturant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class FoodDescription extends AppCompatActivity {
    int quantity = 1;
    CheckBox addToFavourite;
    TextView textView;
    TextView textView0;
    TextView food_quantity;
    ImageView imageView;
    Switch chip;
    int price;
    int priceSize;
    int x = price;
    OrdersDataBase dataBase;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_description);

        Intent intentDescription = getIntent();
        final String name = intentDescription.getStringExtra("names");
        price = intentDescription.getIntExtra("prices", 0);
        final int image = intentDescription.getIntExtra("images", R.drawable.burger);
        priceSize = intentDescription.getIntExtra("priceSize", 0);
        final int id = intentDescription.getIntExtra("id",0);

        chip = findViewById(R.id.radioChipSize);
        if (priceSize != 0) {
            chip.setVisibility(View.VISIBLE);
        } else {
            chip.setVisibility(View.GONE);
        }


        addToFavourite = findViewById(R.id.favorite);
        addToFavourite.setVisibility(View.INVISIBLE);
        addToFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                OrdersDataBase dataBase= new OrdersDataBase(getBaseContext());
                SQLiteDatabase dbhelp = dataBase.getWritableDatabase();
                if (buttonView.isChecked()) {
                    dbhelp.execSQL("UPDATE ordersTable SET favorite = 1 Where _id = "+id+ ";");
                    buttonView.setButtonDrawable(R.drawable.heart);
                    buttonView.setText(" Loved ");
                } else {
                    buttonView.setText(" Like ");
                    buttonView.setButtonDrawable(R.drawable.like);
                    dbhelp.execSQL("UPDATE ordersTable SET favorite = 0 Where _id = "+id+ ";");
                }
            }
        });
        textView = findViewById(R.id.food_name_description);
        textView.setText(name);
        textView0 = findViewById(R.id.food_price_description);
        textView0.setText(price + " EGP");
        imageView = findViewById(R.id.food_image_description);
        imageView.setImageResource(image);

        Button plus = findViewById(R.id.plus);
        Button minus = findViewById(R.id.minus);
        food_quantity = findViewById(R.id.food_quantity);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity < 8) {
                    food_quantity.setText(String.valueOf(quantity + 1));
                    quantity = Integer.parseInt(food_quantity.getText().toString());
                    textView0.setText(quantity * price + " EGP");
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(food_quantity.getText().toString());
                if (quantity > 0) {
                    food_quantity.setText(String.valueOf(quantity - 1));
                    quantity = Integer.parseInt(food_quantity.getText().toString());
                    textView0.setText(quantity * price + " EGP");
                }
            }
        });


        dataBase = new OrdersDataBase(this);
        Button addToCart = findViewById(R.id.food_add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = dataBase.getWritableDatabase();
                String[] args = {name, String.valueOf(price), String.valueOf(price * quantity), String.valueOf(image), String.valueOf(quantity)};
                db.execSQL("INSERT INTO ordersTable (name , price ,totalPrice, image , quantity) VALUES (?,?,?,?,?);", args);
                Toast.makeText(FoodDescription.this, "تم الاضافة الى طلباتك", Toast.LENGTH_SHORT).show();
                //يقفل الشاشة الحالية ويرجع على اللى قبلها اوتوماتيك
                onBackPressed();

            }
        });
    }

    public void comboSize(View view) {
        if (chip.isChecked()) {
            x = price;
            price = priceSize;
            textView0.setText(quantity * price + " EGP");
        } else {
            price = x;
            textView0.setText(quantity * price + " EGP");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}