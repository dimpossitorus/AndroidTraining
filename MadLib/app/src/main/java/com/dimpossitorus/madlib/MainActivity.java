package com.dimpossitorus.madlib;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WelcomeFragment.OnWelcomeFragmentInteractionListener, SubmitFragment.OnSubmitFragmentInteractionListener {

    private Fragment welcomeFragment;
    private Fragment submitFragment;
    private Fragment storyFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


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
    }

    @Override
    public void onResume() {
        super.onResume();

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
