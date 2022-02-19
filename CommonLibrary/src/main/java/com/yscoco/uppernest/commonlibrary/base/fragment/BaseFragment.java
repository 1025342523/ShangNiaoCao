package com.yscoco.uppernest.commonlibrary.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yscoco.uppernest.commonlibrary.base.presenter.AbsPresenter;
import com.yscoco.uppernest.commonlibrary.base.view.AbstractView;
import com.yscoco.uppernest.commonlibrary.domain.MessageEvent;
import com.yscoco.uppernest.commonlibrary.global.GlobalApplication;
import com.yscoco.uppernest.commonlibrary.utils.NetUtils;
import com.yscoco.uppernest.commonlibrary.utils.NetworkBroadcastReceiver;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public abstract class BaseFragment<T extends AbsPresenter> extends SupportFragment implements AbstractView, NetworkBroadcastReceiver.NetEvent {

    public View rootView;
    protected Activity activity;
    protected GlobalApplication context;
    public static NetworkBroadcastReceiver.NetEvent eventFragment;
    private int netMobile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        activity = getActivity();
        context = (GlobalApplication) getActivity().getApplication();
        initBind(view);
        getBundle(getArguments());
    }

    private void initBind(View view) {
        ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        NetUtils.init(getContext());
    }

    public void getBundle(Bundle bundle) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initUI();
        initData();
    }

    /**
     * 数据初始化
     */
    protected abstract void initData();

    /**
     * 界面初始化
     */
    protected abstract void initUI();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }

    @Override
    public void onNetChange(int netWorkState) {
        this.netMobile = netMobile;
        isNetConnect();
    }

    /**
     * 判断有无网络
     */
    private boolean isNetConnect() {
        if (netMobile == NetUtils.NETWORK_WIFI) {
            return true;
        } else if (netMobile == NetUtils.NETWORK_MOBILE) {
            return true;
        } else if (netMobile == NetUtils.NETWORK_NONE) {
            return false;
        }
        return false;
    }

    @Override
    public void showNormal() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void reload() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {

    }

    @Override
    public boolean onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            return false;
        }
        return true;
    }

}
