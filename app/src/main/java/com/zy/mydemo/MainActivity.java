package com.zy.mydemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zy.mydemo.activitys.Dialogdemo;
import com.zy.mydemo.activitys.QueryActivity;
import com.zy.mydemo.activitys.VideoActivity;
import com.zy.mydemo.base.BaseActivity;
import com.zy.mydemo.utils.LogUtils;

public class MainActivity extends BaseActivity {

    private boolean MenuIsShow = true;
    private EditText mPhoneNumber;
    private Button mCallphone;


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


        mPhoneNumber = (EditText) findViewById(R.id.phone_number);
        mCallphone = (Button) findViewById(R.id.callphone);
        mCallphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mPhoneNumber.getText().toString())){
                   String phonenumber =  mPhoneNumber.getText().toString();
                    String reg = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
                    if (phonenumber.matches(reg)){
                            //循环拨号
//                            startService()

                        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenumber)));
                    }

                }

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
        if (id == R.id.action_seach) {
            opeanActivity(QueryActivity.class, "查询");
            return true;
        }
        if (id == R.id.action_vitamio) {
            opeanActivity(VideoActivity.class, "VideoDemo");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
