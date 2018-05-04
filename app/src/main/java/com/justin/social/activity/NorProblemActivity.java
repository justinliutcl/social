package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.justin.social.R;
import com.justin.social.databinding.ActivityCustomSocialaccuBinding;
import com.justin.social.databinding.ActivityNormalProblemBinding;
import com.justin.social.model.one.CustomSocialAccuModel;
import com.justin.social.model.one.NormalProblemModel;

/**
 * Created by ASUS on 2018/4/14.
 */

public class NorProblemActivity extends BackActivity {
    ActivityNormalProblemBinding binding;
    NormalProblemModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_normal_problem);
        model = new NormalProblemModel(this);
        binding.setModel(model);
        model.initBind(binding);
        model.initNewsList();
    }
}
