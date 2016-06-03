package com.dimpossitorus.madlib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StoryActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        intent = getIntent();

        TextView storyText = (TextView) findViewById(R.id.story);
        Bundle bundle = intent.getExtras();
        Story story = (Story) bundle.getSerializable("Story");
        storyText.setText(story.toString());

        Button newStory = (Button) findViewById(R.id.newStory);
        newStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        });

    }
}
