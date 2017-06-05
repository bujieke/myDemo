package com.zy.mydemo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.zy.mydemo.activitys.Dialogdemo;
import com.zy.mydemo.activitys.LoginActivity;
import com.zy.mydemo.base.BaseActivity;
import com.zy.mydemo.fragments.BeiYongFragment;
import com.zy.mydemo.fragments.HomeFragment;
import com.zy.mydemo.fragments.MineFragment;

public class MainActivity extends BaseActivity {
    private Class fragment[];
    private FragmentTabHost mHomeTablehost;
    private RadioGroup mHomeRg;
    private RadioButton mRbHome;
    private RadioButton mRbMine;
    private RadioButton mRbBeiyong;
    private boolean MenuIsShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().setDisplayHomeAsUpEnabled(false).setTitle(R.string.homePage);
        initView();
        initListener();
    }


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }


    private void initView() {
        fragment = new Class[]{HomeFragment.class, MineFragment.class, BeiYongFragment.class};
        mHomeTablehost = (FragmentTabHost) findViewById(R.id.home_tablehost);
        mHomeRg = (RadioGroup) findViewById(R.id.home_rg);
        mRbHome = (RadioButton) findViewById(R.id.rb_home);
        mRbMine = (RadioButton) findViewById(R.id.rb_mine);
        mRbBeiyong = (RadioButton) findViewById(R.id.rb_beiyong);
        initTableHost();
    }

    private void initTableHost() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mHomeTablehost.setup(getApplicationContext(), fragmentManager, R.id.home_fl);
        for (int i = 0; i < fragment.length; i++) {
            TabHost.TabSpec tabspec = mHomeTablehost.newTabSpec(fragment[i].getSimpleName())
                    .setIndicator(fragment[i].getSimpleName());
            mHomeTablehost.addTab(tabspec, fragment[i], null);
        }
        mHomeTablehost.setCurrentTab(0);

    }

    private void initListener() {
        mHomeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        MenuIsShow = true;
                        mHomeTablehost.setCurrentTab(0);
                        getToolbar().setTitle("主页");
                        break;
                    case R.id.rb_mine:
                        MenuIsShow = false;
                        mHomeTablehost.setCurrentTab(1);
                        getToolbar().setTitle("我的");
                        break;
                    case R.id.rb_beiyong:
                        MenuIsShow = false;
                        mHomeTablehost.setCurrentTab(2);
                        getToolbar().setTitle("备用");
                        break;
                }
                supportInvalidateOptionsMenu(); //重绘制menu
            }
        });
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
        return super.onOptionsItemSelected(item);
    }
}
