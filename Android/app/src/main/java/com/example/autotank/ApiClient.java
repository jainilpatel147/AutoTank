package com.example.autotank;

import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ApiClient {

    // Define the base URL of your API
    private static final String BASE_URL = "https://example.com/api/"; // Replace with your API base URL
    private static Retrofit retrofit = null;

    // Create Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // Convert JSON response to Java objects
                    .build();
        }
        return retrofit;
    }

    // Define the API interface
    public interface ApiService {
        // Define a GET request to your specific endpoint
        @GET("https://jsonplaceholder.typicode.com/posts") // Replace with your actual endpoint
        Call<ResponseBody> getDynamicResponse(); // Using ResponseBody for dynamic responses
    }

    // Create a method to return the API service interface
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}