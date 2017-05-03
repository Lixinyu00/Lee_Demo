package com.lqx.ui.amazingdialog.Users;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lqx.ui.amazingdialog.Bean.UserBean;
import com.lqx.ui.amazingdialog.Ui.MainActivity;
import com.lqx.ui.amazingdialog.R;
import com.lqx.ui.amazingdialog.config.BmobId;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by NEDUsoftware on 2017/4/30.
 */

public class Login extends Activity implements View.OnClickListener {
    private EditText et_id;
    private EditText et_psd;
    private Button btn_login;
    private TextView t_rigister;
    private Intent intent;
    private SharedPreferences.Editor editor ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Bmob.initialize(this, BmobId.AppId);
        initViews();
        initListeners();
    }

    private void initViews() {
        et_id=(EditText)findViewById(R.id.et_login_id);
        et_psd=(EditText)findViewById(R.id.et_login_psd);
        btn_login=(Button) findViewById(R.id.btn_login);
        t_rigister=(TextView) findViewById(R.id.t_login_rigister);
    }

    private void initListeners() {
        btn_login.setOnClickListener(this);
        t_rigister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et_psd.getWindowToken(), 0);
                queryuser();
                break;
            case R.id.t_login_rigister:
                intent=new Intent(Login.this,Register.class);
                startActivity(intent);
                break;
        }
    }

    private void queryuser() {
        if ((et_id.getText().toString().equals("")) || (et_psd.getText().toString().equals(""))) {
            Toast.makeText(Login.this, "请输入账号密码", Toast.LENGTH_SHORT).show();
        } else {
            final String psd = Md5.getmd5(et_psd.getText().toString());
            BmobQuery<UserBean> bmobQuery = new BmobQuery<UserBean>();
            bmobQuery.addWhereEqualTo("id", et_id.getText().toString());
            bmobQuery.findObjects(new FindListener<UserBean>() {
                @Override
                public void done(List<UserBean> list, BmobException e) {
                    if (e == null) {
                        if (list.size() != 0) {
                            for (UserBean user : list) {
                                if (user.getPassword().equals(psd)) {
                                    Toast.makeText(Login.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    editor= getSharedPreferences("userdata", MODE_PRIVATE).edit();
                                    editor.putString("userName", user.getName());
                                    editor.putString("userSex", user.getSex());
                                    editor.putString("userMail", user.getMail());
                                    editor.putString("objectId", user.getObjectId());
                                    if (user.getIcon() != null) {
                                        editor.putBoolean("userIcon", true);
                                        Log.e("11111", "userIcon123: true" );
                                    } else {
                                        editor.putBoolean("userIcon", false);
                                        Log.e("11111", "userIcon123: false" );
                                    }
                                    addusers();
                                    startActivity(intent);
                                    Login.this.finish();
                                } else {
                                    Toast.makeText(Login.this, "密码错误", Toast.LENGTH_SHORT).show();
                                    et_psd.setText("");
                                }
                            }
                        } else {
                            Toast.makeText(Login.this, "账号错误", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Login.this, "查询失败"+e, Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }
    }
    private void addusers() {
        editor.putString("userId", et_id.getText().toString());
        editor.putString("userPassword", et_id.getText().toString());
        editor.putBoolean("isuser", true);
        editor.apply();
    }
}
