package com.dimpossitorus.madlib;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.OnWelcomeFragmentInteractionListener, SubmitFragment.OnSubmitFragmentInteractionListener {

    private Fragment welcomeFragment;
    private Fragment submitFragment;
    private Fragment storyFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //Navigation Drawer Component
    private ListView mDrawerList;
    private ArrayAdapter<String> adapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout drawerLayout;
    private String activityTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        // Check whether the app is restarted or not
        if(savedInstanceState==null) {
            welcomeFragment = new WelcomeFragment();
            fragmentTransaction.replace(R.id.mainActivity, welcomeFragment);
            fragmentTransaction.commit();
        }
        else {
            fragmentTransaction.replace(R.id.mainActivity, getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getBackStackEntryCount()));
            fragmentTransaction.commit();
        }



        //submitFragment = new SubmitFragment();

        //storyFragment = new StoryFragment();

        /*
        Button start = (Button) findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(intent);
            }
        });
        */

        //Part to set up the navigation drawer
        mDrawerList = (ListView) findViewById(R.id.drawerList);
        String[] osArray = { "Story 1", "Story 2", "Story 3", "Story 4", "Story 5" };
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        activityTitle = getTitle().toString();

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened (View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(activityTitle);
                invalidateOptionsMenu();
            }

        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        mDrawerToggle.onConfigurationChanged(configuration);
    }

    //WelcomeFragment Callback
    @Override
    public void onStartClicked(View view) {
        //welcomeFragment.
        fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.remove(welcomeFragment);
        //fragmentTransaction.add(submitFragment, "submitFragment");
        submitFragment = new SubmitFragment();
        fragmentTransaction.replace(R.id.mainActivity, submitFragment);
        fragmentTransaction.addToBackStack(null);
        Toast.makeText(this, "Start Button Clicked, starting the SubmitFragment", Toast.LENGTH_LONG).show();
        fragmentTransaction.commit();
    }


    //SubmitFragment Callback
    @Override
    public void startStoryFragment(Story story) {
        fragmentTransaction = fragmentManager.beginTransaction();
        Bundle args = new Bundle();
        args.putSerializable("Story", story);
        storyFragment = new StoryFragment();
        storyFragment.setArguments(args);
        fragmentTransaction.replace(R.id.mainActivity,storyFragment);
        fragmentTransaction.addToBackStack(null);
        Toast.makeText(this, "Finish filling the blank, starting the StoryFragment", Toast.LENGTH_LONG).show();
        fragmentTransaction.commit();
    }
}
