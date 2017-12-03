package com.example.android.sampleapp.interfaces;

import com.example.android.sampleapp.models.ResponseModel;

import okhttp3.ResponseBody;

/**
 * Created by Mohamed Yasser on 12/2/2017.
 */

public interface Callback {

    void onSuccess(ResponseModel response);
    void onError(ResponseBody errorResponse);
    void onFailure(Throwable throwable);
}
