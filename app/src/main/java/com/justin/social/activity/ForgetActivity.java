package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.databinding.ActivityForgetBinding;
import com.justin.social.model.loginMode.ForgetModel;
import com.justin.social.model.loginMode.LoginModel;

/**
 * Created by ASUS on 2018/3/25.
 */

public class ForgetActivity extends SmsActivity {
    ActivityForgetBinding binding;
    ForgetModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forget);
        model = new ForgetModel(this,binding.phoneEd,binding.codeEd,binding.passwordEd);
        binding.setModel(model);
        model.initBind(binding);
        binding.phoneEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 > 0 && !binding.passwordEd.getText().toString().isEmpty()) {
                    model.loginBackId.set(model.loginSelectId);
                } else {
                    model.loginBackId.set(model.loginUnClickId);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.passwordEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i2 > 0 && !binding.phoneEd.getText().toString().isEmpty()) {
                    model.loginBackId.set(model.loginSelectId);
                } else {
                    model.loginBackId.set(model.loginUnClickId);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
