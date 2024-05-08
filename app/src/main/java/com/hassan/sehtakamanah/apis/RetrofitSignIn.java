package com.hassan.sehtakamanah.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSignIn {

    //declaration
    private static RetrofitSignIn instance = null;
    private APIServices myAPIService;

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    private RetrofitSignIn() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.SIGN_IN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(APIServices.class);

    }

    //initialization RetrofitSignIn class only from this method
    public static synchronized RetrofitSignIn getInstance() {
        if (instance == null) {
            instance = new RetrofitSignIn();
        }
        return instance;
    }

    //get instance from APIServices to call (userLogin method)
    public APIServices getMyApi() {
        return myAPIService;
    }

}


