package com.zy.mydemo.activitys;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;

import com.zy.mydemo.R;
import com.zy.mydemo.base.BaseActivity;
import com.zy.mydemo.fragments.Evaluate1Fragment;
import com.zy.mydemo.fragments.Evaluate2Fragment;

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
public class EvaluateAddActivity extends BaseActivity {
    private Class[] fragments;
    private FragmentTabHost mTbIndcate;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initView();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_evaluateadd;
    }


    private void initView() {
        fragments = new Class[]{Evaluate1Fragment.class, Evaluate2Fragment.class};
        mTbIndcate = (FragmentTabHost) findViewById(R.id.tb_indcate);

        FragmentManager fragmentManager = getSupportFragmentManager();
        mTbIndcate.setup(getApplicationContext(), fragmentManager, R.id.home_fl);

        for (int i = 0; i < fragments.length; i++) {
            TabHost.TabSpec tabspec = mTbIndcate.newTabSpec(fragments[i].getSimpleName())
                    .setIndicator(fragments[i].getSimpleName());
            mTbIndcate.addTab(tabspec, fragments[i], null);
        }
        mTbIndcate.setCurrentTab(0);


    }
}
