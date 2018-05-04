package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.justin.social.R;
import com.justin.social.databinding.ActivityCustomSocialaccuBinding;
import com.justin.social.model.one.CustomSocialAccuModel;

/**
 * Created by ASUS on 2018/4/14.
 */

public class CustomSocialAccu extends BackActivity {
    ActivityCustomSocialaccuBinding binding;
    CustomSocialAccuModel model;
    public static final String TYPE         = "type";
    public static final String SOCIAL_TYPE  = "social_type";
    public static final String ACCU_TYPE    = "accu_type";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_socialaccu);
        model = new CustomSocialAccuModel(this);
        binding.setModel(model);
        model.getCity();
        String type = getIntent().getStringExtra(TYPE);
        if (type != null) {
            model.setSocial(type.equals(SOCIAL_TYPE));
            model.title.set("我的公积金");
        }
        model.initBind(binding);
    }

    public static void JumpToCustomSocialAccu(Context context, String type) {
        Intent intent = new Intent(context, CustomSocialAccu.class);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }
}
