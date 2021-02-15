package com.example.resturant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView navigationView = findViewById(R.id.bottom_navigator);
        BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.cart){
                    Intent intent = new Intent(getBaseContext(),OrderActivity.class);
                    startActivity(intent);
                }
                if(item.getItemId() == R.id.user){
                    Intent intent = new Intent(getBaseContext(),ProfileActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        };
        navigationView.setOnNavigationItemSelectedListener(selectedListener);
    }

public void openCategory(View view){
        int id = view.getId();
        Intent intent ;
        if (id == R.id.burger){
            intent=new Intent(this,BurgerActivity.class);
        }else if (id == R.id.meel){
            intent=new Intent(this,MeelActivity.class);
        }else if (id == R.id.pizza){
            intent=new Intent(this, PizzaActivity.class);
        }else if (id == R.id.appetizer){
            intent=new Intent(this,AppetizerActivity.class);
        }
        else {
            intent=new Intent(this, CrepsActivity.class);
        }
        startActivity(intent);
}


}