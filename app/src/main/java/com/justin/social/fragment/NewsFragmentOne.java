package com.justin.social.fragment;


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.databinding.FragmentNewslistOneBinding;
import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.model.one.NewsListModel;
import com.justin.social.model.tab.OneModel;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class NewsFragmentOne extends Fragment {
    private FragmentNewslistOneBinding mBinding;
    public static final String TYPE = "type";
    private NewsListModel model;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_newslist_one, container, false);
        initView();
        mBinding.setModel(model);
        return mBinding.getRoot();
    }

    private void initView() {
        model = new NewsListModel(getActivity());
        model.init(mBinding);
        model.initNewsList(getArguments().getString(TYPE, NewsListConfig.SOCIAL_HOT));
    }

}
