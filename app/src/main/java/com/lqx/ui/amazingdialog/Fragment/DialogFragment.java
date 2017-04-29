package com.lqx.ui.amazingdialog.Fragment;

/**
 * Created by NEDUsoftware on 2016/10/31.
 */

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import com.lqx.ui.amazingdialog.Db.DatabaseHelper;
import com.lqx.ui.amazingdialog.Dialog.DialogDemo;
import com.lqx.ui.amazingdialog.R;

import java.util.ArrayList;
import java.util.List;


public class DialogFragment extends Fragment implements AdapterView.OnItemClickListener {

    private DatabaseHelper database;
    private SQLiteDatabase db;
    private ListView listView;
    private DialogDemo dl;
    private int did;

    public static DialogFragment newInstance() {
        DialogFragment fragment = new DialogFragment();
        return fragment;
    }

    public DialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        listView = (ListView) view.findViewById(R.id.list_main);
        listView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, getData()));
        listView.setOnItemClickListener(this);
        return view;
    }

    private List<String> getData() {
        database = new DatabaseHelper(getActivity());
        db = database.getReadableDatabase();
        String sql = "select Count(*) from dl;";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        db.close();
        List<String> data = new ArrayList<String>();
        if(count!=0) {
            for (int i = 1; i <= count ; i++) {
                data.add("第" + i + "条");
            }
        }
        return data;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dl = new DialogDemo();
        database = new DatabaseHelper(getActivity());
        db = database.getReadableDatabase();
        String sql = "select Did from dl where ID=" + (position + 1) + ";";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            did = cursor.getInt(cursor.getColumnIndex("Did"));
        }
        cursor.close();
        db.close();
        switch (did) {
            case 1:
                dl.dialog_1(getActivity(), position);
                break;
            case 2:
                dl.dialog_2(getActivity(), position);
                break;
            case 3:
                dl.dialog_3(getActivity(), position);
                break;
            case 4:
                dl.dialog_4(getActivity(), position);
                break;
            case 5:
                dl.dialog_5(getActivity(), position);
                break;
            case 6:
                dl.dialog_6(getActivity(), position);
                break;
        }
    }
}