package com.yscoco.uppernest.ui.activity.login;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yscoco.uppernest.R;
import com.yscoco.uppernest.commonlibrary.base.activity.BaseActivity;
import com.yscoco.uppernest.commonlibrary.utils.MD5Utils;
import com.yscoco.uppernest.commonlibrary.utils.StringUtils;
import com.yscoco.uppernest.commonlibrary.utils.ToastUtils;
import com.yscoco.uppernest.contract.ForgetPasswordContract;
import com.yscoco.uppernest.presenter.ForgetPasswordPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ZhangZeZhi on 2018-11-22.
 */

public class ForgetPasswordActivity extends BaseActivity<ForgetPasswordPresenter> implements ForgetPasswordContract.View {

    @BindView(R.id.et_phone)
    EditText mEtPhone;

    @BindView(R.id.et_code)
    EditText mEtCode;

    @BindView(R.id.et_password)
    EditText mEtPassword;

    @BindView(R.id.et_password_again)
    EditText mEtPasswordAgain;

    @BindView(R.id.btn_code)
    Button mBtnCode;

    private String mPhone;
    private ForgetPasswordPresenter mPresenter;
    private String mCode;
    private String mPassword;
    private String mPasswordAgain;
    private CountDownTimer mTimer;
    private long countTime = 60 * 1000;

    @Override
    public void updateOk() {
        ToastUtils.showToast("修改密码成功");
        startActivity(LoginActivity.class);
    }

    @Override
    public void updateErr(String info) {
        ToastUtils.showToast(info);
    }

    @Override
    public void sendSmsCodeOk() {
        ToastUtils.showToast("验证码发送成功");
    }

    @Override
    public void sendSmsCodeErr(String info) {
        ToastUtils.showToast(info);
        mBtnCode.setClickable(true);
        mTimer.cancel();
        mBtnCode.setText("获取验证码");
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        mPresenter = new ForgetPasswordPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_password;
    }

    @OnClick({R.id.iv_left, R.id.tv_forget
            , R.id.btn_code, R.id.btn_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
            case R.id.tv_forget:
                finish();
                break;
            case R.id.btn_code:
                mPhone = mEtPhone.getText().toString().trim();
                if (StringUtils.isEmpty(mPhone)) {
                    ToastUtils.showToast("请输入电话号码");
                    return;
                }
                sendSms(mPhone);
                break;
            case R.id.btn_sure:
                if (checked()) {
                    mPresenter.updatePassword(mPhone, mCode, MD5Utils.getMD5(mPasswordAgain));
                }
                break;
        }
    }

    private void sendSms(String phone) {
        mBtnCode.setClickable(false);
        mTimer = new CountDownTimer(countTime, 1000) {
            @Override
            public void onTick(long l) {
                countTime -= 1000;
                mBtnCode.setText(countTime / 1000 + " s");
            }

            @Override
            public void onFinish() {
                mBtnCode.setText("获取验证码");
                mBtnCode.setClickable(true);
            }
        };

        mTimer.start();
        mPresenter.sendSmsCode(phone, "3");

    }

    public boolean checked() {
        mPhone = mEtPhone.getText().toString().trim();
        mCode = mEtCode.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();
        mPasswordAgain = mEtPasswordAgain.getText().toString().trim();

        if (StringUtils.isEmpty(mPhone)) {
            ToastUtils.showToast("电话号码不能为空！");
            return false;
        }

        if (StringUtils.isEmpty(mCode)) {
            ToastUtils.showToast("验证码不能为空！");
            return false;
        }

        if (mPassword.length() < 6 || mPassword.length() > 16) {
            ToastUtils.showToast("密码为6~16位！");
            return false;
        }

        if (!mPasswordAgain.equals(mPassword)) {
            ToastUtils.showToast("两次密码不一致！");
            return false;
        }

        return true;
    }


}



