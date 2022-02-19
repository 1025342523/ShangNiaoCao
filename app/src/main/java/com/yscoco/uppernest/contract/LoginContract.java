package com.yscoco.uppernest.contract;

import com.yscoco.uppernest.commonlibrary.base.presenter.AbsPresenter;
import com.yscoco.uppernest.commonlibrary.base.view.AbstractView;
import com.yscoco.uppernest.model.LoginBean;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class LoginContract {

    public interface View extends AbstractView {

        void loginOk(LoginBean bean);

        void loginErr(String info);

    }

    public interface Presenter extends AbsPresenter<LoginContract.View> {

        void login(String name, String password, String loginDevice);
        
    }


}
