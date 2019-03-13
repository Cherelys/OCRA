package com.ocra.dwarbi.ocra;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
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
import com.ocra.dwarbi.ocra.Fragments.AboutFragment;
import com.ocra.dwarbi.ocra.Fragments.MainFragment;
import com.ocra.dwarbi.ocra.Fragments.FormulierFragment;
import com.ocra.dwarbi.ocra.Fragments.SettingsFragment;
import com.ocra.dwarbi.ocra.login.LoginActivity;

public class Mainactivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


        ArrayAdapter<CharSequence> adapter;
        FormulierFragment forFrag;
        String username;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = getIntent().getStringExtra("username");
        /*spinner=(Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.bouwwerk_items,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

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
        forFrag=new FormulierFragment();
        fn.beginTransaction().replace(R.id.main_content, forFrag).commit();
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

        forFrag.addFormulier();


        }

       public void viewEvent(View view){
        DatabaseHelper dbh =new DatabaseHelper(this);
        User user = dbh.getUserByName(username);
        int id= user.getId();
        Formulier formulier= dbh.getFormulierByUserID(id);

        Bundle bundle=new Bundle();
        bundle.putString("diensten",formulier.getDienst());
        bundle.putString("eisen",formulier.getEisen());
        bundle.putDouble("budget",formulier.getBudget());
        bundle.putString("bouwwerk",formulier.getBouwwerk());

        FormulierInfo formulierInfo=new FormulieInfo();
        formulierInfo.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,formulierInfo).addToBackStack("jojo").commit();
      }

    }



