package com.lqx.ui.amazingdialog.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NEDUsoftware on 2016/12/25.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mydata.db"; //数据库名称
    private static final int version = 1; //数据库版本

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table dl(ID int primary key , Did int not null ," +
                "title varchar(20) , content varchar(100) ," +
                " A varchar(20) , B varchar(20) , C varchar(20) , D varchar(20)," +
                "confirm varchar(20), title_1 varchar(20), content_1 varchar(100), confirm_1 varchar(20));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
    public void delete(SQLiteDatabase db){
        String sql="drop table dl;";
        db.execSQL(sql);
    }

}