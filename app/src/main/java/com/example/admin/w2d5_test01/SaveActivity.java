package com.example.admin.w2d5_test01;

import android.content.ContentValues;
import android.content.SharedPreferences;
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

public class SaveActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = SaveActivity.class.getSimpleName()+"_TAG";
    public TextView tv_result_save;
    public EditText et_title_save,et_title_content;
    public Button btn_save_save;
    private SQLiteDatabase database;
    private DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        tv_result_save=(TextView)findViewById(R.id.tv_result_save);
        et_title_content=(EditText)findViewById(R.id.et_Content_Save);
        et_title_save=(EditText)findViewById(R.id.et_Title_Save);
        btn_save_save=(Button)findViewById(R.id.btn_Save_Save);
        btn_save_save.setOnClickListener(this);
        helper=new DBHelper(SaveActivity.this);
        database=helper.getWritableDatabase();
    }
   private void saveRecord(){
       if(checkEmptySearch()){
           Toast.makeText(SaveActivity.this,"NOT ALLOW EMPTY VALUES",Toast.LENGTH_SHORT).show();
           et_title_save.setText("");
           et_title_content.setText("");
       }
       else {
           String title = et_title_save.getText().toString();
           String subtitle = et_title_content.getText().toString();
           ContentValues values = new ContentValues();
           values.put(FeedEntry.COLUMN_NAME_TITLE, title);
           values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
           long recordId = database.insert(FeedEntry.TABLE_NAME, null, values);
           if (recordId > 0) {
               Toast.makeText(SaveActivity.this, "RECORD SAVED", Toast.LENGTH_SHORT).show();
               Log.d(TAG, "saveRecord: Record Saved.");
               et_title_save.setText("");
               et_title_content.setText("");
           } else {
               Toast.makeText(SaveActivity.this, "RECORD NOT SAVED", Toast.LENGTH_SHORT).show();
               Log.d(TAG, "saveRecord: Record not Saved.");
           }
       }
    }
    public boolean checkEmptySearch(){
        if(et_title_content.getText().toString().isEmpty()||et_title_save.getText().toString().isEmpty()){
            return false;
        }
        else return true;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Save_Save:
                saveRecord();
                break;
        }
    }
}
