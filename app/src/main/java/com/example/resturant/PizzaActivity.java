package com.example.resturant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PizzaActivity extends AppCompatActivity {
    int[] price = {25, 32,35,65,80,45};
    int[] priceSize = {48,58,89,150,100,80};
    int [] image = {R.drawable.pizza0, R.drawable.pizza1,R.drawable.pizza3,R.drawable.pizza5,R.drawable.pizza6,R.drawable.pizza8,R.drawable.pizza9};
    String[] name = {"pizza0", "pizza 2", "pizza 3", "pizza 4", "pizza 5", "pizza 6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        RecyclerView recyclerView = findViewById(R.id.recycularView_pizza);
        MyRecycularAdapter recycularAdapter = new MyRecycularAdapter(price, image, name,priceSize);
        recyclerView.setAdapter(recycularAdapter);
        GridLayoutManager manager = new GridLayoutManager(getBaseContext(), 2);
        recyclerView.setLayoutManager(manager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getBaseContext(), OrderActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}