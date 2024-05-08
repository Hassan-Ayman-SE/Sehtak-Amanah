package com.hassan.sehtakamanah.apis;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUpdate {

    //declaration
	private static RetrofitUpdate instance = null;
    private APIServices myAPIService;

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    private RetrofitUpdate() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.UPDATE_INFO)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(APIServices.class);

    }

    //initialization RetrofitUpdate class only from this method
    public static synchronized RetrofitUpdate getInstance() {
        if (instance == null) {
            instance = new RetrofitUpdate();
        }
        return instance;
    }

    //get instance from APIServices to call (updateUser method)
    public APIServices getMyApi() {
        return myAPIService;
    }

}
