package com.yscoco.uppernest.presenter;

import com.yscoco.uppernest.api.ApiService;
import com.yscoco.uppernest.api.BaseResp;
import com.yscoco.uppernest.api.HttpObserver;
import com.yscoco.uppernest.commonlibrary.base.presenter.BasePresenter;
import com.yscoco.uppernest.commonlibrary.helper.RetrofitCreateHelper;
import com.yscoco.uppernest.contract.RegisterContract;
import com.yscoco.uppernest.model.RegisterResultBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZhangZeZhi on 2018\11\19 0019.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
    }

    @Override
    public void register(String username, String mobileOrEmail, String password, String nickName, String loginDevice
            , String smsCode, String referrer, String referrerStatus) {
        RetrofitCreateHelper.createApi(ApiService.class)
                .register(username, mobileOrEmail, password, nickName, loginDevice, smsCode, referrer, referrerStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<RegisterResultBean>>() {
                    @Override
                    public void onNext(BaseResp<RegisterResultBean> registerResultBeanBaseResp) {

                        if ("0".equals(registerResultBeanBaseResp.getCode())) {

                            view.registerOk(registerResultBeanBaseResp.getData());

                        } else {

                            view.registerErr(registerResultBeanBaseResp.getMsg());

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        view.registerErr(e.getMessage());

                    }
                });
    }

    @Override
    public void sendSmsCode(String mobileOrEmail, String type) {
        RetrofitCreateHelper.createApi(ApiService.class)
                .sendSmsCode(mobileOrEmail, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<String>>() {
                    @Override
                    public void onNext(BaseResp<String> s) {
                        if ("0".equals(s.getCode())) {
                            view.sendSmsCodeOk(s.getMsg());
                        } else {
                            view.sendSmsCodeErr(s.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.sendSmsCodeErr(e.getMessage());
                    }
                });
    }

}




