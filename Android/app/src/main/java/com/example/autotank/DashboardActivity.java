package com.example.autotank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.util.ArrayList;


public class DashboardActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 100;
    ArrayList<String> stringList = new ArrayList<>();
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
        ListView listView = findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                stringList
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = (String) parent.getItemAtPosition(position);
            Toast.makeText(this,"name"+selectedItem ,Toast.LENGTH_SHORT).show();
            Intent view_list = new Intent(this,Device_Detail_Activity.class);
            view_list.putExtra("device_name", selectedItem);
            startActivity(view_list);

        });
        //Fetch user id from the data base and store it into the shared preference
        try {
            UserFunc.fetchUserId(this,uname,"_id",isSuccess -> {
                if(isSuccess!="false"){
                    //Toast.makeText(this,"userid"+ isSuccess ,Toast.LENGTH_SHORT).show();
                    editor.putString("userid",isSuccess);
                    editor.apply();
                }
                else{
                    Toast.makeText(this,"userid not" ,Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String userid = pref.getString("userid", "");
        try {
            DeviceFunc.fetchDeviceById(this,userid,"name",stringList,adapter,isSuccess -> {
                if(isSuccess!="false"){
                    //Toast.makeText(this,"userid"+ isSuccess ,Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
                else{
                    //Toast.makeText(this,"No device found" ,Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Button btn_logut = findViewById(R.id.btn_logout);
//        Button btn_notify = findViewById(R.id.btn_notify);
//        btn_notify.setOnClickListener(v -> {
//            Intent resultIntent = new Intent(this, DashboardActivity.class);
//            NotificationHelper.showNotification(
//                    this,
//                    "First Notification",
//                    "Hey there, I'm your app",
//                    resultIntent,
//                    NOTIFICATION_ID
//            );
//        });
        Button add_dev= findViewById(R.id.btn_addnew);
        add_dev.setOnClickListener(v -> {
            Intent move_home = new Intent(this, AddDeviceActivity.class);
            startActivity(move_home);
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