package com.example.admin.w2d5_test01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.w2d5_test01.FeedReaderContract.FeedEntry;

/**
 * Created by Admin on 7/26/2017.
 * FROM THE SCRATCH!
 */

public class DBHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="my_dbNotes.db";
    public static final String SQL_CREATE_ENTRIES= "CREATE TABLE "+
            FeedEntry.TABLE_NAME +" ("+
            FeedEntry._ID+" INTEGER PRIMARY KEY,"+
            FeedEntry.COLUMN_NAME_TITLE+" TEXT,"+
            FeedEntry.COLUMN_NAME_SUBTITLE+" TEXT)";
    public static final String SQL_DELETE_ENTRIES= "DROP TABLE IF EXISTS "+
            FeedEntry.TABLE_NAME;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
