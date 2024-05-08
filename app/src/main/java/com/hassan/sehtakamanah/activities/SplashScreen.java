package com.hassan.sehtakamanah.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.sharedPreferences.SharedPreferencesManager;

public class SplashScreen extends AppCompatActivity {

    //Declaration
    Animation animationZoom;
    ImageView imageView;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // remove the title from splash screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        //initialization
        animationZoom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
        imageView = findViewById(R.id.image);
        imageView.startAnimation(animationZoom);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Check if not logged in go to SignIn Page
                if (!SharedPreferencesManager.getInstance(getApplicationContext()).isLoggedIn()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), SignIn.class));

                //go to MainActivity
                }else{
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();}
            }
        }, 4000);

    }
}