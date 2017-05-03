package com.lqx.ui.amazingdialog.Ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.ant.liao.GifView;
import com.lqx.ui.amazingdialog.R;
import com.lqx.ui.amazingdialog.Users.Login;

/**
 * Created by NEDUsoftware on 2017/5/2.
 */

public class AppStart extends Activity{
    private GifView gifView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app);
        initViews();
    }

    private void initViews() {
        WebView webView=(WebView)findViewById(R.id.app);
        webView.setBackgroundColor(0);
        webView.loadUrl("file:///android_asset/a.gif");
        //webView.loadDataWithBaseURL(null,"<center><img src='file:///android_asset/gif.gif'></center>",
                //"text/html","utf-8",null);
        //gifView=(GifView)findViewById(R.id.gifView);
        //gifView.setGifImage(R.drawable.gif_2);
        //gifView.setGifImageType(GifView.GifImageType.SYNC_DECODER);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(AppStart.this, Login.class);
                startActivity(intent);
                AppStart.this.finish();
            }
        },3000);
    }
}
