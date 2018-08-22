package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.justin.social.R;
import com.justin.social.databinding.ActivityShareFriendBinding;
import com.justin.social.databinding.ActivitySocialCalculaterBinding;
import com.justin.social.model.four.SocialCalculaterModel;
import com.justin.social.utils.DialogUtils;

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
        binding.shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.getDialogUtilInstance().
                        showShareDialog(ShareFriendActivity.this,"优选社保",
                                "www.youxuanzhijia.top",
                                "欢迎加入优选社保的大家庭");
            }
        });
    }
}
