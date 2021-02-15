package com.example.resturant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.resturant.R;

public class AppetizerActivity extends AppCompatActivity {
    int[] price = {45,25,35,40,35,50,36,20,110};
    int [] image = {R.drawable.mouse_images, R.drawable.pie,R.drawable.summer_dessert,R.drawable.dessert_fruits,R.drawable.caramel,R.drawable.cheese_cake,R.drawable.cheesecake_pudding,R.drawable.chocolate_bars,R.drawable.cherry_dessert};
    String[] name = {"mouze", "pie", "summer dessert", "dessert fruits", "caramel", "cheese cake", "cheesecake pudding", "chocolate bars", "cherry dessert"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appetizer);

        RecyclerView recyclerView = findViewById(R.id.recycularView_appetizer);
        MyRecycularAdapter recycularAdapter = new MyRecycularAdapter(price,image,name,null);
        recyclerView.setAdapter(recycularAdapter);
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getBaseContext(),OrderActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}