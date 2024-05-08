package com.hassan.sehtakamanah.apis;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDelete {

    //declaration
	private static RetrofitDelete instance = null;
    private APIServices myAPIService;

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    private RetrofitDelete() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.DELETE_USER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(APIServices.class);

    }

    //initialization RetrofitDelete class only from this method
    public static synchronized RetrofitDelete getInstance() {
        if (instance == null) {
            instance = new RetrofitDelete();
        }
        return instance;
    }

    //get instance from APIServices to call (deleteUser method)
    public APIServices getMyApi() {
        return myAPIService;
    }

}
