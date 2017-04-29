package com.lqx.ui.amazingdialog.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import com.lqx.ui.amazingdialog.R;

/**
 * User: Ivor
 * Date: 16/12/15
 * Desc:
 */
public class BottomDialog extends Dialog {

    private Activity activity;
    private int resLayout;
    private TextView textView;
    private String text;

    public BottomDialog(Activity activity) {
        super(activity);
    }

    public BottomDialog(Activity activity, int resLayout,String text) {
        super(activity, R.style.myDialog);
        this.activity = activity;
        this.resLayout = resLayout;
        this.text=text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resLayout);
        textView = (TextView) findViewById(R.id.my_text);
        textView.setText(text);
    }
}
