package com.zy.dialogutils;

import android.content.Context;

import java.util.HashMap;

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
public class DialogFactory {
    private static CustomDialog mCustomDialog;

    public enum DialogType {PROGRESS, COMMON, MSG, CUSTONVIEW}

    public static CustomDialog creatDialog(DialogBuilder build, DialogType type) {

        switch (type) {
            case PROGRESS:
                mCustomDialog = build.creatPro();
                break;
            case COMMON:
                try {
                    mCustomDialog = build.creatCommon();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                break;
            case MSG:
                try {
                    mCustomDialog = build.creatMsg();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    build.setMessage("请检查是否设置了message");
                    mCustomDialog = build.creatMsg();
                }

                break;
            case CUSTONVIEW:
                try {
                    mCustomDialog = build.creatCustom();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                break;

        }

        return mCustomDialog;
    }


}
