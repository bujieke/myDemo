package com.zy.mydemo.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zy.dialogutils.CustomDialog;
import com.zy.dialogutils.DialogBuilder;
import com.zy.dialogutils.DialogFactory;
import com.zy.mydemo.R;
import com.zy.mydemo.base.BaseActivity;

public class Dialogdemo extends BaseActivity implements DialogBuilder.DialogBtnClickListener {


    private Button mBtnCommon;
    private Button mBtnPro;
    private Button mBtnMes;
    private CustomDialog customDialog;
    private Button mBtnCustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initLinstener();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_dialogdemo;
    }


    private void initView() {
        mBtnCommon = (Button) findViewById(R.id.btn_common);
        mBtnPro = (Button) findViewById(R.id.btn_pro);
        mBtnMes = (Button) findViewById(R.id.btn_mes);
        mBtnCustom = (Button) findViewById(R.id.btn_custom);
    }

    private void initLinstener() {


        mBtnCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBuilder builder = new DialogBuilder(mContext);
                builder.setTitles("问题1").setMessage("生存还是死亡").setBtnLeft("生存").setBtnRight("死亡").setListener(Dialogdemo.this);
                customDialog = DialogFactory.creatDialog(builder, DialogFactory.DialogType.COMMON);
                customDialog.show();
            }
        });
        mBtnPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBuilder builder = new DialogBuilder(mContext);
                builder.setMessage("加载中");
                customDialog = DialogFactory.creatDialog(builder, DialogFactory.DialogType.PROGRESS);
                customDialog.show();
            }
        });
        mBtnMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBuilder builder = new DialogBuilder(mContext);
                builder.setMessage("哈哈哈");
                customDialog = DialogFactory.creatDialog(builder, DialogFactory.DialogType.MSG);
                customDialog.show();
            }
        });

        mBtnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogBuilder builder = new DialogBuilder(mContext);
                builder.setBindView(new DialogBuilder.DialogBindView() {
                    @Override
                    public void bindView(View view) {
                        Button btn = (Button) view.findViewById(R.id.btn_mes);
                        btn.setText("6666666");
                    }
                }).setLayouResId(R.layout.activity_dialogdemo);
                customDialog = DialogFactory.creatDialog(builder, DialogFactory.DialogType.CUSTONVIEW);
                customDialog.show();
            }
        });
    }

    @Override
    public void doConfirm() {
        customDialog.dismiss();
    }

    @Override
    public void doCancle() {
        customDialog.dismiss();
    }
}
