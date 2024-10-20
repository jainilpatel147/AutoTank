package com.example.autotank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ApiHandler {
    private RequestQueue requestQueue;

    public interface VolleyCallback {
        void onSuccess(String result);
    }
    // Initialize the requestQueue with the context
    public void construct(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void fetchSingleVal(Context context,String url, String name, String reqfield, VolleyCallback callback){
        String final_url = url + "/" +name;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, final_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String name = jsonObject.getString(reqfield);
                                // Assuming 'height' is a string field
                                callback.onSuccess(name);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       callback.onSuccess("false");
                    }
                }
        );

        // Add the request to the RequestQueue for asynchronous execution
        requestQueue.add(jsonArrayRequest);
    }
    public <T> void fetchtolist(String url, String user ,String reqField, ArrayList<T> arrayList, ArrayAdapter<T> adapter , VolleyCallback callback) {
        String final_url = url + "/" +user;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, final_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String name = jsonObject.getString(reqField);
                                arrayList.add((T) name);// Assuming 'height' is a string field

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                        callback.onSuccess("true");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            arrayList.add((T) error.getMessage());
                            adapter.notifyDataSetChanged();
                            callback.onSuccess("false");
                    }
                }
        );

        // Add the request to the RequestQueue for asynchronous execution
        requestQueue.add(jsonArrayRequest);
    }
    public void sendPostRequest(String url, JSONObject jsonObject, Context context ,VolleyCallback callback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean name = response.getBoolean("result");
                            Toast.makeText(context, "Message " + name, Toast.LENGTH_SHORT).show();
                            if (name) {
                                callback.onSuccess("true");
                            } else {
                                callback.onSuccess("false");
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
                callback.onSuccess("error");
            }
        });

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}



