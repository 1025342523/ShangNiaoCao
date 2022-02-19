package com.yscoco.uppernest.ui.fragment.position;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yscoco.uppernest.R;
import com.yscoco.uppernest.commonlibrary.base.fragment.BaseRootFragment;
import com.yscoco.uppernest.commonlibrary.utils.ResourcesUtils;
import com.yscoco.uppernest.commonlibrary.utils.ToastUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ZhangZeZhi on 2018-11-26.
 */

public class PositionFragment extends BaseRootFragment {

    @BindView(R.id.tv_recommend)
    TextView mTvRecommend;

    @BindView(R.id.tv_filter)
    TextView mTvFilter;

    @BindView(R.id.iv_new)
    ImageView mIvNew;

    @BindView(R.id.rl_recommend_new)
    RelativeLayout mRlRecommendNew;

    @BindView(R.id.scrollView)
    ScrollView mScrollView;

    @BindView(R.id.tfl_monthly_salary)
    TagFlowLayout mTflMonthlySalary;

    @BindView(R.id.tfl_experience)
    TagFlowLayout mTflExperience;

    @BindView(R.id.tfl_education)
    TagFlowLayout mTflEducation;

    @BindView(R.id.iv_recommend)
    ImageView mIvRecommend;

    @BindView(R.id.rv)
    RecyclerView mRv;

    @BindView(R.id.et_search)
    EditText mEtSearch;

    private boolean isRecommendShow = false;
    private boolean isFilterShow = false;
    private boolean isRecommendSelected = true;
    private boolean isNewSelected = false;
    private TagAdapter<String> mSalaryAdapter;
    private String[] mSalaryArray;
    private String[] mExprienceArray;
    private String[] mEducationArray;
    private TagAdapter<String> mExprienceAdapter;
    private TagAdapter<String> mEducationAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_position;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initUI() {
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSalaryArray = ResourcesUtils.getStringArray(R.array.monthly_salary_array);
        mExprienceArray = ResourcesUtils.getStringArray(R.array.experience_array);
        mEducationArray = ResourcesUtils.getStringArray(R.array.education_array);
        
        mSalaryAdapter = new TagAdapter<String>(mSalaryArray) {
            @Override
            public View getView(FlowLayout parent, int position, String earNumber) {
                TextView tvTab = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.layout_flowlayout_view, mTflMonthlySalary, false);
                tvTab.setText(earNumber);

                if (position == 0) {
                    tvTab.setBackground(ResourcesUtils.getDrawable(R.drawable.shape_flow_select_bg));
                    tvTab.setTextColor(Color.WHITE);
                } else {
                    tvTab.setBackground(ResourcesUtils.getDrawable(R.drawable.shape_flow_unselect_bg));
                    tvTab.setTextColor(Color.BLACK);
                }
                return tvTab;
            }
        };

        mExprienceAdapter = new TagAdapter<String>(mExprienceArray) {
            @Override
            public View getView(FlowLayout parent, int position, String earNumber) {
                TextView tvTab = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.layout_flowlayout_view, mTflExperience, false);
                tvTab.setText(earNumber);
                if (position == 0) {
                    tvTab.setBackground(ResourcesUtils.getDrawable(R.drawable.shape_flow_select_bg));
                    tvTab.setTextColor(Color.WHITE);
                } else {
                    tvTab.setBackground(ResourcesUtils.getDrawable(R.drawable.shape_flow_unselect_bg));
                    tvTab.setTextColor(Color.BLACK);
                }
                return tvTab;
            }

            @Override
            public boolean setSelected(int position, String earNumber) {
                return position == 0;
            }
        };
        mEducationAdapter = new TagAdapter<String>(mEducationArray) {
            @Override
            public View getView(FlowLayout parent, int position, String earNumber) {
                TextView tvTab = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.layout_flowlayout_view, mTflEducation, false);
                tvTab.setText(earNumber);
                if (position == 0) {
                    tvTab.setBackground(ResourcesUtils.getDrawable(R.drawable.shape_flow_select_bg));
                    tvTab.setTextColor(Color.WHITE);
                } else {
                    tvTab.setBackground(ResourcesUtils.getDrawable(R.drawable.shape_flow_unselect_bg));
                    tvTab.setTextColor(Color.BLACK);
                }
                return tvTab;
            }

            @Override
            public boolean setSelected(int position, String earNumber) {
                return position == 0;
            }
        };
        mSalaryAdapter.setSelectedList(0);
        mExprienceAdapter.setSelectedList(0);
        mEducationAdapter.setSelectedList(0);

        mTflMonthlySalary.setAdapter(mSalaryAdapter);
        mTflExperience.setAdapter(mExprienceAdapter);
        mTflEducation.setAdapter(mEducationAdapter);

        mTflMonthlySalary.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                ToastUtils.showToast(mSalaryAdapter.getItem(position));
                return true;
            }
        });

        mTflMonthlySalary.setMaxSelectCount(1);

        mTflMonthlySalary.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                ToastUtils.showToast("selectPosSet: " + selectPosSet.toString());
            }
        });

        mSalaryAdapter.notifyDataChanged();
        mExprienceAdapter.notifyDataChanged();
        mEducationAdapter.notifyDataChanged();
    }

    @OnClick({R.id.rl_recommend, R.id.ll_recommend, R.id.ll_new
            , R.id.btn_reset, R.id.btn_sure, R.id.rl_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_recommend:
                if (!isRecommendShow) {
                    isRecommendShow = true;
                    isFilterShow = false;
                    mRlRecommendNew.setVisibility(View.VISIBLE);
                    mScrollView.setVisibility(View.GONE);
                } else {
                    isRecommendShow = false;
                    mRlRecommendNew.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_filter:
                if (!isFilterShow) {
                    isFilterShow = true;
                    isRecommendShow = false;
                    mScrollView.setVisibility(View.VISIBLE);
                    mRlRecommendNew.setVisibility(View.GONE);
                } else {
                    isFilterShow = false;
                    mScrollView.setVisibility(View.GONE);
                }
                break;
            case R.id.ll_recommend:
                if (!isRecommendSelected) {
                    isRecommendSelected = true;
                    isNewSelected = false;
                    mIvNew.setVisibility(View.INVISIBLE);
                    mIvRecommend.setVisibility(View.VISIBLE);
                    mTvRecommend.setText("推荐");
                    mRlRecommendNew.setVisibility(View.GONE);
                    isRecommendShow = false;
                } else {
                    mRlRecommendNew.setVisibility(View.GONE);
                    isRecommendShow = false;
                }
                break;
            case R.id.ll_new:
                if (!isNewSelected) {
                    isNewSelected = true;
                    isRecommendSelected = false;
                    mIvNew.setVisibility(View.VISIBLE);
                    mIvRecommend.setVisibility(View.INVISIBLE);
                    mTvRecommend.setText("最新");
                    mRlRecommendNew.setVisibility(View.GONE);
                    isRecommendShow = false;
                } else {
                    mRlRecommendNew.setVisibility(View.GONE);
                    isRecommendShow = false;
                }
                break;
            case R.id.btn_reset:
                break;
            case R.id.btn_sure:
                break;
        }
    }

}
