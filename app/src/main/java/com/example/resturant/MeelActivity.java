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

public class MeelActivity extends AppCompatActivity {
    int[] price = {45,50,95,85,90,100,110,60};
    int[] priceSize = {80,90,160,85*2,135,250,220,60*2};
    int [] image = { R.drawable.meel0,R.drawable.meel1,R.drawable.meel2,R.drawable.meel3,R.drawable.meel4,R.drawable.meel5,R.drawable.meel6,R.drawable.meel7};
    String[] name = {"فراخ مشوية", "وجبة 2", "وجبة 3", "وجبة 4", "وجبة 5", "وجبة 6", "وجبة 7", "وجبة 8"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meel);

        RecyclerView recyclerView = findViewById(R.id.recycularView_meal);
        MyRecycularAdapter recycularAdapter = new MyRecycularAdapter(price,image,name,priceSize);
        recyclerView.setAdapter(recycularAdapter);

        GridLayoutManager manager = new GridLayoutManager(this ,2);
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