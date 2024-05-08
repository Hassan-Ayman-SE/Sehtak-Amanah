package com.hassan.sehtakamanah.sharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.hassan.sehtakamanah.activities.SignIn;
import com.hassan.sehtakamanah.model.User;

public class SharedPreferencesManager {

    private static final String SHARED_PREF_NAME= "ESehtakSharedPreferences";
    private static final String KEY_ID= "keyId";
    private static final String KEY_USERNAME= "keyUsername";
    private static final String KEY_PHONE= "keyPhone";
    private static final String KEY_EMAIL= "keyEmail";
    private static final String KEY_PASSWORD= "keyPassword";


    private static SharedPreferencesManager mInstance;
    private static Context mCtx;

    //singleton
    private SharedPreferencesManager(Context context){
        mCtx = context;
    }

    //initialization SharedPreferencesManager class only from this method
    public static synchronized SharedPreferencesManager getInstance(Context context){
        if (mInstance == null){
            mInstance = new SharedPreferencesManager(context);
        }

        return mInstance;
    }

    //save user Info
    public void userLogin(User user){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_PHONE, user.getPhone());
        editor.apply();


    }

    //save user Info after update
    public void userUpdate(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_PHONE,user.getPhone());
        editor.apply();
    }

    //get user info from SharedPreferences
    public User getUsers(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getInt(KEY_ID, 0),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_PHONE, null)
        );
    }

    //check if user is logged in or not
    public boolean isLoggedIn(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;

    }

    //sign out
    public void logout(){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();

        Intent intent = new Intent(mCtx, SignIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mCtx.startActivity(intent);

    }
}
