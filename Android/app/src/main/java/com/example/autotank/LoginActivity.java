package com.example.autotank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
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

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Button btn = findViewById(R.id.loginButton);
        EditText uname = findViewById(R.id.nameInput);
        EditText password = findViewById(R.id.passwordInput);
        TextView txt = findViewById(R.id.signup_btn);
        txt.setOnClickListener(v -> {
            Intent view_list = new Intent(this,SignupActivity.class);
            startActivity(view_list);
            finish();
        });
        btn.setOnClickListener(v -> {
            try {
                Toast.makeText(this,"Loading..",Toast.LENGTH_SHORT).show();
                UserFunc.validateUser(this, uname.getText().toString(), password.getText().toString(),
                        isSuccess -> {
                            if(isSuccess!="false"){
                                SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();

                                editor.putBoolean("is_login",true);
                                editor.putString("uname",uname.getText().toString());
                                editor.apply();

                                Intent view_list = new Intent(this,DashboardActivity.class);
                                startActivity(view_list);
                                finish();
                            }
                            else{
                                Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show();;
                            }
                        });

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        });
    }

}