package com.example.autotank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Button btn = findViewById(R.id.loginButton);
        EditText uname = findViewById(R.id.nameInput);
        EditText mobile_no = findViewById(R.id.mobileInput);

        btn.setOnClickListener(v -> {

//            SmsHelper.sendSms(this,"6352751359","OTP Message");
//            Toast.makeText(this, "SMS Sent to 6352751359", Toast.LENGTH_SHORT).show();
            SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();

            editor.putBoolean("is_login",true);
            editor.putString("uname",uname.getText().toString());
            editor.putString("mobile_no",mobile_no.getText().toString());
            editor.apply();

            Intent view_list = new Intent(this,ReadingsActivity.class);
            startActivity(view_list);
            finish();
        });
    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        // Handle the permission result
//        SmsHelper.handlePermissionResult(this, requestCode, permissions, grantResults, "9876543210", "Hello from Android!");
//    }
}