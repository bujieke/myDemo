package com.zy.mydemo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zy.mydemo.R;
import com.zy.mydemo.adapter.QueryAdapter;
import com.zy.mydemo.base.BaseActivity;
import com.zy.mydemo.base.BaseAdapter;
import com.zy.mydemo.ui.RecyclerViewTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  zy on 2017/6/5.
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖保佑             永无BUG
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 */
public class QueryActivity extends BaseActivity {
    private EditText mEdQuery;
    private Button mButton;
    private RecyclerView mRvQueryList;
    private QueryAdapter adapter;
    private List<String> mlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_query;
    }

    private void initView() {
        mEdQuery = (EditText) findViewById(R.id.ed_query);
        mButton = (Button) findViewById(R.id.btn_query);
        mRvQueryList = (RecyclerView) findViewById(R.id.rv_query_list);
        RecyclerViewTool recyclerViewTool = new RecyclerViewTool(mRvQueryList, mContext);
        recyclerViewTool.initRecyle(RecyclerViewTool.RVTYPE_GENERAL);
        mlist = new ArrayList<>();
        adapter = new QueryAdapter(mlist, R.layout.item_query);
        mRvQueryList.setAdapter(adapter);
        initListener();
    }

    private void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 10; i++) {
                    mlist.add("查询的第" + i + "项");

                }
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
//               adapter.RefreshItemData(mlist);
             adapter.notifyDataSetChanged();
            }
        });


        adapter.setItemClickLitener(new BaseAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                List list1 = adapter.getList();
                String o = (String) list1.get(position);
                Intent intent = new Intent();
                intent.putExtra("result", o);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


}
