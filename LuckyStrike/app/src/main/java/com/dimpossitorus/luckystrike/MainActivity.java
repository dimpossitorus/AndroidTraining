package com.dimpossitorus.luckystrike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;
    private int userInput;
    private final String INFO_TAG = "INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random random = new Random();

        randomNumber = random.nextInt(1000)+1;

        final TextView number = (TextView) findViewById(R.id.number);
        if (number != null) {
            number.setText(String.valueOf(randomNumber));
        }

        final EditText input = (EditText) findViewById(R.id.input);

        Button submit = (Button) findViewById(R.id.submit);

        if (submit != null) {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (input != null && input.getText() != null) {
                        userInput = Integer.parseInt(input.getText().toString());
                    }
                    else {
                        userInput=0;
                        Log.i(INFO_TAG, "Input is Empty");
                    }

                    if (userInput<randomNumber) {
                        Toast.makeText(getApplicationContext(),"Your input is smaller than the answer", Toast.LENGTH_LONG).show();
                    }
                    else if (userInput>randomNumber) {
                        Toast.makeText(getApplicationContext(),"Your input is bigger than the answer", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Your answer is correct. Congratulations!", Toast.LENGTH_LONG).show();
                        if (number != null) {
                            number.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }

        Button restart = (Button) findViewById(R.id.restartGame);

        if (restart!= null) {
            restart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void checkAnswer (int input, int answer, TextView result) {
        if (input<answer) {
            Toast.makeText(getApplicationContext(),"Your input is smaller than the answer", Toast.LENGTH_LONG).show();
        }
        else if (input>answer) {
            Toast.makeText(getApplicationContext(),"Your input is bigger than the answer", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Your answer is correct. Congratulations!", Toast.LENGTH_LONG).show();
            result.setVisibility(View.VISIBLE);
        }
    }
}
