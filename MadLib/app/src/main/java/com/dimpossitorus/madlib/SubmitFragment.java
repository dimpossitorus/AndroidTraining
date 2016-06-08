package com.dimpossitorus.madlib;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubmitFragment.OnSubmitFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SubmitFragment extends Fragment implements NewStoryDialogFragment.NewStoryDialogListener {

    private OnSubmitFragmentInteractionListener mListener;
    private Story story;
    private int counter = -1; //So that when starting first, the value become 0;
    private static String DEBUG_TAG = "DEBUG";
    private String filename="";
    private TextView remainInfo;
    private TextView wordType;
    private AssetManager assetManager;

    public SubmitFragment() {
        // Required empty public constructor
    }

    public Story getStory() {
        return story;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSubmitFragmentInteractionListener) {
            mListener = (OnSubmitFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSubmitFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submit, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final View view = getView();


        remainInfo = (TextView) view.findViewById(R.id.remainInfo);

        wordType = (TextView) view.findViewById(R.id.wordType);

        assetManager = getActivity().getAssets();

        final EditText input = (EditText) view.findViewById(R.id.input);

        Button submit = (Button) view.findViewById(R.id.submitButton);
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
                        Toast.makeText(getActivity(),"Please give input, do not empty",Toast.LENGTH_LONG).show();
                    }
                }
                else if (story.getPlaceholderRemainingCount()==1 && input.getText()!=null) {
                    if (!"".equals(input.getText().toString())) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromInputMethod(view.getWindowToken(),0);
                        story.fillInPlaceholder(input.getText().toString());
                        Log.d(DEBUG_TAG, story.getPlaceholderRemainingCount()+"");
                        input.setText(null);
                        //Start the other fragment here
                        mListener.startStoryFragment(story);
                    }
                    else {
                        Toast.makeText(getActivity(),"Please give input, do not empty",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });


        Button nextStory = (Button) view.findViewById(R.id.nextStory);
        nextStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewStory();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        newStory();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
        newStory.setTargetFragment(SubmitFragment.this,0);
        newStory.show(getFragmentManager(),"dialog_new_story");
        Log.d("DEBUG", "Create New Story Dialog");
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSubmitFragmentInteractionListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(Uri uri);
        void startStoryFragment(Story story);
    }
}
