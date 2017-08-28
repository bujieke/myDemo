package com.zy.mydemo.present;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.zy.mydemo.MainActivity;
import com.zy.mydemo.utils.LogUtils;
import com.zy.mydemo.utils.SpUtils;
import com.zy.mydemo.view.ILoginView;

/**
 * Created by  zy on 2017/6/6.
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
public class LoginPresent {
    private Context mContext;
    private ILoginView mView;
    private boolean savePwd;

    public LoginPresent(Context context, ILoginView view) {
        this.mContext = context;
        this.mView = view;
    }

    /**
     * 验证是否可以登录
     *
     * @param account
     * @param pwd
     */
    public void confirmation(String account, String pwd, boolean SavePwd) {


        if (TextUtils.isEmpty(account)) {
            mView.setFacus(0);
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            mView.setFacus(1);
            return;
        }


        this.savePwd = SavePwd;
        Login(account, pwd);
    }

    private void Login(String account, String pwd) {


        if (account.length() > 4 && pwd.length() > 4) {
            mView.LoginView();
            //存储账号密码
            if (savePwd) {
                SpUtils.put(mContext, "account", account);
                SpUtils.put(mContext, "pwd", pwd);
                SpUtils.put(mContext, "save", savePwd);

            } else {
                SpUtils.put(mContext, "save", savePwd);
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    mView.opeanActivity(intent);
                }
            }, 3000);


        }
    }

    public void getSavaData() {
        savePwd = (boolean) SpUtils.get(mContext, "save", false);
//        LogUtils.LogD(savePwd + "");
//        LogUtils.LogD((String) SpUtils.get(mContext, "account", null));
//        LogUtils.LogD((String) SpUtils.get(mContext, "pwd", null));

        if (savePwd) {
            mView.getSaveData((String) SpUtils.get(mContext, "account", ""), (String) SpUtils.get(mContext, "pwd", ""));
        }
    }
}
