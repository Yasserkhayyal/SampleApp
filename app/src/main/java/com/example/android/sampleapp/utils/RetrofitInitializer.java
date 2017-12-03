package com.example.android.sampleapp.utils;

import android.content.Context;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohamed Yasser on 12/2/2017.
 */

public class RetrofitInitializer {


    public Retrofit retrofit;

    public RetrofitInitializer(Context context) {
        init(context);
    }

    private void init(final Context context){
        Interceptor rewriteCacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                String cacheHeaderValue = new NetworkUtils(context).isConnectedToInternet()
                        ? "public, max-age=600"
                        : "public, only-if-cached, max-stale=2419200" ;
                Request request = originalRequest.newBuilder().build();
                Response response = chain.proceed(request);
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", cacheHeaderValue)
                        .build();
            }
        };

        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(rewriteCacheInterceptor).cache(cache).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
