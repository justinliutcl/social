package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.justin.social.MainActivity;
import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.RetrofitUtils.DataBean.UserConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.databinding.ActivityLoginBinding;
import com.justin.social.model.loginMode.LoginModel;
import com.justin.social.utils.ConfigUtils;

/**
 * Created by ASUS on 2018/3/25.
 */

public class LoginActivity extends BackActivity {
    ActivityLoginBinding binding;
    LoginModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        model = new LoginModel(this);
        binding.loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new HttpConfigManager().loginConfig(binding.phoneEd.getText().toString(), binding.passwordEd.getText().toString(), new BeanConfigCallBack<LoginConfig>() {
                    @Override
                    public void onDataResponse(LoginConfig bean) {
                        if(ConfigUtils.isSuccess(bean)){
                            MainActivity.JumpTMain(LoginActivity.this);
                            UserDataObtain.getInstance(LoginActivity.this).updataUser(bean.getData().changeToDbUser(),null);
                        }else {
                            toastShow(bean.getMsg());
                        }
                    }
                });
            }
        });
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
    }
}
