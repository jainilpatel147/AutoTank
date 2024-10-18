package com.example.autotank;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserFunc {

    public interface UserFuncCallback {
        void onResult(boolean isSuccess);
    }

    private static ApiHandler handler = new ApiHandler();
    private static String userCreationUrl = "http://192.168.29.194:5000/api/users/register";
    private static String getUsersUrl= "http://192.168.29.194:5000/api/users/me";
    private static String validateUserUrl= "http://192.168.29.194:5000/api/users/login";
    private static String updateUserUrl = "http://192.168.29.194:5000/api/users/register";
    private static String DeleteUserUrl = "http://192.168.29.194:5000/api/users/register";

    public static void createUser(Context context, String name, String email, String password,UserFuncCallback callback) throws JSONException {
        handler.construct(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String res = "";
        handler.sendPostRequest(userCreationUrl,jsonObject,context, result -> {

            if (result.equals("true")) {
                callback.onResult(true);
            } else if (result.equals("false")) {
                callback.onResult(false);
            } else {
                callback.onResult(false);
            }
        });

    }

    public  static  void validateUser(Context context,String name,String password,UserFuncCallback callback) throws JSONException{
        handler.construct(context);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
            jsonObject.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String res ="";
        handler.sendPostRequest(validateUserUrl,jsonObject,context,result -> {
            if (result.equals("true")) {
                callback.onResult(true);
            } else if (result.equals("false")) {
                callback.onResult(false);
            } else {
                callback.onResult(false);
            }
        });
//        Toast.makeText(context,  res, Toast.LENGTH_SHORT).show();

    }
}
