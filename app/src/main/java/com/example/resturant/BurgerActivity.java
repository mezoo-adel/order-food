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

public class BurgerActivity extends AppCompatActivity {
    int[] price = {30,38,35,43,35,43,35,42,28,36};
    int [] image = {R.drawable.burger0, R.drawable.burger1,R.drawable.burger3,R.drawable.burger4,R.drawable.burger2,R.drawable.burger7,R.drawable.burger5,R.drawable.burger6,R.drawable.burger9,R.drawable.burger8};
    String[] name = {"تشيز برجر", "تشيز برجر لارج", "تشيز مشروم برجر", "تشيز مشروم برجر لارج", "بيكون برجر", "بيكون برجر لارج", "مكس برجر", "مكس برجر لارج", "سموكد برجر", "سموكد برجر لارج"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);

        RecyclerView recyclerView = findViewById(R.id.recycularView_burger);
        MyRecycularAdapter recycularAdapter = new MyRecycularAdapter(price,image,name,null);
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