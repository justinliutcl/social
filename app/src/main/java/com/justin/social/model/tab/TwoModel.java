package com.justin.social.model.tab;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ShortNewsConfig;
import com.justin.social.RetrofitUtils.DataBean.one.SocialPeopleConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.databinding.FragmentTwoBinding;
import com.justin.social.databinding.ItemSocialPeopleBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.views.AnimationEndListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.*;

/**
 * Created by Justinliu on 2018/3/28.
 */

public class TwoModel extends BaseModel {
    FragmentTwoBinding mBinding;
    public ObservableField<String> socialPeople;
    private int count = 3;
    private List<SocialPeopleConfig> configs;
    private List<String> list;
    HttpConfigManager httpConfigManager;

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

    public void initList(){
        httpConfigManager.getNewListConfig(NewsListConfig.MAIN_NEWS, new BeanConfigCallBack<NewsListConfig>() {
            @Override
            public void onDataResponse(NewsListConfig bean) {

            }
        });
    }

    public void onClick(View view){

    }

}
