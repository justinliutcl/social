package com.justin.social.model.tab;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
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
import com.justin.social.databinding.ItemSocialPeopleBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.views.AnimationEndListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justinliu on 2018/3/28.
 */

public class TwoModel extends BaseModel {
    FragmentOneBinding mBinding;
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


    public void init(FragmentOneBinding mBinding) {
        this.mBinding = mBinding;
        list = new ArrayList<>();
        configs = new ArrayList<>();
    }

    public void initList(){
        httpConfigManager.getNewListConfig(NewsListConfig.MAIN_NEWS, new BeanConfigCallBack<NewsListConfig>() {
            @Override
            public void onDataResponse(NewsListConfig bean) {

            }
        });
    }


}
