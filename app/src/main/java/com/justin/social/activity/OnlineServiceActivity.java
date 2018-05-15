package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityOnlineServiceBinding;
import com.justin.social.model.one.OnlineServiceModel;

public class OnlineServiceActivity extends BackActivity {
    ActivityOnlineServiceBinding bind;
    OnlineServiceModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_online_service);
        initData();
        initView();
    }

    private void initView() {
        model = new OnlineServiceModel(this);
        model.initData();
        bind.setModel(model);
    }

    private void initData() {
    }

}
