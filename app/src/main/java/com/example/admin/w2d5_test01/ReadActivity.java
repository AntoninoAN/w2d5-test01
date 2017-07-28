package com.example.admin.w2d5_test01;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.admin.w2d5_test01.FeedReaderContract.FeedEntry;

public class ReadActivity extends AppCompatActivity implements View.OnClickListener{
    private SQLiteDatabase database;
    private DBHelper helper;
    public Button btn_Read;
    public TextView tv_result;
    public StringBuilder stringBuilder;
    private final static String TAG=ReadActivity.class.getSimpleName()+"_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        helper=new DBHelper(ReadActivity.this);
        database=helper.getWritableDatabase();
        btn_Read=(Button)findViewById(R.id.btn_Read_Read);
        btn_Read.setOnClickListener(this);
        tv_result=(TextView)findViewById(R.id.tv_result_read);
    }
    private void readRecord(){
        tv_result.setText("");
        stringBuilder=new StringBuilder();
        String[] projection={FeedEntry._ID,
                FeedEntry.COLUMN_NAME_TITLE,
                FeedEntry.COLUMN_NAME_SUBTITLE};
        String selection= FeedEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArg={
                "Record Title"
        };
        String sortOrder=FeedEntry._ID+" ASC";
        Cursor cursor= database.query(
                FeedEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder,
                null);
        while (cursor.moveToNext()) {
            long entryId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedEntry._ID));
            String entryTitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE));
            String entrySubtitle = cursor.getString(cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE));
            stringBuilder.append("ID: "+entryId+" NOTE TITLE: "+entryTitle+" CONTENT TITLE: "+entrySubtitle);
            stringBuilder.append("\n");
            tv_result.setText("");
            tv_result.setText(stringBuilder);
            Log.d(TAG, "readRecord: id: " + entryId+" title: "+entryTitle+" subtitle: "+entrySubtitle);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Read_Read:
                readRecord();
                break;
       }
    }
}
