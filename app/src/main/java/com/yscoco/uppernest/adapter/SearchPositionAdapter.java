package com.yscoco.uppernest.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by ZhangZeZhi on 2018-11-19.
 */

public class SearchPositionAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SearchPositionAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
