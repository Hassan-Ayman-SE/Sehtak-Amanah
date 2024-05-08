package com.hassan.sehtakamanah.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSignUp {

    //declaration
    private static RetrofitSignUp instance = null;
    private APIServices myAPIService;

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    private RetrofitSignUp() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.SIGN_UP)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(APIServices.class);

    }

    //initialization RetrofitSignUp class only from this method
    public static synchronized RetrofitSignUp getInstance() {
        if (instance == null) {
            instance = new RetrofitSignUp();
        }
        return instance;
    }

    //get instance from APIServices to call (insertNewUser method)
    public APIServices getMyApi() {
        return myAPIService;
    }

}
