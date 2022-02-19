package com.yscoco.uppernest.ui.activity.login;

import android.Manifest;
import android.view.View;
import android.widget.EditText;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yscoco.uppernest.R;
import com.yscoco.uppernest.commonlibrary.Constant;
import com.yscoco.uppernest.commonlibrary.base.activity.BaseActivity;
import com.yscoco.uppernest.commonlibrary.utils.AppUtils;
import com.yscoco.uppernest.commonlibrary.utils.MD5Utils;
import com.yscoco.uppernest.commonlibrary.utils.SpUtils;
import com.yscoco.uppernest.commonlibrary.utils.StringUtils;
import com.yscoco.uppernest.commonlibrary.utils.ToastUtils;
import com.yscoco.uppernest.contract.LoginContract;
import com.yscoco.uppernest.model.LoginBean;
import com.yscoco.uppernest.presenter.LoginPresenter;
import com.yscoco.uppernest.ui.activity.ChooseIdentityActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.et_phone)
    EditText mEtPhone;

    @BindView(R.id.et_password)
    EditText mEtPassword;

    private LoginPresenter mPresenter;
    private String mPhone;
    private String mPassword;

    @Override
    public void loginOk(LoginBean bean) {
        ToastUtils.showToast("登陆成功");
        SpUtils.setData(Constant.USER_BEAN_KEY, bean);
        startActivity(ChooseIdentityActivity.class);
    }

    @Override
    public void loginErr(String info) {
        ToastUtils.showToast(info);
    }

    @Override
    protected void initUI() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        requestPermissions();
    }

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        //请求权限全部结果
        rxPermission.request(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CAMERA
        )
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            ToastUtils.showToast("App未能获取全部需要的相关权限，部分功能可能不能正常使用.");
                        }
                    }
                });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.btn_login, R.id.iv_wx
            , R.id.tv_register, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checked()) {
                    mPresenter.login(mPhone, MD5Utils.getMD5(mPassword), AppUtils.getIMEI(this));
                }
                break;
            case R.id.iv_wx:

                break;
            case R.id.tv_register:
                startActivity(RegisterActivity.class);
                break;
            case R.id.tv_forget_password:
                startActivity(ForgetPasswordActivity.class);
                break;
        }
    }

    private boolean checked() {

        mPhone = mEtPhone.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();

        if (StringUtils.isEmpty(mPhone)) {
            ToastUtils.showToast("请输入账号");
            return false;
        }

        if (mPassword.length() < 6 || mPassword.length() > 16) {
            ToastUtils.showToast("密码为6~16位");
            return false;
        }

        return true;
    }

}


