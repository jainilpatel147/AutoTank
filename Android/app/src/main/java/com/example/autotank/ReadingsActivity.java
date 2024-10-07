package com.example.autotank;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ReadingsActivity extends AppCompatActivity {
    private ArrayList<String> names = new ArrayList<>();
    private static final String CHANNEL_ID = "My_Channel";
    private static final int NOTIFICATION_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_readings);


        ListView l1 = findViewById(R.id.list_view_1);
        Button btn_logut = findViewById(R.id.btn_logout);
        Button btn_notify = findViewById(R.id.btn_notify);
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
        SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        btn_notify.setOnClickListener(v -> {
            Intent resultIntent = new Intent(this, ReadingsActivity.class);
            NotificationHelper.showNotification(
                    this,
                    "First Notification",
                    "Hey there, I'm Vishwa",
                    resultIntent,
                    NOTIFICATION_ID
            );
        });


        btn_logut.setOnClickListener(v -> {


            editor.putBoolean("is_login",false);
            editor.apply();

            Intent move_home = new Intent(this,LoginActivity.class);
            startActivity(move_home);
            finish();
        });

    }
}