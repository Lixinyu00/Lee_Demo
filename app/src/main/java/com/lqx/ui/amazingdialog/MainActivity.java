package com.lqx.ui.amazingdialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

/**
 *
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.OnTabSelectedListener {

    private BottomNavigationBar bottomNavigationBar;
    private int lastSelectedPosition = 0;
    private String TAG = MainActivity.class.getSimpleName();
    private LocationFragment mLocationFragment;
    private FindFragment mFindFragment;
    private FavoritesFragment mFavoritesFragment;
    private BookFragment mBookFragment;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private DatabaseHelper database;
    private SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydata();
        initview();
        initlistener();
        setDefaultFragment();
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

        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_location_on_white_24dp, "位置").setActiveColor(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.ic_find_replace_white_24dp, "发现").setActiveColor(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite_white_24dp, "爱好").setActiveColor(R.color.green))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "图书").setActiveColor(R.color.colorAccent))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
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
                dialogview=new Dialogview(this,R.layout.a_dialog);
                dialogview.show();
                break;
            case R.id.nav_gallery:
                dialogview=new Dialogview(this,R.layout.b_dialog);
                dialogview.show();
                break;
            case R.id.nav_slideshow:
                dialogview=new Dialogview(this,R.layout.c_dialog);
                dialogview.show();
                break;
            case R.id.nav_manage:
                dialogview=new Dialogview(this,R.layout.d_dialog);
                dialogview.show();
                break;
            case R.id.nav_share:
                dialogview=new Dialogview(this,R.layout.e_dialog);
                dialogview.show();
                break;
            case R.id.nav_send:
                dialogview=new Dialogview(this,R.layout.f_dialog);
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
        mLocationFragment = new LocationFragment();
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
                mLocationFragment = new LocationFragment();
                transaction.replace(R.id.tb, mLocationFragment);
                break;
            case 1:
                mFindFragment=new FindFragment();
                transaction.replace(R.id.tb, mFindFragment);
                break;
            case 2:
                if (mFavoritesFragment == null) {
                    mFavoritesFragment = FavoritesFragment.newInstance("爱好");
                }
                transaction.replace(R.id.tb, mFavoritesFragment);
                break;
            case 3:
                if (mBookFragment == null) {
                    mBookFragment = BookFragment.newInstance("图书");
                }
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
}

