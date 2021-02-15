package com.example.resturant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PayingActivity extends AppCompatActivity {
    Intent intent = null;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paying);
        intent = getIntent();
        message = intent.getStringExtra("orderFinal");
        int total = intent.getIntExtra("total",0);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView = findViewById(R.id.food_price_final);
        textView.setText(String.valueOf(total));

        Button creditCard = findViewById(R.id.addCard);
        creditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PayingActivity.this, "قريبا ....", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void openProfile(View view) {
        startActivity(new Intent(PayingActivity.this, ProfileActivity.class));
    }

    public void cashOndelivery(View view) {
        String phoneNumberWithCountryCode = "+201091720977";
        Intent openWhatsap = new Intent(Intent.ACTION_VIEW);
        openWhatsap.setData(Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)));
        startActivity(openWhatsap);
    }
}