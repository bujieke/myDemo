package com.zy.dialogutils;

import android.content.Context;
import android.system.ErrnoException;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

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
public class DialogBuilder {
    private Context context;
    private String titles, message, btnRight, btnLeft;
    private DialogBindView bindView;

    private DialogBtnClickListener listener;
    private int LayouResId = -1;

    public DialogBuilder setLayouResId(int layouResId) {
        LayouResId = layouResId;
        return this;
    }


    public DialogBuilder setBindView(DialogBindView bindView) {
        this.bindView = bindView;
        return this;
    }

    public DialogBuilder setListener(DialogBtnClickListener listener) {
        this.listener = listener;
        return this;
    }


    public DialogBuilder(Context context) {
        this.context = context;
        btnLeft = "取消";
        btnRight = "确定";
    }

    public DialogBuilder setTitles(String titles) {
        this.titles = titles;
        return this;
    }

    public DialogBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public DialogBuilder setBtnRight(String btnRight) {
        this.btnRight = btnRight;
        return this;
    }

    public DialogBuilder setBtnLeft(String btnLeft) {
        this.btnLeft = btnLeft;
        return this;
    }

    /**
     * @return
     */
    public CustomDialog creatCommon() {
        if (listener == null) {
            throw new NullPointerException("Check your DialogBtnClickListener is set");
        }

        CustomDialog customDialog = new CustomDialog(context, R.style.CustomDialog);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_common, null);
        TextView title = (TextView) view.findViewById(R.id.dialog_common_title);
        if (!TextUtils.isEmpty(titles)) {
            title.setVisibility(View.VISIBLE);
            title.setText(titles);
        } else {
            title.setVisibility(View.GONE);
        }
        TextView tv_mess = (TextView) view.findViewById(R.id.dialog_common_message);
        if (!TextUtils.isEmpty(message)) {
            tv_mess.setVisibility(View.VISIBLE);
            tv_mess.setText(message);
        } else {
            tv_mess.setVisibility(View.GONE);
        }
        TextView btn_yes = (TextView) view.findViewById(R.id.dialog_common_btnyes);
        TextView btn_no = (TextView) view.findViewById(R.id.dialog_common_btnno);
        btn_yes.setText(btnLeft);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.doConfirm();
            }
        });
        btn_no.setText(btnRight);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.doCancle();
            }
        });
        customDialog.setContentView(view);
        return customDialog;
    }

    public CustomDialog creatMsg() {
        if (TextUtils.isEmpty(message)) {
            throw new NullPointerException("Check your message is set");
        }
        CustomDialog customDialog = new CustomDialog(context, R.style.CustomDialog);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_nobtn, null);
        TextView title = (TextView) view.findViewById(R.id.dialog_common_title);
        if (!TextUtils.isEmpty(titles)) {
            title.setVisibility(View.VISIBLE);
            title.setText(titles);
        } else {
            title.setVisibility(View.GONE);
        }
        TextView tv_mess = (TextView) view.findViewById(R.id.dialog_common_message);
        tv_mess.setText(message);
        customDialog.setContentView(view);
        return customDialog;
    }

    /**
     * prodialog
     *
     * @return
     */
    public CustomDialog creatPro() {
        CustomDialog customDialog = new CustomDialog(context, R.style.CustomDialog);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_pro, null);
        TextView textView = (TextView) view.findViewById(R.id.dialog_pro_msg);
        textView.setText(message);
        ImageView img = (ImageView) view.findViewById(R.id.dialog_pro_img);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.loading_animation);
        // 使用ImageView显示动画
        img.startAnimation(hyperspaceJumpAnimation);
        customDialog.setContentView(view);
        customDialog.setCanceledOnTouchOutside(false);
        return customDialog;
    }

    /**
     * 绑定View接口和布局得有
     *
     * @return
     */
    public CustomDialog creatCustom() {
        if (bindView == null || LayouResId == -1) {
            throw new NullPointerException("Check your LayouResId And bindView is set");
        }
        CustomDialog customDialog = new CustomDialog(context, R.style.CustomDialog);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(LayouResId, null);
        bindView.bindView(view);
        customDialog.setContentView(view);
        return customDialog;
    }

    public interface DialogBtnClickListener {

        public void doConfirm();

        public void doCancle();
    }

    public interface DialogBindView {
        public void bindView(View view);
    }

}
