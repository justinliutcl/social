package com.justin.social.fragment;


import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.PoliceConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.activity.PoliceDetialActivity;
import com.justin.social.databinding.FragmentOrderFlowBinding;
import com.justin.social.databinding.FragmentPhysicalDetialBinding;
import com.justin.social.utils.ImageUtils;

/**
 * Created by Justinliu on 2018/3/27.
 */

    public class PhysicalFragment extends Fragment {
    public static final String PHYSICAL_LOCAL       = "1";
    public static final String PHYSICAL_INTERNET    = "2";
    private FragmentPhysicalDetialBinding mBinding;
    public static final String TYPE = "type";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_physical_detial, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        switch (getArguments().getString(TYPE, NewsListConfig.SOCIAL_HOT)){
            case NewsListConfig.SOCIAL_HOT:
                 new HttpConfigManager().getPoliceDetialConfig(PoliceDetialActivity.PHYSICAL_LOCAL_TYPE,callBack);
                break;
            case NewsListConfig.SOCIAL_NEWS:
                new HttpConfigManager().getPoliceDetialConfig(PoliceDetialActivity.PHYSICAL_INTER_TYPE,callBack);
                break;
        }
    }
    BeanConfigCallBack callBack =  new BeanConfigCallBack<PoliceConfig>() {
        @Override
        public void onDataResponse(PoliceConfig bean) {
            mBinding.newTitle.setText(bean.getData().getTitleOne());
            mBinding.top.setText(bean.getData().getTitleTwo());
            mBinding.content.setText(bean.getData().getContent());
            ImageUtils.setImage(mBinding.image,bean.getData().getTitleImg());

        }
    };
}
