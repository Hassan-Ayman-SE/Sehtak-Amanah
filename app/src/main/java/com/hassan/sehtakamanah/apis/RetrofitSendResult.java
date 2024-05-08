package com.hassan.sehtakamanah.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSendResult {

    //declaration
    private static RetrofitSendResult instance = null;
    private APIServices myAPIService;

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    private RetrofitSendResult() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.SEND_RESULT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(APIServices.class);

    }

    //initialization RetrofitSendResult class only from this method
    public static synchronized RetrofitSendResult getInstance() {
        if (instance == null) {
            instance = new RetrofitSendResult();
        }
        return instance;
    }

    //get instance from APIServices to call (sendResult method)
    public APIServices getMyApi() {
        return myAPIService;
    }
}
