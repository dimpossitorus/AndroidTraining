package com.dimpossitorus.materialtab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button simpleTabs = (Button) findViewById(R.id.simpleTabs);
        simpleTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimpleTabs.class);
                startActivity(intent);
            }
        });

        Button scrollableTabs = (Button) findViewById(R.id.scrollableTabs);
        scrollableTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScrollableTabs.class);
                startActivity(intent);
            }
        });

        Button iconTextTabs = (Button) findViewById(R.id.iconTextTabs);
        iconTextTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IconTextTabs.class);
                startActivity(intent);
            }
        });

        Button customTabs = (Button) findViewById(R.id.customTabs);
        customTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomTabs.class);
                startActivity(intent);
            }
        });
    }
}
