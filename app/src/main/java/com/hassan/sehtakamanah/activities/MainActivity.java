package com.hassan.sehtakamanah.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.hassan.sehtakamanah.R;
import com.hassan.sehtakamanah.fragments.AboutUsFragment;
import com.hassan.sehtakamanah.fragments.BodyFragment;
import com.hassan.sehtakamanah.fragments.ContactUsFragment;
import com.hassan.sehtakamanah.fragments.MentalFragment;
import com.hassan.sehtakamanah.fragments.NutritionFragment;
import com.hassan.sehtakamanah.fragments.ProfileFragment;
import com.hassan.sehtakamanah.fragments.QuestionsFragment;
import com.hassan.sehtakamanah.fragments.SettingsFragment;
import com.hassan.sehtakamanah.fragments.ViewSectionFragment;
import com.hassan.sehtakamanah.sharedPreferences.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity {

    //Declaration
    FloatingActionButton btnFAB;//for Test section
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    BottomAppBar bottomAppBar;
    public static TextView navUsername, navEmail, navPhone;
    FragmentTransaction fragmentTransaction;
    int score = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialization
        drawerLayout =(DrawerLayout) findViewById(R.id.drawerLayout);
        btnFAB = findViewById(R.id.btnFAB);

        //toolBar + BottomAppBar
        toolbar =(Toolbar) findViewById(R.id.toolBar);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        setSupportActionBar(toolbar);
        setSupportActionBar(bottomAppBar);


        //get type(Mental or Body or Nutrition) from ViewSectionFragment depending on the clicked cardView in ViewSectionFragment
        String type = getIntent().getStringExtra("type");

        //if type is null this mean the user wants to access ViewSectionFragment
        if (type == null) {
            //Run ViewSectionFragment
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new ViewSectionFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        //if type is not null then we will open the selected section
        }else if (type.equals("Mental")){
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new MentalFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle(getResources().getString(R.string.mental));

        }else if (type.equals("Body")){
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new BodyFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle(getResources().getString(R.string.body));

        }else if (type.equals("Nutrition")){
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new NutritionFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            toolbar.setTitle(getResources().getString(R.string.nutrition));
        }

        //Run ViewSectionFragment by home icon in bottomAppBar
        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new ViewSectionFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                toolbar.setTitle(getResources().getString(R.string.home));

            }
        });



        //navigation drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView = (NavigationView) findViewById(R.id.navigationView);

        //define the header of navigation view
        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.name_nav);
        navEmail = (TextView) headerView.findViewById(R.id.email_nav);
        navPhone = (TextView) headerView.findViewById(R.id.phone_nav);

        //call method setHeaderInfo()
        setHeaderInfo();

        //actionListener for items in navigationDrawer to call (Fragments)
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_home:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new ViewSectionFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.home));
                        break;

                    case R.id.nav_profile:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new ProfileFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.profile));
                        break;

                    case R.id.nav_settings:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new SettingsFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.settings));
                        break;

                    case R.id.nav_about_us:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new AboutUsFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.about));
                        break;

                    case R.id.nav_contact_us:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,new ContactUsFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        toolbar.setTitle(getResources().getString(R.string.contact));
                        break;

                    case R.id.nav_log_out:
                        finish();
                        SharedPreferencesManager.getInstance(getApplicationContext()).logout();
                        break;



                }
                return true;
            }
        });

        //actionListener for btnFAB (go to questionFragment)
        btnFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new QuestionsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                toolbar.setTitle(getResources().getString(R.string.test));
            }
        });

        //get score from QuestionAdapter to use it
         score = getIntent().getIntExtra("score",-1);

         //if score was -1 this mean the user didn't do the exam therefor no result will be showed
        //else if the score was not -1 then show the result and the status using AlertDialog
        if (score != -1){
            String status = null;
            if (score <= 4)
                status = "No depression";
            else if (score >= 5 && score <= 9)
                status = "Mild depression";
            else if (score >= 10 && score <= 14)
                status = "Moderate depression";
            else if (score >= 15 && score <= 19)
                status = "Moderately severe depression";
            else if (score >= 20 && score <= 27)
                status = "Severe depression";

            //to customize the AlertDialog design we use Html
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(Html.fromHtml("<h1><font color='#F018786'>Your Result</font></h1>"));
            builder.setMessage(Html.fromHtml("<h1><font color='#FBB86FC'>Your Result is : "+score+
                    "</font></h1>"+"<h3>" + "<h3><font color='#FBB86FC'>---> Your status: "+status+"</font></h3>"));
            builder.setPositiveButton("OK", ((dialogInterface, i) -> finishTest()));
            builder.setCancelable(false);
            builder.show();


        }

    }//end of onCreate

    //if the user click on OK button in AlertDialog (show this Toast)
    private void finishTest() {
        Toast.makeText(this, "You can try again to get better result", Toast.LENGTH_SHORT).show();
    }


    @Override//actionListener for icons in bottomAppBar
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.profileBottom:
                //Call profileFragment
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new ProfileFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                toolbar.setTitle(getResources().getString(R.string.profile));
                break;
            case R.id.settingsBottom:
                //Call settingsFragment
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new SettingsFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                toolbar.setTitle(getResources().getString(R.string.settings));
                break;
        }

        return true;
    }

    //set userInfo to navigationHeader
    public void setHeaderInfo() {

        String name = SharedPreferencesManager.getInstance(MainActivity.this).getUsers().getName();
        String email = SharedPreferencesManager.getInstance(MainActivity.this).getUsers().getEmail();
        String phone = SharedPreferencesManager.getInstance(MainActivity.this).getUsers().getPhone();

        navUsername.setText(name);
        navPhone.setText(phone);
        navEmail.setText(email);

    }

    //set menu to bottomAppBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.list_item_bottom,menu);

        return true;
    }
}