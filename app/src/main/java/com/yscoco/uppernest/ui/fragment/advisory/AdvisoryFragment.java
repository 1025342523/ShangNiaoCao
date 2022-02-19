package com.yscoco.uppernest.ui.fragment.advisory;

import android.support.v4.app.Fragment;

import com.flyco.tablayout.SlidingTabLayout;
import com.yscoco.uppernest.R;
import com.yscoco.uppernest.commonlibrary.adapter.CommonFragmentPagerAdapter;
import com.yscoco.uppernest.commonlibrary.base.fragment.BaseRootFragment;
import com.yscoco.uppernest.commonlibrary.utils.ResourcesUtils;
import com.yscoco.uppernest.commonlibrary.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ZhangZeZhi on 2018-11-26.
 */

public class AdvisoryFragment extends BaseRootFragment {

    @BindView(R.id.tab_layout)
    SlidingTabLayout mTabLayout;

    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;

//    @BindView(R.id.civ_img)
//    CircleImageView mCivImg;

    private String[] mAdvisoryTab;
    private List<Fragment> mFragments;
    private CommonFragmentPagerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_advisory;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initUI() {
        mAdvisoryTab = ResourcesUtils.getStringArray(R.array.advisory_tab_array);
        mFragments = new ArrayList<>();
        mFragments.add(new LegalAdviceFragment());
        mFragments.add(new CounselingFragment());
        mAdapter = new CommonFragmentPagerAdapter(getChildFragmentManager(), mFragments, null, null);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setViewPager(mViewPager, mAdvisoryTab);
    }

}
