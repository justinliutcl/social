package com.justin.social.model.tab;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ShortNewsConfig;
import com.justin.social.RetrofitUtils.DataBean.one.SocialPeopleConfig;
import com.justin.social.RetrofitUtils.DataBean.two.ServiceConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.NewsListActivity;
import com.justin.social.activity.OnlineServiceActivity;
import com.justin.social.adapter.ServiceAdapter;
import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.databinding.FragmentTwoBinding;
import com.justin.social.databinding.ItemSocialPeopleBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.views.AnimationEndListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.*;
import static com.mob.MobSDK.getContext;

/**
 * Created by Justinliu on 2018/3/28.
 */

public class TwoModel extends BaseModel {
    FragmentTwoBinding mBinding;
    public ObservableField<String> socialPeople;
    private List<SocialPeopleConfig> configs;
    private List<String> list;
    private HttpConfigManager httpConfigManager;
    private ServiceAdapter adapter;

    public TwoModel(Context context) {
        super(context);
        socialPeople = new ObservableField<>("title");
        httpConfigManager = new HttpConfigManager();
    }


    public void init(FragmentTwoBinding mBinding) {
        this.mBinding = mBinding;
        list = new ArrayList<>();
        configs = new ArrayList<>();
        mBinding.list.addItemDecoration(new DividerItemDecoration(mContext, VERTICAL));
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.news_list:
                Intent intent = new Intent(mContext, NewsListActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.pay_social:
                mContext.startActivity(new Intent(mContext,OnlineServiceActivity.class));
                break;
            case R.id.pay_accu:
                if(CommonSettingValue.getIns(mContext).getCustomPhone()!=null){
                    AppUtils.diallPhone(CommonSettingValue.getIns(mContext).getCustomPhone(),mContext);
                }else{

                }
                break;
        }
    }

    public void getService() {
        ServiceConfig serviceBean = CommonSettingValue.getIns(getContext()).getService();
        if(serviceBean!=null){
            adapter = new ServiceAdapter(serviceBean.getData(),mContext);
            mBinding.list.setAdapter(adapter);
        }
        new HttpConfigManager().getServiceConfig(new BeanConfigCallBack<ServiceConfig>() {
            @Override
            public void onDataResponse(ServiceConfig bean) {
                if(bean.isSuccess())
                    CommonSettingValue.getIns(mContext).setService(bean);
                adapter = new ServiceAdapter(bean.getData(),mContext);
                mBinding.list.setAdapter(adapter);
            }
        });
    }

    public void onServicePeopleClick(View view){
        mContext.startActivity(new Intent(mContext, OnlineServiceActivity.class));
    }
}
