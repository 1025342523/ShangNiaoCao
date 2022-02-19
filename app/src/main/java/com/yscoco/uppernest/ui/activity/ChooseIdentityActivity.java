package com.yscoco.uppernest.ui.activity;

import android.content.Intent;
import android.view.View;

import com.yscoco.uppernest.R;
import com.yscoco.uppernest.commonlibrary.base.activity.BaseActivity;
import com.yscoco.uppernest.constant.Constant;

import butterknife.OnClick;

/**
 * Created by ZhangZeZhi on 2018\11\24 0024.
 */

public class ChooseIdentityActivity extends BaseActivity {

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_identity;
    }

    @OnClick({R.id.iv_left, R.id.tv_choose_identity
            , R.id.btn_job_hunting, R.id.btn_recruitment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_choose_identity:
            case R.id.iv_left:
                finish();
                break;
            case R.id.btn_job_hunting:
                Intent intent = new Intent();
                intent.putExtra(Constant.USER_TYPE_KEY, Constant.USER_TYPE_JOB_HUNTING_CODE);
                startActivity(MainActivity.class, intent);
                break;
            case R.id.btn_recruitment:
                Intent recIntent = new Intent();
                recIntent.putExtra(Constant.USER_TYPE_KEY, Constant.USER_TYPE_RECRUITMENT_CODE);
                startActivity(MainActivity.class, recIntent);
                break;
        }
    }


}
