package com.example.admin.w2d5_test01;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.w2d5_test01.FeedReaderContract.FeedEntry;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText et_title_update,et_content_update;
    public Button btn_update;
    public TextView tv_result;
    private SQLiteDatabase database;
    private DBHelper helper;
    private final static String TAG=ReadActivity.class.getSimpleName()+"_TAG";
    public String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        helper=new DBHelper(UpdateActivity.this);
        database=helper.getWritableDatabase();
        et_title_update=(EditText)findViewById(R.id.et_Title_Update);
        et_content_update=(EditText)findViewById(R.id.et_Content_Update);
        btn_update=(Button)findViewById(R.id.btn_Save_Update);
        btn_update.setOnClickListener(this);
        tv_result=(TextView)findViewById(R.id.tv_result_update);
        Intent intent =getIntent();
        if(intent!=null) {
            message= intent.getStringExtra(MainActivity.META_DATA);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Save_Update:
                updateRecord();
                break;
        }
    }
    private void updateRecord(){
        if(checkEmptySearch()){
            Toast.makeText(UpdateActivity.this,"NOT ALLOW EMPTY VALUES",Toast.LENGTH_SHORT).show();
            et_title_update.setText("");
            et_content_update.setText("");
        }
        else {
            ContentValues values = new ContentValues();

            values.put(FeedEntry.COLUMN_NAME_TITLE, et_title_update.getText().toString());
            values.put(FeedEntry.COLUMN_NAME_SUBTITLE, et_content_update.getText().toString());

            String selection = FeedEntry.COLUMN_NAME_TITLE + " LIKE ?";
                String[] selectionArgs = {message};
                int count = database.update(FeedEntry.TABLE_NAME, values, selection, selectionArgs);
                if (count > 0) {
                    tv_result.setText("");
                    tv_result.setText("Updated Values.");
                    Log.d(TAG, "updatedRecord: Records Updated");
                    et_title_update.setText("");
                    et_content_update.setText("");
                } else
                    Log.d(TAG, "updatedRecord: No Records Updated");
            }
        }

    public boolean checkEmptySearch(){
        if(et_title_update.getText().toString().isEmpty()||et_content_update.getText().toString().isEmpty()){
            return true;
        }
        else return false;
    }

}
