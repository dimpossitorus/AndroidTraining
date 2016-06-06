package com.dimpossitorus.rvschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Vector<String> schedule = new Vector<String>(10,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedule.add("Test1");
        schedule.add("Test2");
        schedule.add("Test3");
        Log.d("DEBUG", schedule.toString());

        recyclerView = (RecyclerView) findViewById(R.id.rvSchedule);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ScheduleAdapter(schedule);
        recyclerView.setAdapter(adapter);

        final EditText inputTask = (EditText) findViewById(R.id.inputTask);

        Button submitTask = (Button) findViewById(R.id.submitTask);
        submitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTask.getText()!=null && !"".equals(inputTask.getText().toString())) {
                    schedule.add(inputTask.getText().toString());
                    adapter = new ScheduleAdapter(schedule);
                    recyclerView.setAdapter(adapter);
                    Log.d("DEBUG", schedule.toString());
                    inputTask.setText(null);
                }
            }
        });

    }
}
