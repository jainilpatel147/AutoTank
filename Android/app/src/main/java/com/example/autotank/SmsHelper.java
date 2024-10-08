package com.example.autotank;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SmsHelper {

    private static final int SMS_PERMISSION_CODE = 1;

    public static void sendSms(Activity activity, String phoneNumber, String message) {
        // Check for SMS permission
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Request SMS permission
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        } else {
            // Permission already granted, send SMS
            sendSmsMessage(phoneNumber, message);
        }
    }

    // Handle permission request result
    public static void handlePermissionResult(Activity activity, int requestCode, String[] permissions, int[] grantResults, String phoneNumber, String message) {
        if (requestCode == SMS_PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // If permission is granted, send the SMS
            sendSmsMessage(phoneNumber, message);
        } else {
            Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    // Function to send SMS
    private static void sendSmsMessage(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(null, "SMS Sent!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(null, "Failed to send SMS", Toast.LENGTH_SHORT).show();
        }
    }
}
