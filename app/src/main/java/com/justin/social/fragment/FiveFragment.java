package com.justin.social.fragment;
import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.databinding.FragmentFiveBinding;
import com.justin.social.utils.PhotoSelectUtil;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class FiveFragment extends Fragment {
    private FragmentFiveBinding mBinding;
    PhotoSelectUtil photoUtil;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_five, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        photoUtil=new PhotoSelectUtil(getActivity(),this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoUtil.forresult(requestCode,resultCode,data);
    }

    public void onImageClick(View view){
        photoUtil.setimg(mBinding.titleIv);
        photoUtil.showDialog();
    }
}
