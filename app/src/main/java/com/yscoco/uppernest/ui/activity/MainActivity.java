package com.yscoco.uppernest.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.yscoco.uppernest.R;
import com.yscoco.uppernest.commonlibrary.adapter.CommonFragmentPagerAdapter;
import com.yscoco.uppernest.commonlibrary.base.activity.BaseRootActivity;
import com.yscoco.uppernest.commonlibrary.utils.ResourcesUtils;
import com.yscoco.uppernest.commonlibrary.widget.CustomViewPager;
import com.yscoco.uppernest.constant.Constant;
import com.yscoco.uppernest.ui.fragment.advisory.AdvisoryFragment;
import com.yscoco.uppernest.ui.fragment.circle.CircleFragment;
import com.yscoco.uppernest.ui.fragment.message.MessageFragment;
import com.yscoco.uppernest.ui.fragment.mine.MineFragment;
import com.yscoco.uppernest.ui.fragment.position.PositionFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseRootActivity {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;

    private int mUserType;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private CommonFragmentPagerAdapter mAdapter;

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        mUserType = getIntent().getIntExtra(Constant.USER_TYPE_KEY, 0);
        int[] images = {R.drawable.selector_tab_position, R.drawable.selector_tab_advisory
                , R.drawable.selector_tab_message, R.drawable.selector_tab_circle, R.drawable.selector_tab_mine};
        String[] tabArray = ResourcesUtils.getStringArray(R.array.tab_array);
        mFragmentList.add(new PositionFragment());
        mFragmentList.add(new AdvisoryFragment());
        mFragmentList.add(new MessageFragment());
        mFragmentList.add(new CircleFragment());
        mFragmentList.add(new MineFragment());
        mAdapter = new CommonFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList, tabArray, images);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (i == 2) {
                mTabLayout.getTabAt(i).select();
            }
            tab.setCustomView(mAdapter.getTabView(i));
        }
        mViewPager.setCurrentItem(2);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

}