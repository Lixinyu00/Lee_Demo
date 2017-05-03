package com.lqx.ui.amazingdialog.Ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lqx.ui.amazingdialog.Bean.UserBean;
import com.lqx.ui.amazingdialog.Db.DatabaseHelper;
import com.lqx.ui.amazingdialog.Dialog.Dialogview;
import com.lqx.ui.amazingdialog.Fragment.UserFragment;
import com.lqx.ui.amazingdialog.Fragment.WeatherFragment;
import com.lqx.ui.amazingdialog.Fragment.MapFragment;
import com.lqx.ui.amazingdialog.Fragment.DialogFragment;
import com.lqx.ui.amazingdialog.R;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

/**
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    private int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private DialogFragment mLocationFragment;
    private MapFragment mFindFragment;
    private WeatherFragment mWeatherFragment;
    private UserFragment mBookFragment;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private DatabaseHelper database;
    private SQLiteDatabase db = null;
    private ImageView imageView;
    private Boolean isuser;
    private BmobFile bmobFile;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydata();
        initview();
        initlistener();
        setDefaultFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences("userdata", MODE_PRIVATE);
        String iconpath = getApplicationContext().getFilesDir() + "/" + pref.getString("userId", "") + ".jpg";
        File icon = new File(iconpath);
        if (isuser && icon.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(iconpath);
            imageView.setImageBitmap(bitmap);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.launcher);
            imageView.setImageBitmap(bitmap);
        }
    }

    private void initview() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        TextView textView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.t_header);

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "Dialog").setActiveColor(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.ic_location_on_white_24dp, "位置").setActiveColor(R.color.cadetblue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_find_replace_white_24dp, "天气").setActiveColor(R.color.green_1))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "用户").setActiveColor(R.color.colorAccent))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
        SharedPreferences pref = getSharedPreferences("userdata", MODE_PRIVATE);
        isuser = pref.getBoolean("isuser", false);
        if (isuser) {
            textView.setText("欢迎回来！"+pref.getString("userName",""));
            Log.e("11111", "userIcon: "+pref.getBoolean("userIcon", false) );
            if (pref.getBoolean("userIcon", false)) {
                downloadBitmap(pref.getString("userId", ""));
            }
        }
        String iconpath = getApplicationContext().getFilesDir() + "/" + pref.getString("userId", "") + ".jpg";
        File icon = new File(iconpath);
        if (isuser && icon.exists()) {
            Log.e("11111", "icon.exists():true " );
            Bitmap bitmap = BitmapFactory.decodeFile(iconpath);
            imageView.setImageBitmap(bitmap);
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.launcher);
            imageView.setImageBitmap(bitmap);
        }
    }

    private void initlistener() {
        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void mydata() {
        database = new DatabaseHelper(this);
        db = database.getReadableDatabase();
        if (!(tabbleIsExist())) {
            database.onCreate(db);
        }
    }

    public boolean tabbleIsExist() {
        boolean result = false;
        try {
            Cursor cursor = null;
            String sql = "select count(*) as c from sqlite_master where type ='table' and name ='dl' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }
            cursor.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Dialogview dialogview;
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_camera:
                dialogview = new Dialogview(this, R.layout.dialog_a);
                dialogview.show();
                break;
            case R.id.nav_gallery:
                dialogview = new Dialogview(this, R.layout.dialog_b);
                dialogview.show();
                break;
            case R.id.nav_slideshow:
                dialogview = new Dialogview(this, R.layout.dialog_c);
                dialogview.show();
                break;
            case R.id.nav_manage:
                dialogview = new Dialogview(this, R.layout.dialog_d);
                dialogview.show();
                break;
            case R.id.nav_share:
                dialogview = new Dialogview(this, R.layout.dialog_e);
                dialogview.show();
                break;
            case R.id.nav_send:
                dialogview = new Dialogview(this, R.layout.dialog_f);
                dialogview.show();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * tab方法
     */

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mLocationFragment = new DialogFragment();
        transaction.replace(R.id.tb, mLocationFragment);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getFragmentManager();
        //开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        switch (position) {
            case 0:
                mLocationFragment = new DialogFragment();
                transaction.replace(R.id.tb, mLocationFragment);
                break;
            case 1:
                mFindFragment = new MapFragment();
                transaction.replace(R.id.tb, mFindFragment);
                break;
            case 2:
                mWeatherFragment = new WeatherFragment();
                transaction.replace(R.id.tb, mWeatherFragment);
                break;
            case 3:
                mBookFragment = new UserFragment();
                transaction.replace(R.id.tb, mBookFragment);
                break;
            default:
                break;
        }
        // 事务提交
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 下载头像Icon by lxy
     */
    public void downloadBitmap(String userId) {
        Log.e("11111", "download:true " );
        final String path = getApplicationContext().getFilesDir() + "/" + userId + ".jpg";
        BmobQuery<UserBean> bmobQuery = new BmobQuery<UserBean>();
        bmobQuery.addWhereEqualTo("id", userId);
        bmobQuery.findObjects(new FindListener<UserBean>() {
            @Override
            public void done(List<UserBean> list, BmobException e) {
                if (e == null) {
                    Log.e("11111", "done:true " );
                    for (UserBean user : list) {
                        bmobFile = user.getIcon();
                    }
                    Log.e("11111", "bmobFile "+bmobFile );
                    File savePath = new File(path);
                    bmobFile.download(savePath, new DownloadFileListener() {
                        @Override
                        public void done(String s, BmobException e) {
                            bitmap = BitmapFactory.decodeFile(path);
                            Log.e("11111", "down :true " );
                        }

                        @Override
                        public void onProgress(Integer integer, long l) {

                        }
                    });
                } else {
                    Log.e("11111", "download:false "+e );
                }
            }
        });
    }
}

