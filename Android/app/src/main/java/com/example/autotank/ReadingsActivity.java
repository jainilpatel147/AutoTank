package com.example.autotank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ReadingsActivity extends AppCompatActivity {
    private ArrayList<String> names = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_readings);
        ListView l1 = findViewById(R.id.list_view_1);
        Button btn_logut = findViewById(R.id.btn_logout);

        names.add("jainil");
        names.add("dhruval");
        names.add("Bhavya");
        names.add("Jay");
        names.add("Aditya");
        names.add("Gautam");
        names.add("Radhi");
        names.add("Manan");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,names);
        l1.setAdapter(adapter);

        btn_logut.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();

            editor.putBoolean("is_login",false);
            editor.apply();

            Intent move_home = new Intent(this,MainActivity.class);
            startActivity(move_home);
        });
    }
}