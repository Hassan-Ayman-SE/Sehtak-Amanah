package com.hassan.sehtakamanah.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputLayout;
import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.activities.MainActivity;
import com.hassan.sehtakamanah.apis.RetrofitUpdate;
import com.hassan.sehtakamanah.model.Result;
import com.hassan.sehtakamanah.model.User;
import com.hassan.sehtakamanah.sharedPreferences.SharedPreferencesManager;

import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    //declaration
    TextInputLayout tName, tPassword, tEmail, tPhone;
    Button btnUpdate;
    CircleImageView imageView;//profile Image
    User user;
    String username, email,phone, password;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile_fragment, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //initialization
        tName =  getView().findViewById(R.id.textInputUsernameP);
        tEmail = getView().findViewById(R.id.textInputLayoutEmailP);
        tPassword = getView().findViewById(R.id.textInputPasswordP);
        tPhone = getView().findViewById(R.id.textInputPhoneP);
        imageView = getView().findViewById(R.id.imageView);

        //set image profile
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImagePicker.with(ProfileFragment.this)
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });


        //get the Info of current user
        user = SharedPreferencesManager.getInstance(getContext()).getUsers();
        tName.getEditText().setText(user.getName());
        tEmail.getEditText().setText(user.getEmail());
        tPassword.getEditText().setText(user.getPassword());
        tPhone.getEditText().setText(user.getPhone());

        btnUpdate = getView().findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!userNameValidate() || !emailValidate() || !phoneValidate()
                        || !passwordValidate() ){
                    return;
                }else {
                    updateUserInfo();
                }

            }
        });
    }

    //update user info
    private void updateUserInfo() {


        Call<Result> call = RetrofitUpdate.getInstance().getMyApi().updateUser(user.getId(),tName.getEditText().getText().toString(),tEmail.getEditText().getText().toString(),tPassword.getEditText().getText().toString(),tPhone.getEditText().getText().toString());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body().getError() == true || response.body().getUser().getName() == null) {
                    Toast.makeText(getContext(), "response msg ---> " + response.body().getMessage(), Toast.LENGTH_LONG).show();

                } else {


                    User user = new User(response.body().getUser().getId(), response.body().getUser().getName(), response.body().getUser().getEmail(), response.body().getUser().getPassword(), response.body().getUser().getPhone());
                    //storing the user in shared preferences
                    SharedPreferencesManager.getInstance(getContext()).userUpdate(user);
                    //reload the info of navigation drawer header
                    MainActivity obj = new MainActivity();
                    obj.setHeaderInfo();

                    Toast.makeText(getContext(), "response msg ---> " + response.body().getMessage(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getContext(), "something goes wrong!! ===> "+t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Error 102 --> ",t.getMessage());

            }
        });


    }//end updateUserInfo()


    //check userName text field
    boolean userNameValidate()
    {
        username = tName.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            tName.setError(getResources().getString(R.string.empty));
            return false;
        }
        else if (username.length() > 30 )
        {
            tName.setError(getResources().getString(R.string.character30));
            return false;
        }
        else {
            tName.setErrorEnabled(false);
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

    @Override//for set imageProfile
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imageView.setImageURI(uri);
    }


}