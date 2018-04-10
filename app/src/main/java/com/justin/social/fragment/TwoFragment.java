package com.justin.social.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.databinding.FragmentTwoBinding;
import com.justin.social.model.tab.TwoModel;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class TwoFragment extends Fragment {
    private FragmentTwoBinding mBinding;
    TwoModel model;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_two, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        model = new TwoModel(getActivity());
        model.init(mBinding);

        model.getService();
    }
}
