package com.example.resturant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

public class CrepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creeps);
        String[]name = {"كريب فراولة" , "كريب نوتيلا موز" ," كريب فاهيتا "," كريب كرانشي"};
     int[]images = {R.raw.crepe1_220_220,R.raw.crepe2_171_240,R.raw.crepe3_160_240,R.raw.crepe4_220_135};
        int[] price = {27,27,45,45};

        RecyclerView view = findViewById( R.id.crepe_RecycularView);
     MyRecycularAdapter adapter = new MyRecycularAdapter(price,images,name,null);
     view.setAdapter(adapter);


        view.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        startActivity(new Intent(this,OrderActivity.class));
        return super.onOptionsItemSelected(item);
    }
}