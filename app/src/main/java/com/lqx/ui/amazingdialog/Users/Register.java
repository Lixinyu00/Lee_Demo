package com.lqx.ui.amazingdialog.Users;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lqx.ui.amazingdialog.Bean.UserBean;
import com.lqx.ui.amazingdialog.R;
import com.lqx.ui.amazingdialog.config.BmobId;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by NEDUsoftware on 2017/4/30.
 */

public class Register extends Activity implements View.OnClickListener {
   // private ImageView imageView;
    private EditText et_id;
    private EditText et_psd;
    private EditText et_name;
    private EditText et_mail;
    private Button btn_register;
    private String iconpath;
    private Intent intent;
    private File img;
    private Boolean aBoolean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initViews();
        initListeners();
        Bmob.initialize(this, BmobId.AppId);
    }

    private void initViews() {
        img = new File(Environment.getExternalStorageDirectory() + "Img.jpg");
       // imageView = (ImageView) findViewById(R.id.register_icon);
        et_id = (EditText) findViewById(R.id.register_id);
        et_psd = (EditText) findViewById(R.id.register_psd);
        et_name = (EditText) findViewById(R.id.register_name);
        et_mail = (EditText) findViewById(R.id.register_mail);
        btn_register = (Button) findViewById(R.id.btn_register);
    }

    private void initListeners() {
        //imageView.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.register_icon:
                if (et_id.getText().toString().equals("") || et_psd.getText().toString().equals("")) {
                    Toast.makeText(this, "请先输入账号密码，在进行此操作", Toast.LENGTH_SHORT).show();
                } else {
                    setIcon();
                }
                break;*/
            case R.id.btn_register:
                saveuser();
                break;
        }
    }

    private void setIcon() {
        iconpath = getApplicationContext().getFilesDir() + "/"+et_id.getText().toString() + ".jpg";
        new AlertDialog.Builder(this).setTitle("选择来源")
                .setItems(new String[]{"拍照", "相册"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(img));
                                startActivityForResult(intent, 1);
                                break;
                            case 1:
                                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, 2);
                                break;
                        }
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                photoZoom(Uri.fromFile(img));
                break;
            case 2:
                photoZoom(data.getData());
                break;
            case 3:
                save(data);
                break;
        }
    }

    private void photoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void save(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            try {
                FileOutputStream fos = new FileOutputStream(iconpath);
                photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                //imageView.setImageBitmap(photo);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveuser() {
        iconpath = getApplicationContext().getFilesDir() +"/"+ et_id.getText().toString() + ".jpg";
        if (et_id.getText().toString().equals("") || et_psd.getText().toString().equals("")) {
            Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            final String psd = Md5.getmd5(et_psd.getText().toString());
            BmobQuery<UserBean> bmobQuery = new BmobQuery<UserBean>();
            bmobQuery.addWhereEqualTo("id", et_id.getText().toString());
            bmobQuery.findObjects(new FindListener<UserBean>() {
                @Override
                public void done(List<UserBean> list, BmobException e) {
                    if (e == null) {
                        if (list.size() == 0) {
                            UserBean userBean = new UserBean();
                            userBean.setId(et_id.getText().toString());
                            userBean.setPassword(psd);
                            userBean.setMail(et_mail.getText().toString());
                            userBean.setName(et_name.getText().toString());
                           /* File file = new File(iconpath);
                            BmobFile bmobFile = new BmobFile(file);
                            Boolean bool = uploadBitmap();
                            Log.e("22222", "done: "+aBoolean );
                            if (bool) {
                                userBean.setIcon(bmobFile);
                            } else {
                                userBean.setIcon(null);
                            }*/
                            userBean.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(Register.this, "添加成功", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Register.this, Login.class);
                                        startActivity(intent);
                                        Register.this.finish();
                                    } else {
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Register.this, "该用户已存在", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "连接失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private Boolean uploadBitmap() {
        final BmobFile bmobFile = new BmobFile(new File(iconpath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    aBoolean = true;
                    Log.e("3333", "done: "+e);
                } else {
                    aBoolean = false;
                    Log.e("11111111", "done: "+e);
                }
            }
        });
        return aBoolean;
    }
}
