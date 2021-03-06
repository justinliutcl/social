package com.justin.social.fragment;


import android.app.Fragment;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.model.tab.OneModel;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class OneFragment extends Fragment {
    private FragmentOneBinding mBinding;
    private OneModel model;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_one, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        model = new OneModel(getActivity());
        model.init(mBinding);
        model.getShortNewsList();
        model.initNewList();
        mBinding.setModel(model);
    }

    @Override
    public void onPause() {
        super.onPause();
        model.pauseShowNewList();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(model!=null)
            model.startShowNewList();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if(model!=null)
                model.startShowNewList();
        }else {
            if(model!=null)
                model.pauseShowNewList();
        }
    }
}
