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
import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.databinding.FragmentThreeBinding;
import com.justin.social.model.five.OrderListModel;
import com.justin.social.model.tab.OneModel;
import com.justin.social.model.tab.ThreeModel;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class ThreeFragment extends Fragment {
    private FragmentThreeBinding mBinding;
    private ThreeModel model;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_three, container, false);
        initView();
        mBinding.setModel(model);
        return mBinding.getRoot();
    }

    private void initView() {
            model = new ThreeModel(getActivity());
            model.init(mBinding);
    }
}
