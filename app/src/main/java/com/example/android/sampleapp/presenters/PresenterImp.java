package com.example.android.sampleapp.presenters;

import android.content.Context;

import com.example.android.sampleapp.interfaces.Callback;
import com.example.android.sampleapp.models.ResponseModel;
import com.example.android.sampleapp.utils.APIManager;
import com.example.android.sampleapp.utils.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by Mohamed Yasser on 12/1/2017.
 */

public class PresenterImp implements Presenter {

    private Callback callback;
    private RetrofitInitializer retrofitInitializer;

    public PresenterImp(Callback callback, RetrofitInitializer retrofitInitializer){
        this.callback = callback;
        this.retrofitInitializer = retrofitInitializer;
    }

    @Override
    public void getMenuItems() {
        APIManager apiManager = retrofitInitializer.retrofit.create(APIManager.class);
        Call<ResponseModel> call = apiManager.getMenuItems();
        call.enqueue(new retrofit2.Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onError(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }
}
