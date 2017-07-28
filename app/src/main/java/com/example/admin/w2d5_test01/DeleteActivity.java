package com.example.admin.w2d5_test01;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.w2d5_test01.FeedReaderContract.FeedEntry;

import org.w3c.dom.Text;


public class DeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private SQLiteDatabase database;
    private DBHelper helper;
    private final static String TAG=DeleteActivity.class.getSimpleName()+"_TAG";
    public String message;
    public Button btn_delete;
    public TextView tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        helper=new DBHelper(DeleteActivity.this);
        database=helper.getWritableDatabase();
        btn_delete=(Button)findViewById(R.id.btn_Read_Delete);
        btn_delete.setOnClickListener(this);
        tv_result=(TextView)findViewById(R.id.tv_result_delete);
        Intent intent =getIntent();
        if(intent!=null) {
            message= intent.getStringExtra(MainActivity.META_DATA);
            //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }
    private void deleteRecord(){
        String selection= FeedEntry.COLUMN_NAME_TITLE+" LIKE ?";

            String[] selectionArgs = {message};
            int deleted = database.delete(
                    FeedEntry.TABLE_NAME,
                    selection,
                    selectionArgs
            );
            if (deleted > 0) {
                Log.d(TAG, message);
                tv_result.setText("Deleted Values.");
            } else {
                Log.d(TAG, "deletedRecord: record not deleted");
                tv_result.setText("Record not Deleted.");
            }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Read_Delete:
                deleteRecord();
                break;

        }
    }
}
