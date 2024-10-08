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
import android.util.Log;
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

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadingsActivity extends AppCompatActivity {
    private ArrayList<String> names = new ArrayList<>();
    private static final String CHANNEL_ID = "My_Channel";
    private static final int NOTIFICATION_ID = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_readings);
        SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String uname = pref.getString("uname","");
        String mo = pref.getString("mobile_no","");

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
        names.add(uname);
        names.add(mo);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,names);
        l1.setAdapter(adapter);


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






        // Calling api from the server
        // Get the API service instance
        ApiClient.ApiService apiService = ApiClient.getApiService();

        // Make the API call
        Call<ResponseBody> call = apiService.getDynamicResponse(); // Your API call

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body(); // Get the raw response body
                    if (responseBody != null) {
                        try {
                            String responseString = responseBody.string(); // Get response as string
                            Log.d("API", "Response: " + responseString); // Log the response

                            // Parse the JSON response directly
                            JSONArray jsonArray = new JSONArray(responseString); // Convert response string to JSONArray

                            for (int i = 0; i < jsonArray.length(); i++) {
                                int id = jsonArray.getJSONObject(i).getInt("id"); // Get ID from each JSONObject
                                names.add(String.valueOf(id)); // Add ID to the list
                                Log.d("API", "Added ID: " + id); // Log each added ID
                            }

                            // Set up the adapter to display IDs in the ListView
                            adapter.notifyDataSetChanged(); // Notify adapter to refresh the ListView

                        } catch (JSONException e) {
                            Log.e("API", "Error parsing JSON: " + e.getMessage());
                        } catch (IOException e) {
                            Log.e("API", "Error reading response: " + e.getMessage());
                        }
                    } else {
                        Log.e("API", "Response body is null");
                    }
                } else {
                    Log.e("API", "Request Failed. Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("API", "Network Failure: " + t.getMessage());
            }
        });

    }


}