package com.lqx.ui.amazingdialog.Dialog;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.lqx.ui.amazingdialog.Db.DatabaseHelper;
import com.lqx.ui.amazingdialog.Dialog.BottomDialog;
import com.lqx.ui.amazingdialog.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DialogDemo{

    private Dialog alertDialog;
    private SweetAlertDialog sweetAlertDialog;
    private int selectedIndex;
    private DatabaseHelper database;
    private SQLiteDatabase db;
    private String t1;
    private String t2;
    private String t3;
    private String t4;
    private String t5;
    private String t6;
    private String number[];


    public void dialog_1(Context view,int id) {


        database=new DatabaseHelper(view);
        db=database.getReadableDatabase();
        String sql="select title,content from dl where ID="+(id+1)+";";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            t1=cursor.getString(cursor.getColumnIndex("title"));
            t2=cursor.getString(cursor.getColumnIndex("content"));
        }
        cursor.close();
        db.close();
        alertDialog = new AlertDialog.Builder(view)
                .setTitle(t1)
                .setMessage(t2)
                .create();
        alertDialog.show();
    }
    public void dialog_2(Context view,int id) {
        database=new DatabaseHelper(view);
        db=database.getReadableDatabase();
        String sql="select title,content from dl where ID="+(id+1)+";";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            t1=cursor.getString(cursor.getColumnIndex("title"));
            t2=cursor.getString(cursor.getColumnIndex("content"));
        }
        cursor.close();
        db.close();
        alertDialog = new AlertDialog.Builder(view)
                        .setTitle(t1)
                        .setMessage(t2)
                        .setIcon(R.mipmap.ic_launcher)
                        .create();
        alertDialog.show();
    }
    public void dialog_3(Context view,int id) {
        final Context v=view;
        database=new DatabaseHelper(view);
        db=database.getReadableDatabase();
        String sql="select title,content from dl where ID="+(id+1)+";";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            t1=cursor.getString(cursor.getColumnIndex("title"));
            t2=cursor.getString(cursor.getColumnIndex("content"));
        }
        cursor.close();
        db.close();
        alertDialog = new AlertDialog.Builder(v)
                .setTitle(t1)
                .setMessage(t2)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO 具体逻辑
                        Toast.makeText(v, "I am OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO 具体逻辑
                        Toast.makeText(v, "I am Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        alertDialog.show();
    }
    public void dialog_4(Context view,int id) {
        final Context v=view;
        number=new String[4];
        database=new DatabaseHelper(view);
        db=database.getReadableDatabase();
        String sql="select title,A,B,C,D from dl where ID="+(id+1)+";";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            t1=cursor.getString(cursor.getColumnIndex("title"));
            number[0]=cursor.getString(cursor.getColumnIndex("A"));
            number[1]=cursor.getString(cursor.getColumnIndex("B"));
            number[2]=cursor.getString(cursor.getColumnIndex("C"));
            number[3]=cursor.getString(cursor.getColumnIndex("D"));
        }
        cursor.close();
        db.close();
        alertDialog = new AlertDialog.Builder(view)
                .setTitle(t1)
                .setSingleChoiceItems(number, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO 具体逻辑
                        selectedIndex = which;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(v, number[selectedIndex], Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        alertDialog.show();
    }
    public void dialog_5(Activity view,int id) {
        database=new DatabaseHelper(view);
        db=database.getReadableDatabase();
        String sql="select title from dl where ID="+(id+1)+";";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            t1=cursor.getString(cursor.getColumnIndex("title"));
        }
        cursor.close();
        db.close();
        // 自定义样式
        BottomDialog myDialog = new BottomDialog(view, R.layout.my_dialog,t1);
        // 禁用触摸取消
        // myDialog.setCanceledOnTouchOutside(false);

        // 居底部显示
        // Window dialogWindow = myDialog.getWindow();
        // dialogWindow.setGravity(Gravity.BOTTOM);
        myDialog.show();
    }
    public void dialog_6(Context view,int id) {
        database=new DatabaseHelper(view);
        db=database.getReadableDatabase();
        String sql="select title,content,confirm,title_1,content_1,confirm_1 from dl where ID="+(id+1)+";";
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            t1=cursor.getString(cursor.getColumnIndex("title"));
            t2=cursor.getString(cursor.getColumnIndex("content"));
            t3=cursor.getString(cursor.getColumnIndex("confirm"));
            t4=cursor.getString(cursor.getColumnIndex("title_1"));
            t5=cursor.getString(cursor.getColumnIndex("content_1"));
            t6=cursor.getString(cursor.getColumnIndex("confirm_1"));
        }
        cursor.close();
        db.close();
        sweetAlertDialog = new SweetAlertDialog(view, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(t1)
                .setContentText(t2)
                .setConfirmText(t3)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog
                                .setTitleText(t4)
                                .setContentText(t5)
                                .setConfirmText(t6)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                });
        sweetAlertDialog.show();
    }




}
