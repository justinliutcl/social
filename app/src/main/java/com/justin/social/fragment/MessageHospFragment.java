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
import com.justin.social.databinding.FragmentMessageHospBinding;
import com.justin.social.databinding.FragmentNewslistOneBinding;
import com.justin.social.model.one.MessageHospModel;
import com.justin.social.model.one.NewsListModel;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class MessageHospFragment extends Fragment {
    private FragmentMessageHospBinding mBinding;
    public static final String TYPE = "type";
    private MessageHospModel model;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_message_hosp, container, false);
        initView();
        mBinding.setModel(model);
        return mBinding.getRoot();
    }

    private void initView() {
        model = new MessageHospModel(getActivity());
        model.init(mBinding);
        model.initNewsList();
    }

}
