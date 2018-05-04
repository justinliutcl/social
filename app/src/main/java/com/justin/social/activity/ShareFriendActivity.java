package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.justin.social.R;
import com.justin.social.databinding.ActivityShareFriendBinding;
import com.justin.social.databinding.ActivitySocialCalculaterBinding;
import com.justin.social.model.four.SocialCalculaterModel;

/**
 * Created by ASUS on 2018/4/14.
 */

public class ShareFriendActivity extends BackActivity {
    ActivityShareFriendBinding binding;
    SocialCalculaterModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share_friend);
    }
}
