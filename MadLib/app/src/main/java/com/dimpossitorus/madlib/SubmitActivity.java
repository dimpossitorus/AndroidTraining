package com.dimpossitorus.madlib;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SubmitActivity extends AppCompatActivity implements NewStoryDialogFragment.NewStoryDialogListener {

    private Intent intent;
    private Story story;
    private int counter = -1; //So that when starting first, the value become 0;
    private static String DEBUG_TAG = "DEBUG";
    private String filename="";
    private TextView remainInfo;
    private TextView wordType;
    private AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        remainInfo = (TextView) findViewById(R.id.remainInfo);

        wordType = (TextView) findViewById(R.id.wordType);

        assetManager = getAssets();

        final EditText input = (EditText) findViewById(R.id.input);

        Button submit = (Button) findViewById(R.id.submitButton);
        intent = new Intent(SubmitActivity.this, StoryActivity.class);

        if (submit != null) {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // check counter
                    // if not zero then repeat receiving input from EditText
                    if (story.getPlaceholderRemainingCount()>1 && input.getText()!=null) {
                        if (!"".equals(input.getText().toString())) {
                            story.fillInPlaceholder(input.getText().toString());
                            Log.d(DEBUG_TAG, story.getPlaceholderRemainingCount()+"");
                            input.setText(null);
                            wordType.setText("Please type a/an " + story.getNextPlaceholder());
                            if (story.getPlaceholderRemainingCount()>1) {
                                remainInfo.setText(story.getPlaceholderRemainingCount()+" words left");
                            }
                            else {
                                remainInfo.setText(story.getPlaceholderRemainingCount()+" word left");
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Please give input, do not empty",Toast.LENGTH_LONG).show();
                        }
                    }
                    else if (story.getPlaceholderRemainingCount()==1 && input.getText()!=null) {
                        if (!"".equals(input.getText().toString())) {
                            story.fillInPlaceholder(input.getText().toString());
                            Log.d(DEBUG_TAG, story.getPlaceholderRemainingCount()+"");
                            input.setText(null);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("Story", story);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Please give input, do not empty",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }

        Button nextStory = (Button) findViewById(R.id.nextStory);
        nextStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load next story
                onClickNewStory();
                /*new AlertDialog.Builder(getApplicationContext()).setTitle("Load New Story?").setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newStory();
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do Nothing
                    }
                });*/
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        newStory();

    }

    /**
     * Description : Load next story from the assets.
     */
    public void newStory() {
        if (counter<4) {
            counter++;
        }
        else {
            counter=0;
        }
        filename="madlib"+counter+".txt";
        try {
            InputStream inputStream = assetManager.open(filename);
            story = new Story(inputStream);
            //wordType.setText(story.toString());
            //Log.d(DEBUG_TAG, story.toString());
            //Log.d(DEBUG_TAG, story.getNextPlaceholder());
            if (story.getPlaceholderRemainingCount()>1) {
                remainInfo.setText(story.getPlaceholderRemainingCount()+" words left");
            }
            else {
                remainInfo.setText(story.getPlaceholderRemainingCount()+" word left");
            }
            wordType.setText("Please input a/an "+story.getNextPlaceholder());

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickNewStory() {
        //FragmentManager fragmentManager = getSupportFragmentManager();
        DialogFragment newStory = new NewStoryDialogFragment();
        //newStory.show(fragmentManager,"dialog_new_story");
        newStory.show(getSupportFragmentManager(),"dialog_new_story");
        Log.d(DEBUG_TAG, "Create New Story Dialog");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        newStory();
        dialog.dismiss();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //do nothing
        dialog.dismiss();
    }
}
