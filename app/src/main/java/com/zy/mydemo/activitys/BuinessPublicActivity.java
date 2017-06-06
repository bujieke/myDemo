package com.zy.mydemo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.zy.mydemo.R;
import com.zy.mydemo.adapter.CreaditAdapter;
import com.zy.mydemo.adapter.EvaluateAdapter;
import com.zy.mydemo.adapter.LoanAdapter;
import com.zy.mydemo.adapter.OpenCardAdapter;
import com.zy.mydemo.base.BaseActivity;
import com.zy.mydemo.base.BaseAdapter;
import com.zy.mydemo.ui.RecyclerViewTool;
import com.zy.mydemo.ui.ToolbarX;

import java.util.ArrayList;
import java.util.List;

public class BuinessPublicActivity extends BaseActivity {
    private int activitype = -1;
    private ToolbarX toolbar;
    private Class AddClass;
    private String opeanStr;
    private String queryTitle;
    private RecyclerView mRvBuinessPublic;
    private BaseAdapter mAdapter;
    private List<String> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        toolbar = getToolbar();
        initView();
        if (intent != null) {
            int type = intent.getIntExtra("type", -1);
            if (type != -1) {
                activitype = type;
            }
        }
        if (activitype != -1) {
            init(activitype);
        }


    }

    private void init(int activitype) {
        mList = new ArrayList<>();

        switch (activitype) {
            case 0:
                //征信查询
                toolbar.setTitle("征信查询");
                AddClass = CreditAddActivity.class;
                opeanStr = "新增征信";
                queryTitle = "征信查询";
                mAdapter = new CreaditAdapter(mList, R.layout.item_buiness_public);
                break;
            case 1:
                //开卡申请
                toolbar.setTitle("开卡申请");
                AddClass = OpenCardAddActivity.class;
                opeanStr = "新增开卡";
                queryTitle = "开卡查询";
                mAdapter = new OpenCardAdapter(mList, R.layout.item_buiness_public);

                break;
            case 2:
                //车辆评估
                toolbar.setTitle("车辆评估");
                AddClass = EvaluateAddActivity.class;
                opeanStr = "新增评估";
                queryTitle = "评估查询";
                mAdapter = new EvaluateAdapter(mList, R.layout.item_buiness_public);

                break;
            case 3:
                //汽车贷款
                toolbar.setTitle("汽车贷款");
                AddClass = CreditAddActivity.class;
                opeanStr = "新增贷款";
                queryTitle = "贷款查询";
                mAdapter = new LoanAdapter(mList, R.layout.item_buiness_public);
                break;
        }
        mRvBuinessPublic.setAdapter(mAdapter);

    }


    @Override
    public int getLayout() {
        return R.layout.activity_buiness_public;
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
            opeanActivity(AddClass, opeanStr);
            return true;
        }
        if (id == R.id.action_query) {
            //查询
            opeanActivityForResult(QueryActivity.class, queryTitle, activitype);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initView() {
        mRvBuinessPublic = (RecyclerView) findViewById(R.id.rv_buiness_public);
        RecyclerViewTool recyclerViewTool = new RecyclerViewTool(mRvBuinessPublic, mContext);
        recyclerViewTool.initRecyle(RecyclerViewTool.RVTYPE_GENERAL);
    }
}
