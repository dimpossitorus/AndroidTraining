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
    private Vector<Schedule> schedule = new Vector<Schedule>(10,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schedule.add(new Schedule("Test Title", "Test Description"));
        schedule.add(new Schedule("Title Test", "Description Test"));
        schedule.add(new Schedule("Just Task", "Don't forget! Don't forget! Don't forget! Don't forget! Don't forget! Don't forget! "));
        Log.d("DEBUG", schedule.toString());

        recyclerView = (RecyclerView) findViewById(R.id.rvSchedule);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ScheduleAdapter(schedule);
        recyclerView.setAdapter(adapter);

        final EditText inputTask = (EditText) findViewById(R.id.inputTask);
        final EditText inputDescription = (EditText) findViewById(R.id.description);

        Button submitTask = (Button) findViewById(R.id.submitTask);
        submitTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTask.getText()!=null && !"".equals(inputTask.getText().toString())) {
                    schedule.add(new Schedule(inputTask.getText().toString(), inputDescription.getText().toString() ));
                    adapter = new ScheduleAdapter(schedule);
                    recyclerView.setAdapter(adapter);
                    Log.d("DEBUG", schedule.toString());
                    inputTask.setText(null);
                    inputDescription.setText(null);
                }
            }
        });

    }
}
