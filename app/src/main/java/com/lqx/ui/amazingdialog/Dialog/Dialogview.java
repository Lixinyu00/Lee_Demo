package com.lqx.ui.amazingdialog.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lqx.ui.amazingdialog.Db.DatabaseHelper;
import com.lqx.ui.amazingdialog.Ui.MainActivity;
import com.lqx.ui.amazingdialog.R;

/**
 * Created by NEDUsoftware on 2016/12/30.
 */
public class Dialogview extends Dialog implements View.OnClickListener {

    private EditText et_1;
    private EditText et_2;
    private EditText et_3;
    private EditText et_4;
    private EditText et_5;
    private EditText et_6;
    private Button btn_1;
    private int count;
    private int resLayout;
    private Activity activity;
    private String sql;
    private int id=1;

    public Dialogview(Activity activity){
        super(activity);
    }
    public Dialogview(Activity activity, int resLayout){
        super(activity,resLayout);
        this.activity=activity;
        this.resLayout = resLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resLayout);
        initview();
        initlistener();
    }

    private void select(){
        switch (resLayout){
            case R.layout.dialog_a:
                sql = "insert into dl(ID,Did,title,content) values('" + Count() + "','1','" + et_1.getText().toString() + "','" + et_2.getText().toString() + "')";
                break;
            case R.layout.dialog_b:
                sql = "insert into dl(ID,Did,title,content) values('" + Count() + "','2','" + et_1.getText().toString() + "','" + et_2.getText().toString() + "')";
                break;
            case R.layout.dialog_c:
                sql = "insert into dl(ID,Did,title,content) values('" + Count() + "','3','" + et_1.getText().toString() + "','" + et_2.getText().toString() + "')";
                break;
            case R.layout.dialog_d:
                sql = "insert into dl(ID,Did,title,A,B,C,D) values('" + Count() + "','4','" + et_1.getText().toString() + "','" + et_2.getText().toString() + "','" + et_3.getText().toString() + "','" + et_4.getText().toString() + "','" + et_5.getText().toString() + "')";
                break;
            case R.layout.dialog_e:
                sql = "insert into dl(ID,Did,title) values('" + Count() + "','5','" + et_1.getText().toString() + "')";
                break;
            case R.layout.dialog_f:
                sql = "insert into dl(ID,Did,title,content,confirm,title_1,content_1,confirm_1) values('" + Count() + "','6','" + et_1.getText().toString() + "','" + et_2.getText().toString() + "','" + et_3.getText().toString() + "','" + et_4.getText().toString() + "','" + et_5.getText().toString() + "','" + et_6.getText().toString() + "')";
                break;
        }
    }
    private void initview() {
        btn_1 = (Button) findViewById(R.id.btn_1_1);
        et_1 = (EditText) findViewById(R.id.et_1_1);
        et_2 = (EditText) findViewById(R.id.et_1_2);
        et_3 = (EditText) findViewById(R.id.et_1_3);
        et_4 = (EditText) findViewById(R.id.et_1_4);
        et_5 = (EditText) findViewById(R.id.et_1_5);
        et_6 = (EditText) findViewById(R.id.et_1_6);
    }

    private void initlistener() {
        btn_1.setOnClickListener(this);
    }

    private int Count() {
        DatabaseHelper database = new DatabaseHelper(getContext());
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select Count(*) from dl;";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        count = cursor.getInt(0);
        db.close();
        return count + 1;
    }

    @Override
    public void onClick(View v) {
        DatabaseHelper database = new DatabaseHelper(getContext());
        SQLiteDatabase db = database.getReadableDatabase();
        select();
        db.execSQL(sql);
        db.close();
        Toast.makeText(activity, "保存成功", Toast.LENGTH_LONG).show();
        dismiss();
        activity.finish();
        Intent intent = new Intent();
        intent.setClass(getContext(),MainActivity.class);
        activity.startActivity(intent);
    }
}
