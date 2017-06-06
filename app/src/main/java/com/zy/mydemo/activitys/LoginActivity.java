package com.zy.mydemo.activitys;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zy.mydemo.R;
import com.zy.mydemo.base.BaseActivity;
import com.zy.mydemo.present.LoginPresent;
import com.zy.mydemo.view.ILoginView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements ILoginView {
    private EditText mAccountView;
    private EditText mPasswordView;
    private LoginPresent mPresent;
    private RadioButton mRbSave;
    private Button mBtnLogin;
    private String account;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar().setTitle("登录").setDisplayHomeAsUpEnabled(false);
        init();
    }

    private void init() {

        mPresent = new LoginPresent(mContext, this);
        mAccountView = (EditText) findViewById(R.id.account);
        mPasswordView = (EditText) findViewById(R.id.password);
        mRbSave = (RadioButton) findViewById(R.id.rb_save);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        account = mAccountView.getText().toString();
        pwd = mPasswordView.getText().toString();



        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresent.confirmation(account, pwd);
            }
        });

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    mPresent.confirmation(account, pwd);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setFacus(int facus) {

        if (facus == 0) {
            mAccountView.requestFocus();
        }
        if (facus == 1) {
            mPasswordView.requestFocus();
        }
    }


//    /**
//     * 验证登录
//     */
//    private void attemptLogin() {
//        String account = mAccountView.getText().toString();
//        String password = mPasswordView.getText().toString();
//        boolean cancel = false;
//        View focusView = null;
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            focusView = mPasswordView;
//            cancel = true;
//        }
//        if (TextUtils.isEmpty(account)) {
//            focusView = mAccountView;
//            cancel = true;
//        }
//
//        if (cancel) {
//            focusView.requestFocus();
//        } else {
//            showPro();
//            login(account, password);
//        }
//    }
//
//    /*
//     * 登录方法
//     *
//     * @param account
//     * @param password
//     */
//    private void login(String account, String password) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                opeanActivity(MainActivity.class, "主页");
//                finish();
//            }
//        }, 3000);
//
//    }
//
//
//    /**
//     * 密码验证
//     *
//     * @param password
//     * @return
//     */
//    private boolean isPasswordValid(String password) {
//        return password.length() > 4;
//    }

}

