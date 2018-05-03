package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.justin.social.MainActivity;
import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RetrofitUtils.DataBean.LoginConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.databinding.ActivityLoginBinding;
import com.justin.social.databinding.ActivityResetcodeBinding;
import com.justin.social.model.five.ResetCodeModel;
import com.justin.social.model.loginMode.LoginModel;
import com.justin.social.utils.AccountUtils;
import com.justin.social.utils.ConfigUtils;

/**
 * Created by ASUS on 2018/3/25.
 */

public class ResetCodeActivity extends BackActivity {
    ActivityResetcodeBinding binding;
    ResetCodeModel model;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_resetcode);
        model = new ResetCodeModel(this);
        binding.loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!AccountUtils.isMobile(binding.phoneEd.getText().toString())){
                    toastShow("请输入正确手机号");
                    return;
                }

                new HttpConfigManager().loginConfig(binding.phoneEd.getText().toString(), binding.passwordEd.getText().toString(), new BeanConfigCallBack<LoginConfig>() {
                    @Override
                    public void onDataResponse(LoginConfig bean) {
                        if(ConfigUtils.isSuccess(bean)){
                            MainActivity.JumpTMain(ResetCodeActivity.this);
                            UserDataObtain.getInstance(ResetCodeActivity.this).updataUser(bean.getData().changeToDbUser(),null);
                            CommonSettingValue.getIns(ResetCodeActivity.this).setCurrentPhone(bean.getData().getPhone());
                            CommonSettingValue.getIns(ResetCodeActivity.this).setCurrentUserID(bean.getData().getUserId());
                        }else {
                            toastShow(bean.getMsg());
                        }
                    }
                });
            }
        });

        binding.setModel(model);
        model.initBind(binding);
    }

}
