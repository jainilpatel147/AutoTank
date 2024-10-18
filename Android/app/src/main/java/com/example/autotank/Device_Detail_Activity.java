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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Device_Detail_Activity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private ViewPagerMsgAdapter pagerAdapter;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_device_detail);


        //Getting the shared pref value
        SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String uname = pref.getString("uname","");
        String mo = pref.getString("mobile_no","");

        TextView dev_name = findViewById(R.id.dev_name);
        Intent intent = getIntent();
        String receivedString = intent.getStringExtra("device_name");
        dev_name.setText(receivedString);

        //Tab layout code from here
        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpager);

        pagerAdapter = new ViewPagerMsgAdapter(this);
        viewPager.setAdapter(pagerAdapter);
        new TabLayoutMediator(tab, viewPager, (tab, position) -> {
            // Set the tab title based on position
            switch (position) {
                case 0:
                    tab.setText("First Tab");
                    break;
                case 1:
                    tab.setText("Second Tab");
                    break;
                default:
                    tab.setText("Default Tab");
                    break;
            }
        }).attach();
    }
}