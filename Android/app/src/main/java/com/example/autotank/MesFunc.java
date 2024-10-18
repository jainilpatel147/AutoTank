package com.example.autotank;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MesFunc {

    public interface UserFuncCallback {
        void onResult(String isSuccess);
    }

    private static ApiHandler handler = new ApiHandler();
    private static String userCreationUrl = "http://192.168.29.194:5000/api/users/register";
    private static String getMesByDevId= "http://192.168.29.194:5000/api/measurements";
    private static String validateUserUrl= "http://192.168.29.194:5000/api/users/login";
    private static String updateUserUrl = "http://192.168.29.194:5000/api/users/register";
    private static String DeleteUserUrl = "http://192.168.29.194:5000/api/users/register";

    public static  void fetchMesByDevId(Context context, String name, String reqfield, UserFunc.UserFuncCallback callback) throws JSONException{
        handler.construct(context);
        handler.fetchSingleVal(context,getMesByDevId,name,reqfield,result -> {
            if (result.equals("false")) {
                callback.onResult("false");
            } else if (!result.equals("true")) {
                callback.onResult(result);
            } else {
                callback.onResult("false");
            }
        });
    }
    public static  <T> void fetchDeviceById(Context context, String userid, String reqfield, ArrayList<T> arrayList, ArrayAdapter<T> adapter , DeviceFunc.DeviceFuncCallback callback){
        handler.construct(context);
        handler.fetchtolist(getMesByDevId,userid,reqfield,arrayList,adapter,result -> {
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
