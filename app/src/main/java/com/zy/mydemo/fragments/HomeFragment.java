package com.zy.mydemo.fragments;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.zy.mydemo.R;
import com.zy.mydemo.adapter.HomeAdapter;
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
public class HomeFragment extends BaseFragment {
    private android.support.v7.widget.RecyclerView mRvHomeview;
    private HomeAdapter<String> stringHomeAdapter;
    private android.support.v4.widget.SwipeRefreshLayout mSwLayout;
    private List<String> strings;

    @Override
    public View getView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_beiyong, null);
        return view;
    }

    @Override
    protected void initData(View view) {
        mRvHomeview = (RecyclerView) view.findViewById(R.id.rv_beiyongview);
        mSwLayout = (SwipeRefreshLayout) view.findViewById(R.id.sw_layout);
        RecyclerViewTool re = new RecyclerViewTool(mRvHomeview, mContext);
        re.initRecyle(RecyclerViewTool.RVTYPE_GENERAL);

        strings = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            strings.add("初始" + i + "项");
        }
        stringHomeAdapter = new HomeAdapter<String>(strings, R.layout.item_home);
        mRvHomeview.setAdapter(stringHomeAdapter);

        mSwLayout.setColorSchemeColors(Color.BLACK, Color.RED, Color.YELLOW);
        mSwLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwLayout.setRefreshing(false);

            }
        }, 3000);
    }


}
