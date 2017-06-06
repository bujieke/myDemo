package com.zy.mydemo.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zy.mydemo.MainActivity;
import com.zy.mydemo.R;
import com.zy.mydemo.base.BaseActivity;

public class SplashScreen extends BaseActivity {

    private RelativeLayout mActivitySplashScreen;
    private ImageView mIvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().hide();
        initView();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                opeanActivity(LoginActivity.class);
                finish();
            }
        }, 3000);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_splash_screen;
    }

    private void initView() {
        mActivitySplashScreen = (RelativeLayout) findViewById(R.id.activity_splash_screen);
        mIvSplash = (ImageView) findViewById(R.id.iv_splash);


    }
}
