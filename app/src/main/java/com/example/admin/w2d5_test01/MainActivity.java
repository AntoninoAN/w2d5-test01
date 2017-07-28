package com.example.admin.w2d5_test01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public EditText et_searchvalues;
    public Button btn_save,btn_read,btn_delete,btn_update;
    private static final String TAG= MainActivity.class.getSimpleName()+"_TAG";
    private static final String META_DATA= "com.example.admin.w2d5_test01_META_DATA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_searchvalues=(EditText)findViewById(R.id.et_files_home);
        btn_save=(Button)findViewById(R.id.btn_save_home);
        btn_save.setOnClickListener(this);
        btn_read=(Button)findViewById(R.id.btn_read_home);
        btn_read.setOnClickListener(this);
        btn_update=(Button)findViewById(R.id.btn_update_home);
        btn_update.setOnClickListener(this);
        btn_delete=(Button)findViewById(R.id.btn_delete_home);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_home:
                callReadActivity();
                break;
            case R.id.btn_read_home:
                break;
            case R.id.btn_update_home:
                break;
            case R.id.btn_delete_home:
                break;
        }
    }
    public void callReadActivity(){
        Intent intent= new Intent(MainActivity.this,SaveActivity.class);
        startActivity(intent);
    }
    public boolean checkEmptySearch(){
        return et_searchvalues.getText().toString().isEmpty();
    }
}
