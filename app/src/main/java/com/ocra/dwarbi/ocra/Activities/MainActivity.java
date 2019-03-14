package com.ocra.dwarbi.ocra.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import com.ocra.dwarbi.ocra.Database.DatabaseHelper;
import com.ocra.dwarbi.ocra.Entities.User;
import com.ocra.dwarbi.ocra.Formulier;
import com.ocra.dwarbi.ocra.Fragments.AboutFragment;
import com.ocra.dwarbi.ocra.Fragments.FormulierFragment;
import com.ocra.dwarbi.ocra.Fragments.MainFragment;
import com.ocra.dwarbi.ocra.Fragments.SettingsFragment;
import com.ocra.dwarbi.ocra.R;
import com.ocra.dwarbi.ocra.login.LoginActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    ArrayAdapter<CharSequence> adapter;
    FormulierFragment formulierFrag;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = getIntent().getStringExtra("username");


        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fn = getFragmentManager();
        fn.beginTransaction().replace(R.id.main_content, new MainFragment()).commit();


    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fn = getFragmentManager();


        int id = item.getItemId();

        switch (id){
            case R.id.nav_home:
                fn.beginTransaction().replace(R.id.main_content, new MainFragment()).commit();
                break;

            case R.id.nav_formulier:
                formulierFrag=new FormulierFragment();
                fn.beginTransaction().replace(R.id.main_content, formulierFrag).commit();
                break;
            case R.id.nav_about:
                fn.beginTransaction().replace(R.id.main_content, new AboutFragment()).commit();
                break;
            case R.id.nav_settings:
                fn.beginTransaction().replace(R.id.main_content, new SettingsFragment()).commit();
                break;
            case R.id.nav_log_out:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addEvent(View view){

        formulierFrag.addFormulier();


    }

    public void viewEvent(View view){
        DatabaseHelper db=new DatabaseHelper(this);
        User user =db.getUserByName(username);
        int id= user.getId();
        Formulier formulier= db.getFormulierByUserID(id);

        Bundle bundle=new Bundle();
        bundle.putString("phone",formulier.getPhone());
        bundle.putString("birthday",formulier.getBirthday());
        bundle.putString("gender",formulier.getGender());
        bundle.putString("birthplace",formulier.getBirthplace());
        bundle.putString("email",formulier.getEmail());
        bundle.putString("previouseducation",formulier.getPreviouseducation());
        bundle.putString("major",formulier.getMajor());

        FormulierInfo formulierInfo=new FormulierInfo();
        formulierInfo.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,formulierInfo).addToBackStack("jojo").commit();
    }

}



