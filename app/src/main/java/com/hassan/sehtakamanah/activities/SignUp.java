package com.hassan.sehtakamanah.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.apis.RetrofitSignUp;
import com.hassan.sehtakamanah.model.Result;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    // declaration
    TextInputLayout tUsername, tEmail, tPhone, tPassword, tRePassword;
    Button btnSignUp, btnBack;
    String username, email,phone, password, rePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // initialization
        tUsername = findViewById(R.id.textInputUsername);
        tEmail = findViewById(R.id.textInputLayoutEmail);
        tPassword = findViewById(R.id.textInputPassword);
        tPhone = findViewById(R.id.textInputPhone);
        tRePassword = findViewById(R.id.textInputRePassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnBack = findViewById(R.id.btnBack);

        //Action for Buttons
        btnSignUp.setOnClickListener(this::isClicked);
        btnBack.setOnClickListener(this::isClicked);
    }


    private void insertNewUser(){

        //insert new user
        Call<Result> call = RetrofitSignUp.getInstance().getMyApi().insertNewUser(username, password, email, phone);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                //error message
                if(response.body().getError() == true){

                    Log.d("something goes wrong --- > ", response.body().getMessage());
                    Toast.makeText(SignUp.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                //No error Added new user successfully and go to SignIn Activity
                }else if(response.body().getError()==false){

                    Log.d("Response ---> ","User registered successfully");

                    Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext(),SignIn.class));


                }



            }

            //Failed to Insert Data
            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Log.d("Failed to Insert Data ---> ",t.getMessage());
                Toast.makeText(getApplicationContext(),"Failed to Insert Data --> "+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }//End insertNewUser method


    //check userName text field
    boolean userNameValidate()
    {
        username = tUsername.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            tUsername.setError(getResources().getString(R.string.empty));
            return false;
        }
        else if (username.length() > 30 )
        {
            tUsername.setError(getResources().getString(R.string.character30));
            return false;
        }
        else {
            tUsername.setErrorEnabled(false);
            return true;
        }
    }//End userNameValidate()

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
        }
        else {
            tEmail.setErrorEnabled(false);
            return true;
        }
    }//End emailValidate()


    //check phone text field
    boolean phoneValidate()
    {
        phone = tPhone.getEditText().getText().toString().trim();
        if (phone.isEmpty()) {
            tPhone.setError(getResources().getString(R.string.empty));
            return false;
        }
        else if (phone.length() != 9)
        {
            tPhone.setError(getResources().getString(R.string.number9));

            return false;
        }
        else if (!checkPhone())
        {
            tPhone.setError(getResources().getString(R.string.invalidPhone));
            return false;
        }
        else {
            tPhone.setErrorEnabled(false);
            return true;
        }
    }//End phoneValidate()



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


    //check re-password text field
    boolean rePasswordValidate()
    {
        rePassword = tRePassword.getEditText().getText().toString().trim();
        if (!rePassword.equals(password)) {
            tRePassword.setError(getResources().getString(R.string.notMatch));
            return false;
        }
        else {
            tRePassword.setErrorEnabled(false);
            return true;
        }
    }//End rePasswordValidate()





    //check phone number format
    private boolean checkPhone()
    {
        String beginPhone = phone.substring(0,2);
        return beginPhone.equals("77") || beginPhone.equals("78") || beginPhone.equals("79");
    }

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
    private void isClicked(View view) {
        switch (view.getId()){

            case R.id.btnSignUp:
                if (!userNameValidate() || !emailValidate() || !phoneValidate()
                        || !passwordValidate() || !rePasswordValidate()){
                    return;
                }else {
                    insertNewUser();
                }
                break;
            case R.id.btnBack:
                finish();

        }
    }
}