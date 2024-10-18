package com.example.autotank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
                        Boolean is_login = pref.getBoolean("is_login" , false);
                        Intent navigate;
                        if(is_login){
                            navigate  = new Intent(getApplicationContext(),DashboardActivity.class);
                        }
                        else{
                            navigate = new Intent(getApplicationContext(),LoginActivity.class);
                        }
                        startActivity(navigate);
                        finish();
                    }
                },4000
        );

    }
}