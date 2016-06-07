package com.dimpossitorus.membersmonitor;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int memberNow; // Show how many members now
    private int memberAll; // Show how many members that registers all the time of the app
    private static final String PREF_NAME = "com.dimpossitorus.membersmonitor.SHARED_PREFERENCES";

    private MemberDBHelper database;
    private ArrayList<String> members;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restoring sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        memberNow = sharedPreferences.getInt("now",0);
        memberAll = sharedPreferences.getInt("all",0);

        database = new MemberDBHelper(this);
        /*for (int i=0; i<5; i++) {
            memberNow++;
            memberAll++;
            database.insertMember("Member " + memberAll, "Address"+memberAll);
        }*/

        final ListView memberList = (ListView) findViewById(R.id.memberList);
        members = database.getAllMembersName();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,members);
        memberList.setAdapter(adapter);

        final TextView now = (TextView) findViewById(R.id.now);
        final TextView all = (TextView) findViewById(R.id.all);
        now.setText("Member now : "+memberNow);
        all.setText("Member ever registered : "+memberAll);

        Toast.makeText(this, "Member Now : "+memberNow+" | Member ever registered : "+memberAll,Toast.LENGTH_LONG).show();
        Button addMember = (Button) findViewById(R.id.addMember);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberNow++;
                memberAll++;
                database.insertMember("Member " + memberNow, "Address"+memberNow);
                members=database.getAllMembersName();
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,members);
                memberList.setAdapter(adapter);
                now.setText("Member now : "+memberNow);
                all.setText("Member ever registered : "+memberAll);
            }
        });

        Button deleteMember = (Button) findViewById(R.id.deleteMember);
        deleteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(memberNow>0) {
                    database.deleteMember(memberNow);
                    members = database.getAllMembersName();
                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, members);
                    memberList.setAdapter(adapter);
                    memberNow--;
                    now.setText("Member now : " + memberNow);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("now", memberNow);
        editor.putInt("all", memberAll);
        editor.commit();
    }
}
