package com.hassan.sehtakamanah.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.apis.RetrofitSignIn;
import com.hassan.sehtakamanah.model.Result;
import com.hassan.sehtakamanah.model.User;
import com.hassan.sehtakamanah.sharedPreferences.SharedPreferencesManager;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity{

    // declaration
    private TextInputLayout tEmail, tPassword;
    private String email, password;
    private Button btnLogin;
    private TextView tvCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //initialization
        btnLogin = findViewById(R.id.btnLogin);
        tEmail = findViewById(R.id.textInputLayoutEmailLogin);
        tPassword = findViewById(R.id.textInputLayoutPasswordLogin);
        tvCreateAccount = findViewById(R.id.tvCreateAccount);

        //Action for Buttons
        btnLogin.setOnClickListener(this::isClick);
        tvCreateAccount.setOnClickListener(this::isClick);

    }


    //check email and password
    private void userLogIn() {

        Call<Result> call = RetrofitSignIn.getInstance().getMyApi().userLogin(email,password);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                //Email and Pass (correct)
                if (!response.body().getError()) {

                    //Go to MainActivity
                    Intent intent = new Intent(SignIn.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Welcome "+response.body().getUser().getName(),Toast.LENGTH_LONG).show();

                    //get user info
                    User user = new User((int)response.body().getUser().getId(),
                            response.body().getUser().getName(),
                            response.body().getUser().getEmail(),
                            response.body().getUser().getPassword(),
                            (String)response.body().getUser().getPhone());

                    //save user info in SharedPreferences Class
                    SharedPreferencesManager.getInstance(getApplicationContext()).userLogin(user);
                    finish();

                    //Invalid email or password
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email or password ", Toast.LENGTH_LONG).show();
                }

            }

            //something wrong (No response)
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Retrofit ERROR -->",t.getMessage());

            }
        });
    }


    //check password text field
    boolean passwordValidate()
    {
        password = tPassword.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            tPassword.setError(getResources().getString(R.string.empty));
            return false;
        }
        else if (password.length() != 8)
        {
            tPassword.setError(getResources().getString(R.string.eightCharacter));
            return false;
        }
        else {
            tPassword.setErrorEnabled(false);
            return true;
        }
    }//End PasswordValidate()

    //check email text field
    boolean emailValidate()
    {
        email = tEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            tEmail.setError(getResources().getString(R.string.empty));
            return false;
        }else if (!isValidEmailId(email)) {
            tEmail.setError(getResources().getString(R.string.invalidEmail));
            return false;
        }else {
            tEmail.setErrorEnabled(false);
            return true;
        }
    }//End emailValidate()

    //check email format
    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }



    //Action for Buttons
    public void isClick(View view) {

        switch (view.getId()){

            case R.id.btnLogin:
                if (!emailValidate() || !passwordValidate()){
                    return;
                }else {
                    userLogIn();
                }
                break;
            case R.id.tvCreateAccount:
                startActivity(new Intent(getApplicationContext(),SignUp.class));

        }

    }

}