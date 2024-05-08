package com.hassan.sehtakamanah.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGetQuestions {


    //declaration
    private static RetrofitGetQuestions instance = null;
    private APIServices myAPIService;

    //getInstance to provide a singleton pattern(that's why we define the constructor as private).
    private RetrofitGetQuestions() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.GET_QUES)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(APIServices.class);

    }

    //initialization RetrofitGetQuestions class only from this method
    public static synchronized RetrofitGetQuestions getInstance() {
        if (instance == null) {
            instance = new RetrofitGetQuestions();
        }
        return instance;
    }

    //get instance from APIServices to call (getQuestions method)
    public APIServices getMyApi() {
        return myAPIService;
    }
}
