package com.justin.social.model.tab;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.SocialPeopleConfig;
import com.justin.social.RetrofitUtils.DataBean.two.ServiceConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.AddCalculater;
import com.justin.social.activity.NewsListActivity;
import com.justin.social.activity.NorProblemActivity;
import com.justin.social.activity.OnlineServiceActivity;
import com.justin.social.activity.SendMessageActivity;
import com.justin.social.activity.SocialCalculater;
import com.justin.social.activity.SocialSearchActivity;
import com.justin.social.adapter.ServiceAdapter;
import com.justin.social.databinding.FragmentTwoBinding;
import com.justin.social.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;
import static com.mob.MobSDK.getContext;

/**
 * Created by Justinliu on 2018/3/28.
 */

public class FourModel extends BaseModel {

    public FourModel(Context context) {
        super(context);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.social_ll:
                Intent intent = new Intent(mContext, SocialCalculater.class);
                mContext.startActivity(intent);
                break;
            case R.id.problem_ll:
                mContext.startActivity(new Intent(mContext, NorProblemActivity.class));
                break;
            case R.id.social_search_ll:
                mContext.startActivity(new Intent(mContext, SocialSearchActivity.class));
                break;
                case R.id.rem_ll:
                mContext.startActivity(new Intent(mContext, AddCalculater.class));
                break;
            case R.id.hosp_ll:
                SendMessageActivity.JumpSendMessage(mContext, SendMessageActivity.TYPE_TWO);
                break;
        }
    }

    public void onServicePeopleClick(View view) {
        mContext.startActivity(new Intent(mContext, OnlineServiceActivity.class));
    }
}
