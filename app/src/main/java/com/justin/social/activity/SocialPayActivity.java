package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.databinding.ActivityOrderTableBinding;
import com.justin.social.databinding.ActivitySocialPayBinding;
import com.justin.social.model.one.OrderTableModel;
import com.justin.social.model.one.SocialPayModel;

public class SocialPayActivity extends BackActivity {
    ActivitySocialPayBinding bind;
    private SocialPayModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_social_pay);
        model = new SocialPayModel(this);
        bind.setModel(model);
    }
}
