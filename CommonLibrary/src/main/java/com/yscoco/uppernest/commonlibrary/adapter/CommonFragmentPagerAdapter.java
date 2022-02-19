package com.yscoco.uppernest.commonlibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yscoco.uppernest.commonlibrary.R;
import com.yscoco.uppernest.commonlibrary.utils.ResourcesUtils;

import java.util.List;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class CommonFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private String[] tabs;
    private int[] images;

    public CommonFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public CommonFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] tabs, int[] images) {
        super(fm);
        mFragments = fragments;
        this.tabs = tabs;
        this.images = images;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public View getTabView(int position) {
        View view = ResourcesUtils.inflate(R.layout.layout_tab_item_view);
        ImageView ivTab = (ImageView) view.findViewById(R.id.imageview);
        TextView tvTab = (TextView) view.findViewById(R.id.textview);
        ivTab.setImageResource(images[position]);
        tvTab.setText(tabs[position]);
        return view;
    }

}
