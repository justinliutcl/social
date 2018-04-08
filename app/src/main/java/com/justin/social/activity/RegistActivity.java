package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.justin.social.R;
import com.justin.social.databinding.ActivityRegistBinding;
import com.justin.social.model.loginMode.RegistModel;

/**
 * Created by ASUS on 2018/3/25.
 */

public class RegistActivity extends SmsActivity {
    ActivityRegistBinding binding;
    RegistModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_regist);
        model = new RegistModel(this,binding.phoneEd,binding.codeEd,binding.passwordEd);
        binding.setModel(model);
        model.initBind(binding);
    }
}
