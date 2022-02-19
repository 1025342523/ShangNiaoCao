package com.yscoco.uppernest.presenter;

import com.yscoco.uppernest.api.ApiService;
import com.yscoco.uppernest.api.BaseResp;
import com.yscoco.uppernest.api.HttpObserver;
import com.yscoco.uppernest.commonlibrary.base.presenter.BasePresenter;
import com.yscoco.uppernest.commonlibrary.helper.RetrofitCreateHelper;
import com.yscoco.uppernest.contract.LoginContract;
import com.yscoco.uppernest.model.LoginBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void login(String name, String password, String loginDevice) {
        RetrofitCreateHelper.createApi(ApiService.class)
                .login(name, password, loginDevice)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<LoginBean>>() {
                    @Override
                    public void onNext(BaseResp<LoginBean> loginBeanBaseResp) {
                        if ("0".equals(loginBeanBaseResp.getCode())) {
                            view.loginOk(loginBeanBaseResp.getData());
                        } else {
                            view.loginErr(loginBeanBaseResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.loginErr(e.getMessage());
                    }
                });
    }

}
