package com.zy.mydemo;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.zy.mydemo.activitys.Dialogdemo;
import com.zy.mydemo.activitys.LoginActivity;
import com.zy.mydemo.activitys.VideoActivity;
import com.zy.mydemo.base.BaseActivity;
import com.zy.mydemo.utils.LogUtils;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends BaseActivity {

    private boolean MenuIsShow = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().setDisplayHomeAsUpEnabled(false).setTitle("业务").setNavigationIcon(R.drawable.ic_action_msg).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtils.LogD("去往消息菜单");
            }
        });
        initView();

    }


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }
    private void initView() {


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (MenuIsShow) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        } else {
            return super.onCreateOptionsMenu(menu);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_godialog) {
            opeanActivity(Dialogdemo.class, "DialogDemo");
            return true;
        }
        if (id == R.id.action_share) {
            opeanActivity(LoginActivity.class, "LoginDemo");
            return true;
        }
        if (id == R.id.action_vitamio) {
            opeanActivity(VideoActivity.class, "VideoDemo");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
