package com.dimpossitorus.madlib;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by Dimpos Sitorus on 03/06/2016.
 */
public class NewStoryDialogFragment extends DialogFragment {

    public interface NewStoryDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    NewStoryDialogListener mListener;

    public NewStoryDialogFragment(){
        Log.d("DEBUG", "NewStoryDialogFragment Constructor");
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            // If the DialogFragment is called from an activity
            // then the Interface must be instantiate by casting the Activity
            //mListener = (NewStoryDialogListener) activity;

            // If the DialogFragment is called from a fragment
            // then the Interface must be instantiate by using getTargetFragment
            mListener = (NewStoryDialogListener) getTargetFragment();
            Log.d("DEBUG", "Attach Successful");
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+" must implement New Story Dialog Listener");
        }

    }

    @Override
    public Dialog onCreateDialog (Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Set Custom Layout
        //LayoutInflater inflater = getActivity().getLayoutInflater();

        //View view = inflater.inflate(R.layout.dialog_new_story,null);

        //builder.setView(view);
/*
        Button yesButton = (Button) view.findViewById(R.id.yes);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDialogPositiveClick(NewStoryDialogFragment.this);
            }
        });

        Button noButton = (Button) view.findViewById(R.id.no);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDialogNegativeClick(NewStoryDialogFragment.this);
            }
        });
        */
        //Set Title
        builder.setTitle(R.string.dialogtitle);

        // Set Positive Button Listener
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogPositiveClick(NewStoryDialogFragment.this);
            }
        });

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogNegativeClick(NewStoryDialogFragment.this);
            }
        });


        return builder.create();
    }

}
