package com.dimpossitorus.membersmonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Dimpos Sitorus on 07/06/2016.
 */
public class MemberDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "members.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "member";
    private static final String MEMBER_ID = "id";
    private static final String MEMBER_NAME = "name";
    private static final String MEMBER_ADDRESS = "address";
    private static final String SQL_CREATE =
            "CREATE TABLE "+TABLE_NAME+
                    " ("+MEMBER_ID+" integer primary key, "+MEMBER_NAME+" text,"+MEMBER_ADDRESS+")";
    private static final String SQL_DROP = "DROP TABLE IF EXIST "+ TABLE_NAME;

    public MemberDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
        Log.d("DEBUG", "SQL TABLE CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP);
        onCreate(db);
    }

    public long insertMember (String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MEMBER_NAME, name);
        contentValues.put(MEMBER_ADDRESS, address);
        return db.insert(TABLE_NAME,null, contentValues);
    }

    public int deleteMember (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int delete = db.delete(TABLE_NAME,"id = ?", new String[]{Integer.toString(id)});
        Log.d("DEBUG", "deleteMember : "+delete+" id="+id);
        return delete;
    }

    public ArrayList<String> getAllMembersName() {
        ArrayList<String> member = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            member.add(cursor.getString(cursor.getColumnIndex(MEMBER_NAME)));
            cursor.moveToNext();
        }

        return member;
    }
}
