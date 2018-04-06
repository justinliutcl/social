package com.justin.social.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.justin.social.MainActivity;
import com.justin.social.R;
import com.justin.social.accessor.CommonSettingValue;

import cn.smssdk.SMSSDK;

/**
 * Created by ASUS on 2018/3/25.
 */

public class SplishActivity extends BackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_splich);
        MainActivity.JumpTMain(this);
//        if(CommonSettingValue.getIns(this).getCurrentPhone()!=null){
//            MainActivity.JumpTMain(this);
//        }else{
//            LoginActivity.JumpToLogin(this);
//        }
//        finish();
    }
}
