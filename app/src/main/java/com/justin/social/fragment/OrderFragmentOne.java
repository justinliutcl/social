package com.justin.social.fragment;


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.databinding.FragmentNewslistOneBinding;
import com.justin.social.databinding.FragmentOrderlistOneBinding;
import com.justin.social.model.five.OrderListModel;
import com.justin.social.model.one.NewsListModel;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class OrderFragmentOne extends Fragment {
    private FragmentOrderlistOneBinding mBinding;
    public static final String TYPE = "type";
    public static final String ORDER_TYPE_ONE = "one_type";
    private OrderListModel model;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_orderlist_one, container, false);
        initView();
        mBinding.setModel(model);
        return mBinding.getRoot();
    }

    private void initView() {
        model = new OrderListModel(getActivity());
        model.init(mBinding);
        model.initNewsList(getArguments().getString(TYPE, OrderConfig.ALL_PAY), (OrderConfig) getArguments().getSerializable(ORDER_TYPE_ONE));
    }

}
