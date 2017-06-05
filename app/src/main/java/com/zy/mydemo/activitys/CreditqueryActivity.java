package com.zy.mydemo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zy.mydemo.R;
import com.zy.mydemo.base.BaseActivity;

public class CreditqueryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().setDisplayShowTitleEnabled(true);


    }

    @Override
    public int getLayout() {
        return R.layout.activity_creditquery;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_credit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            opeanActivity(CreditAddActivity.class, "新增征信");
            return true;
        }
        if (id == R.id.action_query) {
            //查询
            opeanActivityForResult(QueryActivity.class, "新增征信", 0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            Log.e("TAG",result);
            Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
        }

    }
}
