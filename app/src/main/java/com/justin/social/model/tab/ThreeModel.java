package com.justin.social.model.tab;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.NewsListActivity;
import com.justin.social.activity.NorProblemActivity;
import com.justin.social.activity.SocialCalculater;
import com.justin.social.adapter.OrderListContentAdapter;
import com.justin.social.adapter.ThreeOrderListContentAdapter;
import com.justin.social.databinding.FragmentThreeBinding;
import com.justin.social.model.base.BaseModel;

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
        new HttpConfigManager().getOrderConfig(OrderConfig.ALL_PAY, CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<OrderConfig>() {
            @Override
            public void onDataResponse(OrderConfig bean) {
                if (bean != null && bean.getData() != null && !bean.getData().isEmpty()) {
                    allOrderConfig = bean;
                    if (allOrderConfig != null && allOrderConfig.getData() != null && !allOrderConfig.getData().isEmpty()) {
                        adapter = new ThreeOrderListContentAdapter(allOrderConfig.getData(), mContext);
                        mBinding.list.setAdapter(adapter);
                    }
                }

            }
        });
    }

    public void onClick(View view){
        mContext.startActivity(new Intent(mContext,NewsListActivity.class));
    }

    public void onServicePeopleClick(View view){

    }
}
