package com.yscoco.uppernest.contract;

import com.yscoco.uppernest.commonlibrary.base.presenter.AbsPresenter;
import com.yscoco.uppernest.commonlibrary.base.view.AbstractView;
import com.yscoco.uppernest.model.RegisterResultBean;

/**
 * Created by ZhangZeZhi on 2018\11\19 0019.
 */

public class RegisterContract {

    public interface View extends AbstractView {

        void registerOk(RegisterResultBean bean);

        void registerErr(String info);

        void sendSmsCodeOk(String info);

        void sendSmsCodeErr(String info);

    }

    public interface Presenter extends AbsPresenter<RegisterContract.View> {

        void register(String username, String mobileOrEmail, String password, String nickName
                , String loginDevice, String smsCode, String referrer, String referrerStatus);

        void sendSmsCode(String mobileOrEmail, String type);

    }


}
