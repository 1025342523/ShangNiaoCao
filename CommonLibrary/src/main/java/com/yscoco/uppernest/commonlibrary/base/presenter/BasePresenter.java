package com.yscoco.uppernest.commonlibrary.base.presenter;


import com.yscoco.uppernest.commonlibrary.base.view.AbstractView;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class BasePresenter<T extends AbstractView> implements AbsPresenter<T> {

    protected T mView;
    protected int currentPage;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public BasePresenter setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        return this;
    }

}
