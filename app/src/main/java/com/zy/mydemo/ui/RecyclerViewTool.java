package com.zy.mydemo.ui;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.zy.mydemo.R;

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
public class RecyclerViewTool {
    public static int RVTYPE_GENERAL = 1; //类似于listview 的用法
    public static int RVTYPE_GRID = 2; //GRIDVIEW 的用法

    private Context mContext;
    private RecyclerView mView;

    public RecyclerViewTool(RecyclerView view, Context context) {

        this.mContext = context;
        this.mView = view;

    }

    /**
     * @param RecycleType
     */
    public void initRecyle(int RecycleType) {
        RecyclerView.LayoutManager layoutManager = null;
        switch (RecycleType) {
            case 1:
                layoutManager = new LinearLayoutManager(mContext);
                mView.addItemDecoration(new DividerItemDecoration(mContext,LinearLayoutManager.VERTICAL));
                mView.setLayoutManager(layoutManager);
                break;
            case 2:
                layoutManager = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL);
                DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(mContext);
                mView.addItemDecoration(dividerGridItemDecoration);
                mView.setLayoutManager(layoutManager);
                break;


        }
//        mView.addItemDecoration(new RecyclerViewDivider(mContext, LinearLayoutManager.VERTICAL));



    }
}
