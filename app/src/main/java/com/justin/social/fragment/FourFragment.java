package com.justin.social.fragment;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.databinding.FragmentFiveBinding;
import com.justin.social.databinding.FragmentFourBinding;
import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.model.tab.FourModel;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class FourFragment extends Fragment {
    private FragmentFourBinding mBinding;
    public FourModel model;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_four, container, false);
        model = new FourModel(getActivity());
        mBinding.setModel(model);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {

    }
}
