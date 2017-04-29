package com.lqx.ui.amazingdialog.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lqx.ui.amazingdialog.R;

/**
 * Created by NEDUsoftware on 2016/11/3.
 */
public class UserFragment extends Fragment {
    private ImageView im_icon;
    private TextView t_name;
    private TextView t_sex;

    public UserFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initViews(view);
        initListeners();
        return view;
    }

    private void initViews(View view) {

    }

    private void initListeners() {

    }

}
