package com.example.autotank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class DashboardActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        TextView txt_name = findViewById(R.id.name);
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String uname = pref.getString("uname", "");
        String txt = "Welcome, " +uname;
        txt_name.setText(txt);

        Button btn_logut = findViewById(R.id.btn_logout);
        Button btn_notify = findViewById(R.id.btn_notify);
        btn_notify.setOnClickListener(v -> {
            Intent resultIntent = new Intent(this, DashboardActivity.class);
            NotificationHelper.showNotification(
                    this,
                    "First Notification",
                    "Hey there, I'm your app",
                    resultIntent,
                    NOTIFICATION_ID
            );
        });

        //btn logout onclick listener
        btn_logut.setOnClickListener(v -> {
            editor.putBoolean("is_login", false);
            editor.apply();
            Intent move_home = new Intent(this, LoginActivity.class);
            startActivity(move_home);
            finish();
        });

    }
}