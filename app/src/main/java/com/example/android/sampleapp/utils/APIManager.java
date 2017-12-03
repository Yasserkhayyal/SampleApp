package com.example.android.sampleapp.utils;

import com.example.android.sampleapp.models.ResponseModel;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Mohamed Yasser on 12/1/2017.
 */

public interface APIManager {

    @GET(Constants.GET_ITEMS_URL)
    Call<ResponseModel> getMenuItems();
}
