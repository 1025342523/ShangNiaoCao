package com.yscoco.uppernest.presenter;

import com.yscoco.uppernest.api.ApiService;
import com.yscoco.uppernest.api.BaseResp;
import com.yscoco.uppernest.api.HttpObserver;
import com.yscoco.uppernest.commonlibrary.base.presenter.BasePresenter;
import com.yscoco.uppernest.commonlibrary.helper.RetrofitCreateHelper;
import com.yscoco.uppernest.contract.ForgetPasswordContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZhangZeZhi on 2018-11-22.
 */

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordContract.View> implements ForgetPasswordContract.Presenter {

    private ForgetPasswordContract.View view;

    public ForgetPasswordPresenter(ForgetPasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void updatePassword(String phone, String code, String password) {
        RetrofitCreateHelper.createApi(ApiService.class)
                .updatePassword(code, phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<String>>() {
                    @Override
                    public void onNext(BaseResp<String> stringBaseResp) {
                        if ("0".equals(stringBaseResp.getCode())) {
                            view.updateOk();
                        } else {
                            view.updateErr(stringBaseResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.updateErr(e.getMessage());
                    }
                });
    }
                    
    @Override
    public void sendSmsCode(String phone, String type) {
        RetrofitCreateHelper.createApi(ApiService.class)
                .sendSmsCode(phone, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<BaseResp<String>>() {
                    @Override
                    public void onNext(BaseResp<String> stringBaseResp) {
                        if ("0".equals(stringBaseResp.getCode())) {
                            view.sendSmsCodeOk();
                        } else {
                            view.sendSmsCodeErr(stringBaseResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.sendSmsCodeErr(e.getMessage());
                    }
                });
    }

}
