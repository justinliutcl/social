package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityOnlineServiceBinding;
import com.justin.social.databinding.ActivitySocialSearchBinding;
import com.justin.social.model.one.OnlineServiceModel;
import com.justin.social.model.one.SocialSearchModel;

public class SocialSearchActivity extends BackActivity {
    ActivitySocialSearchBinding bind;
    SocialSearchModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_social_search);
        initData();
        initView();
    }

    private void initView() {
        model = new SocialSearchModel(this);
        model.getCity();
        model.setData(bind);
        bind.setModel(model);
    }

    private void initData() {
    }

}
