package com.example.resturant;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

public class ProfileActivity extends AppCompatActivity {

    EditText editName;
    EditText editPhone;
    EditText editAdress;

     SharedPreferences preferences;
     String sharedPrefFile = "com.example.android.hellosharedprefs";
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        editName = findViewById(R.id.editPersonName);
        editPhone = findViewById(R.id.editPersonPhone);
        editAdress = findViewById(R.id.editPersonaddress);

        preferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        editName.setText(preferences.getString("myName", null));
        editPhone.setText(preferences.getString("myPhone", null));
        editAdress.setText(preferences.getString("myAddress", null));

    }

    public void saveProfileInfo(View view) {
        editor = preferences.edit();
        if (!editName.getText().toString().isEmpty() && !editPhone.getText().toString().isEmpty() && !editAdress.getText().toString().isEmpty()) {
            editor.putString("myName", editName.getText().toString());
            editor.putString("myPhone", editPhone.getText().toString());
            editor.putString("myAddress", editAdress.getText().toString());
            editor.commit();
            Toast.makeText(ProfileActivity.this, "تم الحفظ بـنجاح", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ProfileActivity.this, "البيانات غير مكتملة", Toast.LENGTH_SHORT).show();
        }
    }
}