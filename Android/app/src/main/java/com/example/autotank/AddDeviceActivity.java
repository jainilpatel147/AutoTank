package com.example.autotank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;

public class AddDeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_device);
        Button btn = findViewById(R.id.signupButton);
        EditText uname = findViewById(R.id.nameInput);
        EditText Height = findViewById(R.id.HeightInput);
        SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String userid = pref.getString("userid", "");
        Toast.makeText(this,"id" + userid,Toast.LENGTH_SHORT).show();

        btn.setOnClickListener(v -> {
            try {
                DeviceFunc.createDevice(this, uname.getText().toString(),Height.getText().toString(),userid,
                        isSuccess -> {
                            if(isSuccess!="false"){
                                Toast.makeText(this,"Device Created",Toast.LENGTH_SHORT).show();
                                Intent view_list = new Intent(this,DashboardActivity.class);
                                startActivity(view_list);
                                finish();
                            }
                            else{
                                Toast.makeText(this,"User Already Exists",Toast.LENGTH_SHORT).show();;
                            }
                        });
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }
}