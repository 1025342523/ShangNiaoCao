package com.yscoco.uppernest.contract;


import com.yscoco.uppernest.commonlibrary.base.presenter.AbsPresenter;
import com.yscoco.uppernest.commonlibrary.base.view.AbstractView;

/**
 * Created by ZhangZeZhi on 2018-11-22.
 */

public class ForgetPasswordContract {

    public interface View extends AbstractView {

        void updateOk();

        void updateErr(String info);

        void sendSmsCodeOk();

        void sendSmsCodeErr(String info);

    }

    public interface Presenter extends AbsPresenter<ForgetPasswordContract.View> {

        void updatePassword(String phone, String code, String password);

        void sendSmsCode(String phone, String type);
    }


}
