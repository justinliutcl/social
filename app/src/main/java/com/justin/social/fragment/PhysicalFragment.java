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
import com.justin.social.databinding.FragmentOrderFlowBinding;

/**
 * Created by Justinliu on 2018/3/27.
 */

    public class PhysicalFragment extends Fragment {
    public static final String PHYSICAL_LOCAL       = "1";
    public static final String PHYSICAL_INTERNET    = "2";
    private FragmentOrderFlowBinding mBinding;
    public static final String TYPE = "type";
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_order_flow, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        switch (getArguments().getString(TYPE, NewsListConfig.SOCIAL_HOT)){
            case NewsListConfig.SOCIAL_HOT:
                mBinding.image.setBackgroundResource(R.drawable.back_order_social);
                break;
            case NewsListConfig.SOCIAL_NEWS:
                mBinding.image.setBackgroundResource(R.drawable.back_order_accu);
                break;
        }

    }

}
