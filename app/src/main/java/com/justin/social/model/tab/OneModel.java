package com.justin.social.model.tab;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ShortNewsConfig;
import com.justin.social.RetrofitUtils.DataBean.one.SocialPeopleConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.activity.LoginActivity;
import com.justin.social.activity.PoliceDetialActivity;
import com.justin.social.activity.WriteSocialNoteActivity;
import com.justin.social.adapter.NewsListAdapter;
import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.databinding.ItemSocialPeopleBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.views.AnimationEndListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justinliu on 2018/3/28.
 */

public class OneModel extends BaseModel {
    FragmentOneBinding mBinding;
    NewsListAdapter adapter;
    public ObservableField<String> socialPeople;
    private int count = 3;
    private List<SocialPeopleConfig> configs;
    private List<String> list;
    HttpConfigManager httpConfigManager;

    public OneModel(Context context) {
        super(context);
        socialPeople = new ObservableField<>("title");
        httpConfigManager = new HttpConfigManager();
    }


    public void init(FragmentOneBinding mBinding) {
        this.mBinding = mBinding;
        list = new ArrayList<>();
        configs = new ArrayList<>();
        initShortNews();
    }

    private void initShortNews() {
        configs.add(new SocialPeopleConfig(mContext.getString(R.string.short_news_title)));
        configs.add(new SocialPeopleConfig(mContext.getString(R.string.short_news_title)));
        configs.add(new SocialPeopleConfig(mContext.getString(R.string.short_news_title)));
        ViewFlipper vf = mBinding.marqueeView;
        ItemSocialPeopleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_social_people, null, false);
        ItemSocialPeopleBinding binding1 = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_social_people, null, false);
        ItemSocialPeopleBinding binding2 = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_social_people, null, false);
        binding.setModel(configs.get(0));
        binding1.setModel(configs.get(1));
        binding2.setModel(configs.get(2));
        vf.addView(binding.getRoot());
        vf.addView(binding1.getRoot());
        vf.addView(binding2.getRoot());
        vf.getInAnimation().setAnimationListener(new AnimationEndListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                if (list.isEmpty())
                    return;
                configs.get(count % 3).socialTitle.set(list.get(count % list.size()));
                count++;
            }
        });
    }

    public void initNewList(){
        httpConfigManager.getNewListConfig(NewsListConfig.MAIN_NEWS, new BeanConfigCallBack<NewsListConfig>() {
            @Override
            public void onDataResponse(NewsListConfig bean) {
                if(bean!=null){
                    List<NewListBean> list = bean.getData().getData();
                    for(NewListBean listBean:list){
                        listBean.context = mContext;
                    }
                    adapter = new NewsListAdapter(list,mContext);
                    mBinding.list.setAdapter(adapter);
                }

            }
        });
    }

    public void getShortNewsList() {
        list.clear();
        httpConfigManager.shortNewsConfig(new BeanConfigCallBack<ShortNewsConfig>() {
            @Override
            public void onDataResponse(ShortNewsConfig bean) {
                if (bean.getData() != null) {
                    for (SocialPeopleConfig config : bean.getData()) {
                        list.add(config.content);
                    }
                }
            }
        });
    }

    public void onClick(View view){
        if(!isLogin()){
            LoginActivity.JumpToLogin(mContext);
            return;
        }
        switch (view.getId()){
            case R.id.social_ll:
                WriteSocialNoteActivity.JumpWriteSocial(mContext,0);
                break;
            case R.id.accu_ll:
                WriteSocialNoteActivity.JumpWriteSocial(mContext,1);
                break;
            case R.id.five_ll:
                WriteSocialNoteActivity.JumpWriteSocial(mContext,2);
                break;
            case R.id.more_ll:

                break;
            case R.id.add_ll:

                break;
            case R.id.save_ll:

                break;
            case R.id.note_ll:

                break;
            case R.id.send_ll:

                break;
            case R.id.problem_ll:

                break;
            case R.id.police_hourse:
                PoliceDetialActivity.jumpToPoliceDetial(mContext,PoliceDetialActivity.HOURSE_TYPE);
                break;
            case R.id.police_car:
                PoliceDetialActivity.jumpToPoliceDetial(mContext,PoliceDetialActivity.CAR_TYPE);
                break;
            case R.id.police_school:
                PoliceDetialActivity.jumpToPoliceDetial(mContext,PoliceDetialActivity.SCHOOL_TYPE);
                break;
            case R.id.police_locial:
                PoliceDetialActivity.jumpToPoliceDetial(mContext,PoliceDetialActivity.LOCIAL_TYPE);
                break;
        }
    }

    public void pauseShowNewList() {
        mBinding.marqueeView.stopFlipping();
    }

    public void startShowNewList() {
        if(mBinding!=null&&mBinding.marqueeView!=null&&!mBinding.marqueeView.isFlipping()){
            mBinding.marqueeView.startFlipping();
        }

    }
}
