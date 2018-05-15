package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityAboutUsBinding;
import com.justin.social.databinding.ActivityOnlineServiceBinding;
import com.justin.social.model.one.OnlineServiceModel;

public class AboutUsActivity extends BackActivity {
    ActivityAboutUsBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_about_us);
        initData();
        initView();
    }

    private void initView() {
    }

    private void initData() {
    }

}
