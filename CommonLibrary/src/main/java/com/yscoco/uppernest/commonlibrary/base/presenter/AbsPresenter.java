package com.yscoco.uppernest.commonlibrary.base.presenter;


import com.yscoco.uppernest.commonlibrary.base.view.AbstractView;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public interface AbsPresenter<T extends AbstractView> {

    /**
     * 注入View
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 回收View
     */
    void detachView();
}
