package com.example.autotank;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DeviceFunc {

    public interface DeviceFuncCallback {
        void onResult(String isSuccess);
    }

    private static ApiHandler handler = new ApiHandler();
    private static String deviceCreationUrl = "http://192.168.29.194:5000/api/devices/register";
    private static String getDeviceByIdUrl= "http://192.168.29.194:5000/api/devices";

    private static String updateUserUrl = "http://192.168.29.194:5000/api/users/register";
    private static String DeleteUserUrl = "http://192.168.29.194:5000/api/users/register";

    public static void createDevice(Context context, String name, String height, String userid,DeviceFuncCallback callback) throws JSONException {
        handler.construct(context);
        Integer height_1 = Integer.parseInt(height);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("height", height_1);
            jsonObject.put("user", userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String res = "";
        handler.sendPostRequest(deviceCreationUrl,jsonObject,context, result -> {
            if (result.equals("true")) {
                callback.onResult("true");
            } else if (result.equals("false")) {
                callback.onResult("false");
            } else {
                callback.onResult("false");
            }
        });
    }
    public static  <T> void fetchDeviceById(Context context,String userid,String reqfield, ArrayList<T> arrayList, ArrayAdapter<T> adapter , DeviceFuncCallback callback){
        handler.construct(context);
        handler.fetchtolist(getDeviceByIdUrl,userid,reqfield,arrayList,adapter,result -> {
            if (result.equals("false")) {
                callback.onResult("false");
            } else if (!result.equals("true")) {
                callback.onResult(result);
            } else {
                callback.onResult("false");
            }
        });
    }
    public static  void fetchUserId(Context context, String name, String reqfield, UserFunc.UserFuncCallback callback) throws JSONException{
        handler.construct(context);
        handler.fetchSingleVal(context,getDeviceByIdUrl,name,reqfield,result -> {
            if (result.equals("false")) {
                callback.onResult("false");
            } else if (!result.equals("true")) {
                callback.onResult(result);
            } else {
                callback.onResult("false");
            }
        });
    }
}
