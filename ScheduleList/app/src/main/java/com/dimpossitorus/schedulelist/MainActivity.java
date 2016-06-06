package com.dimpossitorus.schedulelist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Vector<String> schedule = new Vector<String>(10,1);
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedule.add("Test");
        schedule.add("test2");

        adapter = new ArrayAdapter<String> (MainActivity.this, android.R.layout.simple_list_item_1, schedule);

        final ListView scheduleList = (ListView) findViewById(R.id.scheduleList);
        scheduleList.setAdapter(adapter);

        scheduleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                schedule.remove(position);
                adapter = new ArrayAdapter<String> (MainActivity.this, android.R.layout.simple_list_item_1, schedule);
                scheduleList.setAdapter(adapter);
                Log.d("DEBUG", schedule.toString());
                return true;
            }
        });


        final EditText inputTask = (EditText) findViewById(R.id.inputTask);

        Button submitTask = (Button) findViewById(R.id.submitTask);
        submitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTask.getText().toString()!=null && inputTask.getText().toString()!="") {
                    schedule.add(inputTask.getText().toString());
                    adapter = new ArrayAdapter<String> (MainActivity.this, android.R.layout.simple_list_item_1, schedule);
                    scheduleList.setAdapter(adapter);
                    Log.d("DEBUG", schedule.toString());
                    inputTask.setText(null);
                }
            }
        });



    }
}
