package com.justin.social.model.five;

import android.content.Context;

import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.adapter.NewsListContentAdapter;
import com.justin.social.adapter.OrderListContentAdapter;
import com.justin.social.databinding.FragmentNewslistOneBinding;
import com.justin.social.databinding.FragmentOrderlistOneBinding;
import com.justin.social.model.base.BaseModel;

import java.util.List;

/**
 * Created by ASUS on 2018/4/19.
 */

public class OrderListModel extends BaseModel {
    HttpConfigManager httpConfigManager;
    OrderListContentAdapter adapter;
    FragmentOrderlistOneBinding mBinding;
    public OrderListModel(Context context) {
        super(context);
        httpConfigManager = new HttpConfigManager();
    }

    public void initNewsList(String type, OrderConfig orderConfig) {
        if (orderConfig != null && orderConfig.getData() != null && !orderConfig.getData().isEmpty()) {
            adapter = new OrderListContentAdapter(type, orderConfig.getData(), mContext);
            mBinding.list.setAdapter(adapter);
        }
    }

    public void init(FragmentOrderlistOneBinding mBinding) {
        this.mBinding = mBinding;
    }

}
