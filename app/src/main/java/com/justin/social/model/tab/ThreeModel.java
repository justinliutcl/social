package com.justin.social.model.tab;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.three.ThreeConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.NewsListActivity;
import com.justin.social.activity.NorProblemActivity;
import com.justin.social.activity.OnlineServiceActivity;
import com.justin.social.activity.SocialCalculater;
import com.justin.social.adapter.OrderListContentAdapter;
import com.justin.social.adapter.ThreeOrderListContentAdapter;
import com.justin.social.databinding.FragmentThreeBinding;
import com.justin.social.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justinliu on 2018/3/28.
 */

public class ThreeModel extends BaseModel {
    FragmentThreeBinding mBinding;
    public OrderConfig allOrderConfig;
    ThreeOrderListContentAdapter adapter;
    public ThreeModel(Context context) {
        super(context);
    }


    public void init(FragmentThreeBinding mBinding) {
        this.mBinding = mBinding;
        initAllOrder();
    }

    public void initAllOrder() {
        new HttpConfigManager().getThreeConfig(CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<ThreeConfig>() {
            @Override
            public void onDataResponse(ThreeConfig bean) {
                List<ThreeConfig> mDataList = new ArrayList<>();
                if (bean != null && bean.getData() != null) {
                    if (bean.getData().getSocialSecurity() != null)
                        mDataList.add(bean.getData().getSocialSecurity());
                    if (bean.getData().getAccumulation() != null)
                        mDataList.add(bean.getData().getAccumulation());
                }
                if (!mDataList.isEmpty()){
                    adapter = new ThreeOrderListContentAdapter(mDataList, mContext);
                    mBinding.list.setAdapter(adapter);
                }

            }
        });
    }

    public void onClick(View view){
        mContext.startActivity(new Intent(mContext,NewsListActivity.class));
    }

    public void onServicePeopleClick(View view){
        mContext.startActivity(new Intent(mContext, OnlineServiceActivity.class));
    }
}
