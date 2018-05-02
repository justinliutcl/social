package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;

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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_regist);
        model = new RegistModel(this, binding.phoneEd, binding.codeEd, binding.passwordEd);
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

        binding.setModel(model);
        model.initBind(binding);
    }
}
