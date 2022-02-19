package com.yscoco.uppernest.ui.activity.login;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.yscoco.uppernest.R;
import com.yscoco.uppernest.commonlibrary.Constant;
import com.yscoco.uppernest.commonlibrary.base.activity.BaseActivity;
import com.yscoco.uppernest.commonlibrary.utils.AppUtils;
import com.yscoco.uppernest.commonlibrary.utils.MD5Utils;
import com.yscoco.uppernest.commonlibrary.utils.SpUtils;
import com.yscoco.uppernest.commonlibrary.utils.StringUtils;
import com.yscoco.uppernest.commonlibrary.utils.ToastUtils;
import com.yscoco.uppernest.contract.RegisterContract;
import com.yscoco.uppernest.model.RegisterResultBean;
import com.yscoco.uppernest.presenter.RegisterPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ZhangZeZhi on 2018\11\19 0019.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R.id.checkBox)
    CheckBox mCheckBox;

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

    private String mCode;
    private boolean isSendCode = false;
    private String mPhone;
    private String mPassword;
    private String mPasswordAgain;
    private RegisterPresenter mPresenter;
    private CountDownTimer mTimer;
    private long countTime = 60 * 1000;

    @Override
    public void registerOk(RegisterResultBean bean) {
        SpUtils.setData(Constant.USER_BEAN_KEY, bean);
        ToastUtils.showToast("注册成功");
        startActivity(LoginActivity.class);
    }

    @Override
    public void registerErr(String info) {
        ToastUtils.showToast(info);
    }

    @Override
    public void sendSmsCodeOk(String info) {
        ToastUtils.showToast("验证码发送成功！");
    }

    @Override
    public void sendSmsCodeErr(String info) {
        ToastUtils.showToast(info);
        mTimer.cancel();
        mBtnCode.setText("获取验证码");
        mBtnCode.setClickable(true);
    }

    @Override
    protected void initUI() {
        mCheckBox.setChecked(false);
    }

    @Override
    protected void initData() {
        mPresenter = new RegisterPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.iv_left, R.id.tv_register
            , R.id.btn_register, R.id.btn_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register:
            case R.id.iv_left:
                finish();
                break;
            case R.id.btn_code:
                mPhone = mEtPhone.getText().toString().trim();
                if (StringUtils.isEmpty(mPhone)) {
                    ToastUtils.showToast("请输入手机号码！");
                    return;
                }
                isSendCode = true;
                sendSms(mPhone);
                break;
            case R.id.btn_register:
                if (checked()) {
                    mPresenter.register("", mPhone, MD5Utils.getMD5(mPasswordAgain), mPhone
                            , AppUtils.getIMEI(this), mCode, "", "");
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
        mPresenter.sendSmsCode(phone, "1");
    }

    public boolean checked() {
        mPhone = mEtPhone.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();
        mPasswordAgain = mEtPasswordAgain.getText().toString().trim();
        mCode = mEtCode.getText().toString().trim();

        if (StringUtils.isEmpty(mPhone)) {
            ToastUtils.showToast("手机号不能为空！");
            return false;
        }

        if (mPassword.length() < 6 || mPassword.length() > 16) {
            ToastUtils.showToast("密码在6~16位之间！");
            return false;
        }

        if (StringUtils.isEmpty(mCode)) {
            ToastUtils.showToast("验证码不能为空！");
            return false;
        }

        if (!mPassword.equals(mPasswordAgain)) {
            ToastUtils.showToast("两次密码不一致！");
            return false;
        }

        if (!mCheckBox.isChecked()) {
            ToastUtils.showToast("请同意用户协议！");
            return false;
        }

        return true;
    }


}
