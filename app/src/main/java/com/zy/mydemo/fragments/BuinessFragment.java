package com.zy.mydemo.fragments;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.zy.mydemo.R;
import com.zy.mydemo.activitys.CreditqueryActivity;
import com.zy.mydemo.adapter.BuinessAdapter;
import com.zy.mydemo.adapter.HomeAdapter;
import com.zy.mydemo.base.BaseAdapter;
import com.zy.mydemo.base.BaseFragment;
import com.zy.mydemo.ui.RecyclerViewTool;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by  zy on 2017/6/1.
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
public class BuinessFragment extends BaseFragment {
    private android.support.v7.widget.RecyclerView mRvBuinessview;

    private List<String> strings;
    private BuinessAdapter<String> mBuinessAdapter;

    @Override
    public View getView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_buiness, null);
        return view;
    }

    @Override
    protected void initData(View view) {
        mRvBuinessview = (RecyclerView) view.findViewById(R.id.rv_buinessview);
        RecyclerViewTool re = new RecyclerViewTool(mRvBuinessview, mContext);
        re.initRecyle(RecyclerViewTool.RVTYPE_GENERAL);
        strings = new ArrayList<String>();
        strings.add("征信查询");
        strings.add("开卡申请");
        strings.add("车辆评估");
        strings.add("汽车贷款");
        mBuinessAdapter = new BuinessAdapter<>(strings, R.layout.item_buiness);
        mRvBuinessview.setAdapter(mBuinessAdapter);
        mBuinessAdapter.setItemClickLitener(new BaseAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        //征信查询
                        opeanActivity(CreditqueryActivity.class, "征信查询");
                        break;
                    case 1:
                        //开卡申请
                        opeanActivity(CreditqueryActivity.class, "开卡申请");
                        break;
                    case 2:
                        //车辆评估
                        opeanActivity(CreditqueryActivity.class, "车辆评估");
                        break;
                    case 3:
                        //汽车贷款
                        opeanActivity(CreditqueryActivity.class, "汽车贷款");
                        break;
                }
            }
        });
    }


}
