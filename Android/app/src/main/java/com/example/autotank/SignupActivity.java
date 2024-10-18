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
import org.w3c.dom.Text;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        Button btn = findViewById(R.id.signupButton);
        EditText uname = findViewById(R.id.nameInput);
        EditText email_id = findViewById(R.id.emailInput);
        EditText password = findViewById(R.id.passwordInput);
        EditText confPass = findViewById(R.id.confpassInput);
        TextView txt = findViewById(R.id.login_btn);

        txt.setOnClickListener(v -> {
            Intent view_list = new Intent(this,LoginActivity.class);
            startActivity(view_list);
            finish();
        });
        btn.setOnClickListener(v -> {
            if(!password.getText().toString().equals(confPass.getText().toString())){
                Toast.makeText(this,"Passwords Mismatch",Toast.LENGTH_LONG).show();
                return;
            }
            try {
                UserFunc.createUser(this, uname.getText().toString(),email_id.getText().toString(), password.getText().toString(),
                        isSuccess -> {
                            if(isSuccess){
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
                                Toast.makeText(this,"User Already Exists",Toast.LENGTH_SHORT).show();;
                            }
                        });
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        });
    }
}