package com.justin.social.fragment;
import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.databinding.FragmentFiveBinding;
import com.justin.social.model.tab.FiveModel;
import com.justin.social.utils.PermessionUtils;
import com.justin.social.utils.PhotoSelectUtil;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class FiveFragment extends Fragment implements View.OnClickListener{
    private FragmentFiveBinding mBinding;
    PhotoSelectUtil photoUtil;
    FiveModel model;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_five, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        photoUtil=new PhotoSelectUtil(getActivity(),this);
        mBinding.titleIv.setOnClickListener(this);
        model = new FiveModel(getContext());
        model.headImage.set(CommonSettingValue.getIns(getContext()).getHeaderImage());
        mBinding.setModel(model);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoUtil.forresult(requestCode,resultCode,data);
    }

    public void onImageClick(View view){
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if(!PermessionUtils.checkPermission(getContext(),this,getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,0))
                return;
        }
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if(PermessionUtils.checkPermission(getContext(),this,getActivity(),
                    Manifest.permission.CAMERA,0))
                return;
        }
        photoUtil.setimg(mBinding.titleIv);
        photoUtil.showDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_iv:
                onImageClick(v);
                break;
        }
    }
}
