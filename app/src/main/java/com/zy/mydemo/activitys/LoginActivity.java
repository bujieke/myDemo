package com.zy.mydemo.activitys;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zy.mydemo.R;
import com.zy.mydemo.base.BaseActivity;
import com.zy.mydemo.present.LoginPresent;
import com.zy.mydemo.utils.KeyBoardUtils;
import com.zy.mydemo.utils.LogUtils;
import com.zy.mydemo.view.ILoginView;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements ILoginView {
    private EditText mAccountView;
    private EditText mPasswordView;
    private LoginPresent mPresent;
    private CheckBox mCbSave;
    private Button mBtnLogin;

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
        mCbSave = (CheckBox) findViewById(R.id.rb_save);
        mBtnLogin = (Button) findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPresent.confirmation(mAccountView.getText().toString(), mPasswordView.getText().toString(), mCbSave.isChecked());
            }
        });
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    mPresent.confirmation(mAccountView.getText().toString(), mPasswordView.getText().toString(), mCbSave.isChecked());
                    return true;
                }
                return false;
            }
        });
        mPresent.getSavaData();
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

    @Override
    public void getSaveData(String account, String pwd) {
        LogUtils.LogD(account);
        LogUtils.LogD(pwd);
        mCbSave.setChecked(true);
        mAccountView.setText(account);
        mPasswordView.setText(pwd);

    }

    @Override
    public void LoginView() {
        KeyBoardUtils.closeKeybord(mPasswordView, mContext);
        showPro();
    }


}

