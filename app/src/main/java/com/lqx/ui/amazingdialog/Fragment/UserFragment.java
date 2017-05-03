package com.lqx.ui.amazingdialog.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lqx.ui.amazingdialog.Bean.UserBean;
import com.lqx.ui.amazingdialog.R;
import com.lqx.ui.amazingdialog.Users.Login;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by NEDUsoftware on 2016/11/3.
 */
public class UserFragment extends Fragment implements View.OnClickListener{
    private RelativeLayout r_icon;
    private ImageView im_icon;
    private TextView t_name;
    private TextView t_sex;
    private TextView t_mail;
    private Button button;
    private String userid;
    private String iconpath;
    private String objecetId;
    private File img;
    private File icon;

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
        initData();
        return view;
    }

    private void initViews(View view) {
        r_icon=(RelativeLayout)view.findViewById(R.id.layout_icon);
        t_name = (TextView) view.findViewById(R.id.t_name);
        t_sex = (TextView) view.findViewById(R.id.t_sex);
        t_mail = (TextView) view.findViewById(R.id.t_mail);
        im_icon = (ImageView) view.findViewById(R.id.im_icon);
        button = (Button) view.findViewById(R.id.btn_exituser);
        img = new File(Environment.getExternalStorageDirectory() + "/Img.jpg");
    }

    private void initListeners() {
        button.setOnClickListener(this);
        r_icon.setOnClickListener(this);
    }

    private void initData() {
        SharedPreferences pref = getActivity().getSharedPreferences("userdata", MODE_PRIVATE);
        userid = pref.getString("userId", "");
        objecetId = pref.getString("objectId", "");
        t_name.setText(pref.getString("userName", ""));
        t_sex.setText(pref.getString("userSex", "男 "));
        t_mail.setText(pref.getString("userMail",""));
        iconpath = getActivity().getApplicationContext().getFilesDir() + "/" + userid + ".jpg";
        icon = new File(iconpath);
        if (icon.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(iconpath);
            im_icon.setImageBitmap(bitmap);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.launcher);
            im_icon.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exituser:
                userexit();
                break;
            case R.id.layout_icon:
                seticon();
                break;
        }
    }
    private void userexit() {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("userdata", MODE_PRIVATE).edit();
        editor.putBoolean("isuser", false);
        editor.apply();
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void seticon() {
        new AlertDialog.Builder(getActivity())
                .setTitle("选择来源")
                .setItems(new String[]{"拍照", "图库"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent_1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent_1.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(img));
                                startActivityForResult(intent_1, 1);
                                break;
                            case 1:
                                Intent intent_2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent_2, 2);
                                break;
                        }
                    }
                }).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                startphotoZoom(Uri.fromFile(img));
                break;
            case 2:
                startphotoZoom(data.getData());
                break;
            case 3:
                if (data != null) {
                    save(data);
                }
        }
    }

    public void startphotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    public void save(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            try {
                FileOutputStream fos = new FileOutputStream(iconpath);
                photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                uploadBitmap();
                im_icon.setImageBitmap(photo);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadBitmap() {
        final BmobFile bmobFile = new BmobFile(new File(iconpath));
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void onProgress(Integer integer) {
            }

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("userdata", MODE_PRIVATE).edit();
                    editor.putBoolean("userIcon", true);
                    editor.apply();
                    Toast.makeText(getActivity(), "上传成功！", Toast.LENGTH_SHORT).show();
                    UserBean user = new UserBean();
                    user.setIcon(bmobFile);
                    user.update(objecetId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "上传失败！" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
