package com.hassan.sehtakamanah.apis;

import com.hassan.sehtakamanah.model.Questions;
import com.hassan.sehtakamanah.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIServices {


    //the SignIn call
    @FormUrlEncoded
    @POST("checkUserLogin.php")
    Call<Result> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    //the SignUp Call
    @FormUrlEncoded
    @POST("insertNewUser.php")
    Call<Result> insertNewUser(
            @Field("name") String name,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone") String phone

    );

    //the Update Call
    @FormUrlEncoded
    @POST("updateUserInfo.php")
    Call<Result> updateUser(
            @Field("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    //the Delete Call
    @FormUrlEncoded
    @POST("deleteUserAccount.php")
    Call<Result> deleteUser(
            @Field("id") int id

    );

    //get Questions
    @GET("getQuestions.php")
    Call<List<Questions>> getQuestions();

    //send user result
    @FormUrlEncoded
    @POST("sendUserResult.php")
    Call<Result> sendResult(
            @Field("id") int id,
            @Field("result") int result

    );


}


